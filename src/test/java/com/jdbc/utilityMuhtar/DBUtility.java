package com.jdbc.utilityMuhtar;

import java.sql.*;

public class DBUtility {

    private final static String username = ConfigurationReader.getProperty("JDBC_username"),
            password = ConfigurationReader.getProperty("JDBC_password"),
            URL = ConfigurationReader.getProperty("JDBC_URL");


   private static Connection connection; // we are separting because we cannot throw exception without method
    // DriverManager.getConnection(URL, username, password);
   private static Statement statement;

   public static DatabaseMetaData metadata;

    static {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            statement = connection.createStatement();
            metadata = connection.getMetaData();

        } catch (Exception e) {
        }
    }

    public static ResultSet getResult(String sql) {
        // creates the result

        ResultSet result = null;
        try {
            result = statement.executeQuery(sql);
        } catch (Exception e) {}
            return result;


        }

        public static void tearDown(){
        //closes the connection at the last step
        try {
            connection.close();
        }catch (Exception e){

        }
        }

        public static void updateQuery(String sql){
        //Update, Alter, truncate, delete, drop, insert
        try{
            statement.executeUpdate(sql);
        }catch(Exception e){

        }
        }

    }



