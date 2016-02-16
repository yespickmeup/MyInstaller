/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myinstaller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static void make(String path, field f) {
        try {
            String host = "";
            String user = "";
            String password = "";
            String db_name = "";
            String img_path = System.getProperty("user.home");
            img_path = img_path.replaceAll("\\\\", "\\\\\\\\");
            img_path = img_path + "\\\\";
            String stmt = ""
                    + "business_name=" + f.business_name + "\n"
                    + "operated_by=" + f.operated_by + "\n"
                    + "address=" + f.address + "\n"
                    + "telephone_number=" + f.telephone_number + "\n"
                    + "tin_no=TIN no: " + f.tin_no + "\n"
                    + "min_no=MIN No.: " + f.min_no + "\n"
                    + "serial_no=Serial No.: " + f.serial_no + "\n"
                    + "permit_no=Permit No.: " + f.permit_no + "\n"
                    + "pos_no=POS No.: " + f.pos_no + "\n"
                    + "acct_no=Accreditation No.: " + f.accreditation_no + "\n"
                    + "status= " + f.status + "\n"
                    + "print_to_receipts=true" + "\n"
                    + "print_to_receipts2=true" + "\n"
                    + "img_path=" + img_path + "\n"
                    + "pool_host=" + f.pool_host + ":" + f.pool_port + "\n"
                    + "pool_user=" + f.pool_user + "\n"
                    + "pool_password=" + f.pool_password + "\n"
                    + "mydb=" + f.my_db + "\n"
                    + "";

            String text = "Your sample content to save in a text file.";
            try (BufferedWriter out = new BufferedWriter(new FileWriter(path + "retail.conf"))) {
                out.write(stmt);
            }
        } catch (IOException e) {
            System.out.println("Exception ");
        }

    }

    public static void make_batch(String path) {
        try {
            String host = "";
            String user = "";
            String password = "";
            String db_name = "";
            String img_path = System.getProperty("user.home");
            img_path = img_path.replaceAll("\\\\", "\\\\\\\\");
            img_path = img_path + "\\\\";

            String stmt = ""
                    + "@echo on" + "\r\n"
                    + "java -jar  \"" + path + "dist\\POS_Retail.jar\"" + "\r\n"
                    + "";

            try (BufferedWriter out = new BufferedWriter(new FileWriter(path + "run.bat"))) {
                out.write(stmt);
            }
        } catch (IOException e) {
            System.out.println("Exception ");
        }

    }

    public static void main(String[] args) {
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
        String name = "POS Retail";
        String where = home + "\\Desktop\\" + "Point of Sale(POS).url";
        String target = path + "run.bat";
        String icon = path + "logo.ico";
        try {
            WindowsUtils.createInternetShortcut(name, where, target, icon);
        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
