/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xquery.not_used_examples;

import java.io.IOException;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.XQuery;
import org.basex.io.serial.Serializer;
import org.basex.query.*;
import org.basex.query.iter.Iter;
import org.basex.query.value.Value;
import org.basex.query.value.item.Item;

/**
 *
 * @author pulpito
 */
public class xquery {

    /**
     * Database context.
     */
    static Context context = new Context();

    /**
     * This method evaluates a query by using the database command. The results
     * are automatically serialized and printed.
     *
     * @param query query to be evaluated
     * @throws BaseXException if a database command fails
     */
    static void query(final String query) throws BaseXException {
        System.out.println(new XQuery(query).execute(context));
    }

    /**
     * This method uses the {@link QueryProcessor} to evaluate a query. The
     * resulting items are passed on to a serializer.
     *
     * @param query query to be evaluated
     * @throws QueryException if an error occurs while evaluating the query
     */
    static void process(final String query) throws QueryException {
        // Create a query processor
        try (QueryProcessor proc = new QueryProcessor(query, context)) {
            // Execute the query
            Value result = proc.value();

            // Print result as string.
            System.out.println(result);
        }
    }

    /**
     * This method uses the {@link QueryProcessor} to evaluate a query. The
     * results are iterated one by one and converted to their Java
     * representation, using {{@link Item#toJava()}. This variant is especially
     * efficient if large result sets are expected.
     *
     * @param query query to be evaluated
     * @throws QueryException if an error occurs while evaluating the query
     */
    static void iterate(final String query) throws QueryException {
        // Create a query processor
        try (QueryProcessor proc = new QueryProcessor(query, context)) {
            // Store the pointer to the result in an iterator:
            Iter iter = proc.iter();

            // Iterate through all items and serialize
            for (Item item; (item = iter.next()) != null;) {
                System.out.println(item.toJava());
            }
        }
    }

    /**
     * This method uses the {@link QueryProcessor} to evaluate a query. The
     * results are iterated one by one and passed on to an serializer. This
     * variant is especially efficient if large result sets are expected.
     *
     * @param query query to be evaluated
     * @throws QueryException if an error occurs while evaluating the query
     * @throws IOException if an error occurs while serializing the results
     */
    static void serialize(final String query) throws QueryException, IOException {
        // Create a query processor
        try (QueryProcessor proc = new QueryProcessor(query, context)) {

            // Store the pointer to the result in an iterator:
            Iter iter = proc.iter();

            // Create a serializer instance
            try (Serializer ser = proc.getSerializer(System.out)) {
                // Iterate through all items and serialize contents
                for (Item item; (item = iter.next()) != null;) {
                    ser.serialize(item);
                }
            }
            System.out.println();
        }
    }
}
