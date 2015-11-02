/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xquery.config;

import xquery.exceptions.configException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author pulpito
 */
public class configLoader {

    private Properties pr;
    public final static String CONFIG_PATH = "src/resources/config.properties";
    public final static String DATABASE = "databaseName";
    public final static String DB_PATH = "path";
    public final static String DATABASE_SERIE = "databaseName_serie";
    public final static String DB_PATH_SERIE = "path_serie";

    /**
     * get the config from the config.properties file
     *
     * @param path path to the config.properties
     * @throws configException
     */
    public configLoader(String path) throws configException {
        try {
            pr = new Properties();
            File f = new File(path);
            System.out.println(f);
            InputStream inputStream = new FileInputStream(f);
            pr.load(inputStream);
        } catch (IOException ex) {
            throw new configException("file not found");
        }
    }

    public String getProp(String prop) {
        return pr.getProperty(prop);
    }
}
