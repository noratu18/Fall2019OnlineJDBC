package com.jdbc.practiceWithMuhtar;

import java.sql.*;

public class SimpleConnection {
    public static void main(String[] args) throws SQLException {

        String URL = "jdbc:oracle:thin:@3.93.48.72:1521:xe";
      String username = "hr";
      String password = "hr";

        Connection connection = DriverManager.getConnection(URL, username, password);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM employees");

        //we use next() method --> to iterate each rows
        //we have to iterate each rows until the end row (next() row exist)
        while (result.next()){
        // 1st.way to do-->  String firstName = result.getString("first_name");//it will print all first name in the column
        // 2nd. way to do--> we can give index number which is 2 for first name
            String firstName = result.getString(2);
            String lastName = result.getString(3); // will get last name
            int salary = result.getInt("salary");

            System.out.println(firstName +" " + lastName + " " + "    $" + salary);




        }



    }

}
