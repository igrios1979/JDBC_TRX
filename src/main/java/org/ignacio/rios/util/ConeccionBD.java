package org.ignacio.rios.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConeccionBD { // SINGLETON

    private static    String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=UTC";
    private static    String user = "root";
    private static   String pass = "master";

    private static Connection connection;


    public static Connection getInstance() throws SQLException {
        if(connection==null){
            connection = DriverManager.getConnection(url,user,pass);
        }
      return connection;
    }







}
