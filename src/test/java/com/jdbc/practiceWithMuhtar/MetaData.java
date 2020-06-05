package com.jdbc.practiceWithMuhtar;

import com.jdbc.utilityMuhtar.DBUtility;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MetaData {

    public static void main(String[] args) throws SQLException {

        System.out.println("User: " + DBUtility.metadata.getUserName());
        System.out.println("Driver version: " + DBUtility.metadata.getDriverVersion());
        System.out.println("Database Type: " + DBUtility.metadata.getDatabaseProductName());
        System.out.println("Driver Name: " + DBUtility.metadata.getDriverName());

        ResultSet result = DBUtility.getResult("SELECT * FROM employees");

        ResultSetMetaData resultData = result.getMetaData();

        System.out.println(resultData.getColumnCount());//how many column we have
        System.out.println(resultData.getColumnName(5));// 5th column name
        System.out.println("****************************");
        
        // to print out the all column names
        for(int i = 1; i< resultData.getColumnCount(); i++){
            System.out.println(resultData.getColumnName(i));
        }


    }
}
