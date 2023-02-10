
package com.frank.escuelaws.config;

import java.sql.Connection;
import java.sql.DriverManager;


public class dbConfig {
    private static Connection conn;
    private static String server = "DESKTOP-85NKVO2\\SQLEXPRESS";
    private static String port = "1434";
    private static String dbName = "practica";
    private static String user = "SA";
    private static String password = "h@acker644";
    private static String url = "jdbc:sqlserver://"+server+":"+port+";"+"databaseName="+dbName+";integratedSecurity=false;encrypt=false;trustServerCertificate=false";
    
    public static Connection getConnection(){
        try {
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
        return conn;
    }
}
