/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myinstaller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 *
 * @author Guinness
 */
public class Shortcut {

    public static void main(String[] args) {
        try {
            String path = "C:\\Users\\Guinness\\Desktop\\cop\\";
            String stmt = ""
                    + "[InternetShortcut]" + "\n"
                    + "URL=file://C:/Users/Guinness/Desktop/cop/dist/POS_Retail.jar" + "\n"
                    + "IconIndex=0" + "\n"
                    + "IconFile=C:\\Users\\Guinness\\Desktop\\cop\\studio.ico" + "\n";
//            System.out.println(stmt);
//            try (BufferedWriter out = new BufferedWriter(new FileWriter(path + "shortcut.url"))) {
//                out.write(stmt);
//            }

            try (FileInputStream inputStream = new FileInputStream("C:\\Users\\Guinness\\Desktop\\cop\\Retail.lnk")) {
                String everything = IOUtils.toString(inputStream);
                System.out.println(everything);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("====================working===================");

            File f1 = new File("C:\\Users\\Guinness\\Desktop\\cop\\Retail.lnk");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (line.contains("C:\\")) {
                    line = line.replace("C:\\=", "C:\\Users\\Guinness\\Desktop\\cop\\dist\\POS_Retail.jar");
                }

                lines.add(i, line);
                i++;
            }
            fr.close();
            br.close();

            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for (int j = 0; j < lines.size(); j++) {
                System.out.println(j + "." + lines.get(j));
                out.append(lines.get(j));
                out.newLine();
            }

            out.flush();
            out.close();
            System.out.println("====================work done===================");

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
    static List<String> lines = new ArrayList<String>();
    static String line = null;

}
