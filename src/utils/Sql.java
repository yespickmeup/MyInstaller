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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static void create_db(String host, String port, String user, String password) {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://" + host + ":" + port + "/";
        System.out.println(DB_URL);
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, user, password);

            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();

            String sql2 = "DROP DATABASE if exists db_fortune_three";
            stmt.executeUpdate(sql2);

            String sql = "CREATE DATABASE db_fortune_three";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
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

        System.out.println(
                "Goodbye!");
    }
}
