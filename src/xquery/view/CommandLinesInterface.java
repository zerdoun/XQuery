/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xquery.view;

import xquery.exceptions.InputException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author pulpito
 */
public class CommandLinesInterface implements Vue {

    BufferedReader bufferRead;

    /**
     * constructor initialize the buffer reader
     */
    public CommandLinesInterface() {
        bufferRead = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * show the main menu
     */
    @Override
    public void showMainMenu() {
        System.out.println("=== RunCommands ===");
        System.out.println("What do you want to do ?");
        System.out.println("1. Show existing databases");
        System.out.println("2. List all documents in the database:");
        System.out.println("3. Open Database");
        System.out.println("4. Query Database");
        System.out.println("5. Projet XQuery, Génération d'XML");
        System.out.println("0. Exit");
    }

    /**
     * show menu title
     *
     * @param str title
     */
    @Override
    public void showMenu(String str) {
        System.out.println("\n* " + str);
    }

    /**
     * show a sentence
     *
     * @param str sentence
     */
    @Override
    public void show(String str) {
        System.out.println(str);
    }

    /**
     * show the result of an opening
     *
     * @param str object opened
     */
    @Override
    public void showOpening(String str) {
        System.out.println("Opening : " + str);
        System.out.println("=== Opening SuccessFull ===");
    }

    /**
     * show interface and wait for a database name
     *
     * @return the user input as String
     * @throws InputException
     */
    @Override
    public String getDbName() throws InputException {
        // The XQuery base-uri() function returns a file path
        show("Enter Database name :");
        return inputCommand();
    }

    /**
     * show interface and wait for a query
     *
     * @return the user input as String
     * @throws InputException
     */
    @Override
    public String getQuery() throws InputException {
        // The XQuery base-uri() function returns a file path
        show("Enter Query in XQuery Language :");
        return inputCommand();
    }

    /**
     * show the database error
     */
    @Override
    public void showDbError() {
        System.out.println("=== Opening Cancelled ===");
        System.out.println("=== Unknown Database ===");
    }

    /**
     * show the query error
     *
     * @param e
     */
    @Override
    public void showQueryError(Exception e) {
        System.out.println("\n\n=== Query Cancelled ===");
        System.out.println("=== Query encounter error ===");
        //e.printStackTrace();
        System.out.println("\n\n=== Trace End ===");
    }

    /**
     * show the input error
     *
     * @param e
     */
    @Override
    public void showInputError(Exception e) {
        System.out.println("\n\n=== Input Error ===");
        //e.printStackTrace();
        System.out.println("\n\n=== Trace End ===");
    }

    /**
     * wait for the user's action
     *
     * @return the input as String
     * @throws InputException
     */
    @Override
    public String inputCommand() throws InputException {
        try {
            return bufferRead.readLine();
        } catch (IOException ex) {
            throw new InputException(ex.toString());
        }
    }

}
