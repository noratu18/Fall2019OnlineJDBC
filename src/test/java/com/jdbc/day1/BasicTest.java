package com.jdbc.day1;

import java.sql.*;

public class BasicTest {

    public static void main(String[] args) throws SQLException {

        //the url syntax--> url:jdbc:DataBaseType:thin:@Host:port:SID

          String URL = "jdbc:oracle:thin:@3.93.48.72:1521:xe";
          String username = "hr";
          String password = "hr";

            //to establish connection with a database we need to create give the below statement
        /**
         * ResultSet.TYPE_SCROLL_INSENSITIVE --> The constant indicating the type for a <code>ResultSet</code> object
         * * that is scrollable but generally not sensitive to changes to the data
         * * that underlies the <code>ResultSet</code>.
         * Connection is an interface
         */
        //getConnection() --> gives a checked SQL exception , we need to declare or try catch
        Connection connection = DriverManager.getConnection(URL, username, password);
        System.out.println("Connected successfully!");
        // we need to connect successfully on order to proceed further
        //createStatement() method -->  returns us statement interface
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //in executeQuery method we provide query as a parameter
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

        //resultSet.next()--> returns true and jumps to next row,  if there is some row with data
        while(resultSet.next()) {
            //System.out.println(resultSet.getString(1)); //this will get data from 1 column
         //   System.out.println(resultSet.getString(2)); //this will get second column from table
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " "+ resultSet.getString(3));
        }
         resultSet.beforeFirst();//to comeback to the beginning of result set
        //some technical information about database
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        //some tecg=hnical information about result set
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        System.out.println("JBDC driver: " + databaseMetaData.getDriverName());
        System.out.println("JDBC driver version: " + databaseMetaData.getDriverVersion());
        System.out.println("DATABASE NAME: " +  databaseMetaData.getDatabaseProductName());
        System.out.println("Database version: "+ databaseMetaData.getDatabaseProductVersion());

        System.out.println("Number of columns: "+ resultSetMetaData.getColumnCount()); //11
        System.out.println("Label of 1st column: " + resultSetMetaData.getColumnName(1));
        System.out.println("Data type of first column: " + resultSetMetaData.getColumnTypeName(1));

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        //this loop will loop through columns and will give us the column names
        for(int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++){
            System.out.printf("%-20s", resultSetMetaData.getColumnName(columnIndex));
        }
        System.out.println("");
        //iterate rows
        while(resultSet.next()){
            //iterate columns
            for(int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++){
                System.out.printf("%-20s", resultSet.getString(columnIndex));
            }
            System.out.println("");
        }



        while(resultSet.next()) {
            System.out.println(resultSet.getString("salary"));

        }
        resultSet.close();
        statement.close();
        connection.close();

    }
}
