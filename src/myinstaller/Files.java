/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myinstaller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import utils.DeEncrypter;
import utils.WindowsUtils;

/**
 *
 * @author Guinness
 */
public class Files {

    public static class field {

        public final String business_name;
        public final String operated_by;
        public final String address;
        public final String telephone_number;
        public final String tin_no;
        public final String min_no;
        public final String serial_no;
        public final String permit_no;
        public final String pos_no;
        public final String accreditation_no;
        public final String status;
        public final String print_to_receipts;
        public final String print_to_receipts2;
        public final String img_path;
        public final String pool_host;
        public final String pool_port;
        public final String pool_user;
        public final String pool_password;
        public final String my_db;

        public field(String business_name, String operated_by, String address, String telephone_number, String tin_no, String min_no, String serial_no, String permit_no, String pos_no, String accreditation_no, String status, String print_to_receipts, String print_to_receipts2, String img_path, String pool_host, String pool_port, String pool_user, String pool_password, String my_db) {
            this.business_name = business_name;
            this.operated_by = operated_by;
            this.address = address;
            this.telephone_number = telephone_number;
            this.tin_no = tin_no;
            this.min_no = min_no;
            this.serial_no = serial_no;
            this.permit_no = permit_no;
            this.pos_no = pos_no;
            this.accreditation_no = accreditation_no;
            this.status = status;
            this.print_to_receipts = print_to_receipts;
            this.print_to_receipts2 = print_to_receipts2;
            this.img_path = img_path;
            this.pool_host = pool_host;
            this.pool_port = pool_port;
            this.pool_user = pool_user;
            this.pool_password = pool_password;
            this.my_db = my_db;
        }

    }

    public static void make(String path, field f, String host, String terminal, JLabel lbl) {
        try {

            String home = System.getProperty("user.home");

            File file = new File(home + "\\my_config.conf");

            if (!file.createNewFile()) {
                System.out.println("my_config.conf file already exists...");
                lbl.setText("my_config.conf file already exists...");
            } else {
                String license = DeEncrypter.encrypt("trial version");
                String stmt = ""
                        + "#configurations\n"
                        + "environment=production\n"
                        + "license_code=" + license + "\n"
                        + "version=V.1.20170206\n"
                        + "pool_host=" + host + "\n"
                        + "terminal_number=" + terminal + "\n"
                        + "hdd_drive=C\n"
                        + "auto_order=true\n"
                        + "";

                try (BufferedWriter out = new BufferedWriter(new FileWriter(home + "\\my_config.conf"))) {
                    out.write(stmt);
                }
                System.out.println("my_config.conf successfully created...");
                lbl.setText("my_config.conf successfully created...");
            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void make_batch(String path, JLabel lbl) {
        try {

            String stmt = ""
                    + "@echo on" + "\r\n"
                    + "java -jar  \"" + path + "dist\\SMIS.jar\"" + "\r\n"
                    + "";

            try (BufferedWriter out = new BufferedWriter(new FileWriter(path + "run.bat"))) {
                out.write(stmt);
            }
        } catch (IOException e) {
            System.out.println("Exception " + e);
        }

    }

    public static void main2(String[] args) {
        String name = "Maytopacka";
        String where = "C:\\Users\\Guinness\\Desktop\\cop\\POS.url";
        String target = "C:\\Users\\Guinness\\Desktop\\cop\\run.bat";
        String icon = "C:\\Users\\Guinness\\Desktop\\cop\\studio.ico";
        try {
            WindowsUtils.createInternetShortcut(name, where, target, icon);
        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void create_shortcut(String path) {
        String home = System.getProperty("user.home");
        String name = "SMIS";
        String where = home + "\\Desktop\\" + "SMIS.url";
        String target = path + "run.bat";
        String icon = path + "logo.ico";
        try {
            WindowsUtils.createInternetShortcut(name, where, target, icon);
        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
