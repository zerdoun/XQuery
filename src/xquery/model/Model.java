/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xquery.model;

import xquery.exceptions.SystemException;

/**
 *
 * @author pulpito
 */

public interface Model {

    void deleteDb(String db) throws SystemException;

    String executeQuery(String query) throws SystemException;

    String getDatabases() throws SystemException;

    String getElementsInCollection(String collectionName) throws SystemException;

    String openDb(String dbName) throws SystemException;

    String createDatabase() throws SystemException;
    
    String addDatabase(String dbName,String path) throws SystemException;

    void removeXML(String file) throws SystemException;

    void addXMLToDb(String path) throws SystemException;

    void refreshDb() throws SystemException;

    String useDefaultDb() throws SystemException;

}
