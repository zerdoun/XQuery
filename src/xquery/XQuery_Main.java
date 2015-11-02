/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xquery;

import xquery.config.configLoader;
import xquery.controller.Controller;
import xquery.model.Model;
import xquery.model.XQueryModel;
import xquery.view.CommandLinesInterface;
import xquery.view.Vue;
import xquery.exceptions.configException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import omdbapi.OMDBAPI;
import xquery.exceptions.SystemException;
import xquery.model.WriteXML;

/**
 *
 * @author simon
 */
public class XQuery_Main {

    /**
     * Runs the example code.
     *
     * @param args (ignored) command-line arguments
     */
    public static void main(final String[] args) throws ParserConfigurationException, TransformerConfigurationException, SystemException {
        try {
            /* Charge la config du config Loader, charge le fichier config.properties dans resources */
            configLoader config = new configLoader(configLoader.CONFIG_PATH);
            /* Construit la vue */
            Vue v = new CommandLinesInterface();
            
            /* Construit le Model, avec le nom database et le chemin contenant the xml */
            Model m = new XQueryModel(config.getProp(configLoader.DATABASE), config.getProp(configLoader.DB_PATH));
            m.addDatabase(config.getProp(configLoader.DATABASE_SERIE), config.getProp(configLoader.DB_PATH_SERIE));
            //construit le controller
            Controller c = new Controller();
            WriteXML xml = new WriteXML();
            /* Lance l'appli*/
            c.run(v, m,xml);
        } catch (configException ex) {
            Logger.getLogger(XQuery_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
