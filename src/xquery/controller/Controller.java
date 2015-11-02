package xquery.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import omdbapi.OMDBAPI;
import xquery.model.Model;
import xquery.model.WriteXML;
import xquery.view.Vue;
import xquery.exceptions.InputException;
import xquery.exceptions.SystemException;

/**
 *
 * @author pulpito
 */
public class Controller {

    /**
     * control the interactionction between the view and the model
     *
     * @param v view
     * @param m model
     */
    public void run(Vue v, Model m, WriteXML xml) throws ParserConfigurationException, TransformerConfigurationException {
        try {
            /* add files to the database */
            v.showOpening(m.useDefaultDb());
            m.refreshDb();
        } catch (SystemException ex) {
            v.showDbError();
        }
        while (true) {
            try {
                /* show menu */
                v.showMainMenu();
                /* execute a command regarding the user input*/
                switch (v.inputCommand()) {
                    case Vue.CHOICE_DB:
                        try {
                            v.showMenu(Vue.MENU_DB);
                            v.show(m.getDatabases());
                        } catch (SystemException e) {
                            v.showDbError();
                        }
                        break;
                    case Vue.CHOICE_LIST:
                        try {
                            v.showMenu(Vue.MENU_LIST);
                            v.show(m.getElementsInCollection(v.getDbName()));
                        } catch (SystemException e) {
                            v.showDbError();
                        }
                        break;
                    case Vue.CHOICE_OPEN_DB:
                        try {
                            v.showMenu(Vue.MENU_OPEN_DB);
                            v.showOpening(m.openDb(v.getDbName()));
                        } catch (SystemException e) {
                            v.showDbError();
                        }
                        break;
                    case Vue.CHOICE_QUERY:
                        try {
                            v.showMenu(Vue.MENU_QUERY);
                            v.show(m.executeQuery(v.getQuery()));
                        } catch (SystemException e) {
                            v.showQueryError(e);
                        }
                        break;
                    case Vue.CHOICE_EXIT:
                        v.showMenu(Vue.EXIT);
//                        try {
//                            m.deleteDb("");
//                        } catch (SystemException ex) {
//                            v.showDbError();
//                        }
                        break;
                    //case qui correspond à notre application permettant de génerer un xml par série. 
                    case Vue.DEBUT:
                        try {
                            v.show("Test: ");
                            //open la database kat (du rss)
                            v.showOpening(m.openDb("kat"));
                            //requete qui prend les titres et liens des series du rss
                            String titre_serie_rss = m.executeQuery("for $d in /rss/channel/item return($d/title/text(),$d/link/text()) ");
                            //separe le string a chaque saut de ligne. un titre un lien, un titre un lien etc ...
                            String[] titre_serie_rss_table = titre_serie_rss.split("\n");
                            //permet de convertir les titre_serie en minuscule du kat.xml
                            for (int i = 0; i < titre_serie_rss_table.length; i = i + 2) {
                                String tmp = titre_serie_rss_table[i].toLowerCase();
                                titre_serie_rss_table[i] = tmp;
                            }
                            //open la database du series.xml
                            v.showOpening(m.openDb("series"));
                            //requete xquery qui prend tout les titres de mon series.xml
                            String titre_serie = m.executeQuery("for $c in /series/serie/nom return $c/text()");
                            //separe le string a chaque saut de ligne.
                            String[] titre_serie_table = titre_serie.split("\n");
                            //convert le titre_serie en minuscule du series.xml
                            for (int i = 0; i < titre_serie_table.length; i++) {
                                String tmp = titre_serie_table[i].toLowerCase();
                                titre_serie_table[i] = tmp;
                            }
                            //boucle permettant de parcourir toute les séries de notre series.xml
                            for (int j = 0; j < titre_serie_table.length; j++) {
                                OMDBAPI omdb = new OMDBAPI();
                                omdb.OmdbAPI(titre_serie_table[j].trim().replaceAll(" ", "%20"));
                                //on définit un arraylist pour stocker les liens de notre série
                                ArrayList<String> link = new ArrayList<String>();
                                //boucle permettant de parcourir toute les séries de notre rss, ici kat.xml
                                for (int i = 0; i < titre_serie_rss_table.length; i = i + 2){
                                    //test permettant de récuperer les series qui sont correspondantes; on vérifie si dans les liens et dans les titres de notre rss, ils contiennent le titre de nos séries de notre series.xml
                                    if (titre_serie_rss_table[i].trim().matches(".*" + titre_serie_table[j].trim() + ".*")||titre_serie_rss_table[i+1].trim().matches(".*" + titre_serie_table[j].trim() + ".*")) {
                                        //on ajout le lien à l'arraylist contenant les liens de la série
                                        link.add(titre_serie_rss_table[i].trim());
                                    }
                                }
                                //on crée notre xml avec en parametre, le nom de notre série et la liste de nos liens
                                xml.EditXML(titre_serie_table[j].trim(), link);
                            }
                        } catch (SystemException e) {
                            v.showDbError();
                        }
                        return;

                }
            } catch (InputException ex) {
                v.showInputError(ex);
            }
        }
    }

}
