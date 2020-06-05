package com.jdbc.practiceWithMuhtar;

import com.jdbc.utilityMuhtar.DBUtility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultSet_Map {

    public static void main(String[] args) throws SQLException {


       ResultSet result =  DBUtility.getResult("SELECT * FROM employees");
        Map<String, Integer> employeeInfo = new LinkedHashMap<>();
        //LinkedHashMap --> will get order as it is

     //   String nameOfEnployee9000 = "";

       while (result.next()){
           String firstName = result.getString("first_name");
           String lastName = result.getString("last_name");

           String fullName = firstName + " " + lastName;
           Integer salary = result.getInt("salary");

          // System.out.println(fullName + " " + salary);
           employeeInfo.put(fullName, salary);
//           if(salary == 9000){
//               System.out.println(fullName+ " is making 9000");
//           }

       }

        System.out.println(employeeInfo);
        System.out.println(employeeInfo.get("Steven King") == 24000);



    }
}