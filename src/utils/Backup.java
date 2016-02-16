/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guinness
 */
public class Backup {

    public static void Backupdbtosql(String path) throws URISyntaxException {
        try {

            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = Backup.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
//            String jarDir = jarFile.getParentFile().getPath();
            String jarDir = path;

            /*NOTE: Creating Database Constraints*/
            String dbName = System.getProperty("mydb", "db_fortune_three");

            String dbUser = "root";
            String dbPass = "password";
            String host = System.getProperty("pool_host", "localhost");
            host = host.replaceAll(":3306", "");
            String date = DateType.backup.format(new Date());

            /*NOTE: Creating Path Constraints for folder saving*/
            /*NOTE: Here the backup folder is created for saving inside it*/
            String folderPath = jarDir + "\\backup";
            System.out.println("Database: " + dbName);
            System.out.println("Host: " + host);
            System.out.println(folderPath);
            /*NOTE: Creating Folder if it does not exist*/
            File f1 = new File(folderPath);
            f1.mkdir();

            /*NOTE: Creating Path Constraints for backup saving*/
            /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
            String savePath = "\"" + jarDir + "\\backup\\" + date + "_" + dbName + ".sql\"";

            /*NOTE: Used to create a cmd command*/
            String executeCmd = "mysqldump -u" + dbUser + " -p" + dbPass + " -h" + host + " --database " + dbName + " -r " + savePath;

            /*NOTE: Executing the command here*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                System.out.println("Backup Complete");
            } else {
                System.out.println("Backup Failure");
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        try {
            //        try {
//            Backupdbtosql();
//           
//        } catch (URISyntaxException ex) {
//            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
//        }
            Restoredbfromsql("backup.sql");
        } catch (InterruptedException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Restoredbfromsql(String s) throws InterruptedException, URISyntaxException {
        try {
            /*NOTE: String s is the mysql file name including the .sql in its name*/
            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = Backup.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            /*NOTE: Creating Database Constraints*/
            String dbName = System.getProperty("mydb", "db_fortune_three");
            String dbUser = "root";
            String dbPass = "password";
            String host = System.getProperty("pool_host", "localhost");
            host = host.replaceAll(":3306", "");
            System.out.println("Database: " + dbName);
            System.out.println("Host: " + host);
            /*NOTE: Creating Path Constraints for restoring*/
//            String restorePath = jarDir + "\\backup" + "\\" + s;
            String restorePath = s;

            /*NOTE: Used to create a cmd command*/
            /*NOTE: Do not create a single large string, this will cause buffer locking, use string array*/
            String[] executeCmd = new String[]{"mysql", dbName, "-u" + dbUser, "-p" + dbPass, "-h" + host, "-e", " source " + restorePath};

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                System.out.println("Successfully restored from SQL : " + s);
            } else {
                System.out.println("Error at restoring");
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
