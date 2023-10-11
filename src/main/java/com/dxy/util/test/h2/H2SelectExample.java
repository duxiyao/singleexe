//package com.dxy.util.test.h2;
//
//
//import java.sql.*;
//
///**
// * Select PreparedStatement JDBC Example
// *
// * @author Ramesh Fadatare
// */
//public class H2SelectExample {
//    private static final String QUERY = "select id,name,email,country,password from users where id =?";
//
//    private static final String createTableSQL = "create table users (\r\n" + "  id  int(3) primary key,\r\n" +
//            "  name varchar(20),\r\n" + "  email varchar(20),\r\n" + "  country varchar(20),\r\n" +
//            "  password varchar(20)\r\n" + "  );";
//
//
//    private static final String INSERT_USERS_SQL = "INSERT INTO users" +
//            "  (id, name, email, country, password) VALUES " +
//            " (?, ?, ?, ?, ?);";
//
//
//    public static void main(String[] args) throws SQLException {
//
//        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:");
//             Statement statement = connection.createStatement();) {
//
//            statement.execute(createTableSQL);
//
//            try (
//                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
//                preparedStatement.setInt(1, 1);
//                preparedStatement.setString(2, "Tony");
//                preparedStatement.setString(3, "tony@gmail.com");
//                preparedStatement.setString(4, "US");
//                preparedStatement.setString(5, "secret");
//
//                System.out.println(preparedStatement);
//                // Step 3: Execute the query or update query
//                preparedStatement.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
//                preparedStatement.setInt(1, 1);
//                System.out.println(preparedStatement);
//                // Step 3: Execute the query or update query
//                ResultSet rs = preparedStatement.executeQuery();
//
//                // Step 4: Process the ResultSet object.
//                while (rs.next()) {
//                    int id = rs.getInt("id");
//                    String name = rs.getString("name");
//                    String email = rs.getString("email");
//                    String country = rs.getString("country");
//                    String password = rs.getString("password");
//                    System.out.println(id + "," + name + "," + email + "," + country + "," + password);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        // Step 4: try-with-resource statement will auto close the connection.
//    }
//
//}