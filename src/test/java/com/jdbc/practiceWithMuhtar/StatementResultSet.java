package com.jdbc.practiceWithMuhtar;

import java.sql.*;

public class StatementResultSet {

    public static void main(String[] args) throws SQLException {

        String URL = "jdbc:oracle:thin:@3.93.48.72:1521:xe";
        String username = "hr";
        String password = "hr";

        //1. Connection step:
        Connection connection = DriverManager.getConnection(URL, username, password);

        //2. Statement step:
        // we need to execute from statement reference
        Statement statement = connection.createStatement();
        System.out.println("Statement is created!");

        //3. ResultSet step: --> to retrieve data from our database
        //we use executeQuery() method --> to execute our query
        //SELECT * FROM employees --> we getting this from sql it should be exactly same as in SQL
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
        System.out.println("Result Set is completed!");

        String sql = "Update cybertek15 set first_name = 'Andrea' WHERE first_name = 'Andera'";
        statement.executeUpdate(sql);







    }
}
