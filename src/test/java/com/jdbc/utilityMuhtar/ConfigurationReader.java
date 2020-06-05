package com.jdbc.utilityMuhtar;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties configFile;
    static {
        try{
            //provides access to file
            // try/catch clock stands for handling ecveptions
            //if exceptions occurs, code inside a catch bloock wil be exceuted
            //any class that is retaed to InputOutput produce checked exceptions
            //without handling checked exception, you cannot run a code
            FileInputStream fileInputStream = new FileInputStream("configuration.properties");
            //initialize properties object
            configFile = new Properties();
            //load configuration,properties
            configFile.load(fileInputStream);
            //close input stream
            fileInputStream.close();

        } catch (IOException e ){
            System.out.println("Failed to load properties file!");
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return configFile.getProperty(key);

    }

    public static void main(String[] args) {
        String URL = getProperty("JDBC_URL");
        String username = getProperty("JDBC_username");
        String password = getProperty("JDBC_password");

        System.out.println(URL);
        System.out.println(username);
        System.out.println(password);


    }
}
