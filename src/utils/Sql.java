/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;

/**
 *
 * @author Guinness
 */
public class Sql {

    //  Database credentials
    public static void main(String[] args) {
//        create_db("localhost", "3306", "root", "password");
        String path2 = System.getProperty("user.home");
        System.out.println("Home: " + path2);
    }//end main

    public static void create_db(String host, String port, String user, String password,JLabel lbl) {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://" + host + ":" + port + "/";
        System.out.println("Url: "+DB_URL);
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            lbl.setText("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, user, password);

            //STEP 4: Execute a query
            System.out.println("Creating database...");
              lbl.setText("Creating database...");
            stmt = conn.createStatement();

            String sql2 = "DROP DATABASE if exists db_smis";
            stmt.executeUpdate(sql2);

            String sql = "CREATE DATABASE db_smis";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
             lbl.setText("Database created successfully...");
        } catch (SQLException | ClassNotFoundException se) {
            throw new RuntimeException(se);
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

//        System.out.println(
//                "Goodbye!");
    }
}
