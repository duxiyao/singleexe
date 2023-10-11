package com.dxy.util.test.h2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2DeleteExample {
    private static final String deleteTableSQL = "delete from users where id = 1";

    public static void main(String[] argv) throws SQLException {
        H2DeleteExample deleteExample = new H2DeleteExample();
        deleteExample.deleteRecord();
    }

    public void deleteRecord() throws SQLException {

        System.out.println(deleteTableSQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:");
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            statement.execute(deleteTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}