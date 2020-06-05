package com.jdbc.day1;

import java.sql.*;

public class InsertAndDeleteTest {

    //the base url --> url:jdbc:DataBaseType:thin:@Host:port:SID

    static String URL = "jdbc:oracle:thin:@3.93.48.72:1521:xe";
    static String username = "hr";
    static String password = "hr";

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(URL, username,password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String INSERT_QUERY = "INSERT INTO employees VALUES(225, 'Norajan', 'Norajan', 'noranora', '999-88-9998', SYSDATE, 'SDET', 47891, 0, NULL, NULL)";
        String DELETE_QUERY = "DELETE FROM employees WHERE employee_id = 223";

        ResultSet resultSet1 = statement.executeQuery(DELETE_QUERY);
        ResultSet resultSet = statement.executeQuery(INSERT_QUERY);
    //    ResultSet resultSet1 = statement.executeQuery(DELETE_QUERY);



        resultSet.close();
        statement.close();
        connection.close();
    }
}
