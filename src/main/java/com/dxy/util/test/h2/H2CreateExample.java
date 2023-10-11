package com.dxy.util.test.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create Statement JDBC Example
 *
 * @author Ramesh Fadatare
 */
public class H2CreateExample {

    private static final String createTableSQL = "create table users (\r\n" + "  id  int(3) primary key,\r\n" +
            "  name varchar(20),\r\n" + "  email varchar(20),\r\n" + "  country varchar(20),\r\n" +
            "  password varchar(20)\r\n" + "  );";

    public static void main(String[] argv) throws SQLException {
        H2CreateExample createTableExample = new H2CreateExample();
        createTableExample.createTable();
        String s = "";
    }

    public void createTable() throws SQLException {

        System.out.println(createTableSQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:");
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            statement.execute(createTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}