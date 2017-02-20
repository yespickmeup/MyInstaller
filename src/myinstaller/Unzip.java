/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myinstaller;

import java.io.BufferedOutputStream;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JLabel;

/**
 *
 * @author Guinness
 */
public class Unzip {

    List<String> fileList;
    private static final String INPUT_ZIP_FILE = "C:\\Users\\Guinness\\Desktop\\cop\\resources.zip";
    private static final String OUTPUT_FOLDER = "C:\\Users\\Guinness\\Desktop\\cop";

    public static void main(String[] args) {
//        String source = "C:\\Users\\Guinness\\Desktop\\cop\\resources.zip";
//        String destination = "C:\\Users\\Guinness\\Desktop\\cop";
//        Unzip unzipper = new Unzip();
//        try {
//            unzipper.unzip(INPUT_ZIP_FILE, OUTPUT_FOLDER);
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
    }
    private static final int BUFFER_SIZE = 4096;

    /**
     * Extracts a zip file specified by the zipFilePath to a directory specified
     * by destDirectory (will be created if does not exists)
     *
     * @param zipFilePath
     * @param destDirectory
     * @throws IOException
     */
    public void unzip(String zipFilePath, String destDirectory, JLabel lbl) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();
            // iterates over entries in the zip file
            while (entry != null) {
                String filePath = destDirectory + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    // if the entry is a file, extracts it
                    extractFile(zipIn, filePath);
                } else {
                    // if the entry is a directory, make the directory
                    File dir = new File(filePath);
                    dir.mkdir();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
        System.out.println("Zip file extracted...");
        lbl.setText("Zip file extracted...");
    }

    public void move(String path) throws IOException {

        String home = System.getProperty("user.home");
        home = home + "\\";

        String source = path + "retail_res";
        File srcDir = new File(source);

        String destination = home + "retail_res";
        File destDir = new File(destination);

        String source2 = path + "retail_res2";
        File srcDir2 = new File(source2);

        String destination2 = home + "retail_res2";
        File destDir2 = new File(destination2);
        try {
            //
            // Move the source directory to the destination directory.
            // The destination directory must not exists prior to the
            // move process.
            //
            if (!destDir.exists()) {
                FileUtils.moveDirectory(srcDir, destDir);
            }
            if (!destDir2.exists()) {
                FileUtils.moveDirectory(srcDir2, destDir2);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File retail_res is move successful!");

    }

    /**
     * Extracts a zip entry (file entry)
     *
     * @param zipIn
     * @param filePath
     * @throws IOException
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read = 0;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
    }
}
