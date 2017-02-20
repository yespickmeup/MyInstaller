/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myinstaller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JLabel;

/**
 *
 * @author Guinness
 */
public class Copy {

    public static void main(String[] args) {

    }

    public static void and_paste(String path, JLabel lbl) {
        InputStream inStream = Copy.class.getResourceAsStream("/resources/resources.zip");
        OutputStream outStream = null;

        try {

            File file = new File(path);
            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Directory is created...");
                    lbl.setText("Directory is created...");
                } else {
                    System.out.println("Failed to create directory...");
                    lbl.setText("Failed to create directory...");
                }
            }

            File bfile = new File(path + "resources.zip");
            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes 
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File is copied successful...");
            lbl.setText("File is copied successful...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
