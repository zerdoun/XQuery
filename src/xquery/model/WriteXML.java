package xquery.model;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;
//class pour creer/génerer un XML
public class WriteXML {
    //méthode qui créé/genere un XML en prenant en parametre le nom de la série, et la list comprenant les liens de la série
    public void EditXML(String nom_serie, ArrayList<String> link_list) {
        // Etape 1 : récupération d'une instance de la classe "DocumentBuilderFactory"
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            //Etape 2 : création d'un parseur
            final DocumentBuilder builder = factory.newDocumentBuilder();
            //Etape 3 : création d'un Document
            final Document document = builder.newDocument();
            // Etape 4 : création de l'Element racine series
            final Element racine = document.createElement("series");
            document.appendChild(racine);
            // Etape 5 : création du second element	 
            final Element serie = document.createElement("serie");
            racine.appendChild(serie);
            //Etape 6 : création du node nom de la série
            final Element nom = document.createElement("nom");
            nom.appendChild(document.createTextNode(nom_serie));
            serie.appendChild(nom);
            //boucle créaant les nodes link
            for (String elem : link_list) {
                final Element link = document.createElement("link");
                link.appendChild(document.createTextNode(elem));
                serie.appendChild(link);
            }
            // Etape 8 : affichage
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            final Transformer transformer = transformerFactory.newTransformer();
            final DOMSource source = new DOMSource(document);
            final StreamResult sortie = new StreamResult(new File("xml/" + nom_serie + ".xml"));
	    //final StreamResult result = new StreamResult(System.out);

            //prologue
            ProcessingInstruction ins = document.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\"href=\"series.xsl\"");
            document.insertBefore(ins, racine);
            transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

            //formatage
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            //sortie
            transformer.transform(source, sortie);
        } catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
