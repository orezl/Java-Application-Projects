/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAHUL
 */
public class dbConnection {
    
    //private static final String url="jdbc:sqlite:C:\\Users\\RAHUL\\Documents\\NetBeansProjects\\schoolManagementSystem\\src\\sqlDB\\School Management System.sqlite";
    private static final String url="jdbc:sqlite:src\\\\sqlDB\\School Management System.sqlite";
    
    public static Connection getConnection() throws SQLException{
    
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
     return null;   
    }
    
}
