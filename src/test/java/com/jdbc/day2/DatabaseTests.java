package com.jdbc.day2;


import com.github.javafaker.Faker;
import org.junit.Test;


import java.sql.*;
import java.util.*;

public class DatabaseTests {

    //final makes our string immutable, we cannot change
    final String DB_URL = "jdbc:oracle:thin:@3.93.48.72:1521:xe";
    final String DB_USER = "hr";
    final String DB_password = "hr";

  @Test
    public void getEmployeesData() throws SQLException {
      Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_password);
      Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      // ResultSet.CONCUR_READ_ONLY) --> creates resulTest object that cannot be updated but can be read,disable editing the result
      //ResultSet.TYPE_SCROLL_INSENSITIVE -->makes resultSet scrollable
      String QUERY = "SELECT * FROM employees";
      ResultSet resultSet = statement.executeQuery(QUERY);

      List<Integer> employeeID = new ArrayList<>();
      List<String> names = new ArrayList<>();
      List<Map<String, Integer>> employeeIdMap = new ArrayList<>();
      List<Map<String, String>> namesMap = new ArrayList<>();


      while (resultSet.next()){
          //next()--> returns data of every single row, while reaches the end of row exists the loop
         Map<String, Integer> map = new HashMap<>();
         //employee_id--> unique value(key)
         map.put("employee_id", resultSet.getInt("employee_id"));
         employeeIdMap.add(map);
          employeeID.add(resultSet.getInt("employee_id"));

          String fullName = resultSet.getString("first_name")+ " " + resultSet.getString("last_name");

          names.add(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
          Map<String, String> name = new HashMap<>();
          name.put("full_name", fullName);
          namesMap.add(name);

      }
      System.out.println(employeeID);
      System.out.println(names);
      System.out.println(employeeIdMap);
      System.out.println(namesMap);

      //get 5th employee
      String fifthEmployee = namesMap.get(4).get("full_name");
      System.out.println("5th employee: " + fifthEmployee);



      resultSet.close();
      statement.close();
      connection.close();

  }

  @Test
  public void insertTest() throws SQLException {
      Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_password);
      Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

      String QUERY_GET_LAST_EMPLOYEE_ID = "SELECT MAX(employee_id) FROM employees";
      //return last employee id
      ResultSet resultSet = statement.executeQuery(QUERY_GET_LAST_EMPLOYEE_ID);
      //this object contains resultSet data

      resultSet.next();//jumps to the first row. Initially, pointer is outside of the table
      int employeeID = resultSet.getInt(1)+1;
      //since employee_id is an Integer, we get getInt("column index"), and + 1 to increment

        //to check if email exists
      boolean emailExists = false;
      String randomEmail = null;
      Faker faker = new Faker();

      do {
          randomEmail =  faker.internet().emailAddress();//to generate fake email
          //randomEmail --> every iteration will have different value

          String QUERY_TO_CHECK_IF_EMAIL_EXISTS = "SELECT COUNT(*) FROM employees WHERE email = '" + randomEmail+"'";
          //to check if email exists
          ResultSet resultSet1 = statement.executeQuery(QUERY_TO_CHECK_IF_EMAIL_EXISTS);
          resultSet1.next();//proceed to the first row
          emailExists = resultSet1.getInt(1) > 0; //
          //if count is positive, it will true, means email exists
      }while(emailExists && randomEmail.length() < 23); // if count is positive, repeat steps again until email is unique
      //we put 25 because the tbale has conctraints no more then 25 email should be

      String QUERY = "INSERT INTO employees VALUES(" + employeeID + ", '" + faker.name().firstName() + "', '" + faker.name().lastName() + "', '" + randomEmail + "', '508-598-6987', SYSDATE, 'IT_PROG', 15000, 0, NULL, NULL)";
      //String QUERY = "INSERT INTO employees VALUES("+employeeID+", 'Ziyoda', 'Mahsut','"+randomEmail+"', '508-258-9632',SYSDATE, 'IT-PROG',15000,0,NULL, NULL);";

      System.out.println("Query: " + QUERY);
      ResultSet resultSet2 = statement.executeQuery(QUERY);


      resultSet.close();
      statement.close();
      connection.close();


  }

}
