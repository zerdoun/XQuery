/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xquery.view;

import xquery.exceptions.InputException;

/**
 *
 * @author pulpito
 */
public interface Vue {

    String CHOICE_DB = "1";
    String CHOICE_EXIT = "0";
    String CHOICE_LIST = "2";
    String CHOICE_OPEN_DB = "3";
    String CHOICE_QUERY = "4";
    String DEBUT ="5";
    
    String EXIT = "Exit";
    String MENU_DB = "Show existing databases:";
    String MENU_LIST = "List all documents in the database:";
    String MENU_OPEN_DB = "Open Database:";
    String MENU_QUERY = "Query Database:";
    
    String getDbName() throws InputException;

    String getQuery() throws InputException;

    String inputCommand() throws InputException;

    void show(String str);

    void showDbError();

    void showInputError(Exception e);

    void showMainMenu();

    void showMenu(String str);

    void showOpening(String str);

    void showQueryError(Exception e);

}
