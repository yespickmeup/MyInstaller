/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import myinstaller.pnl.Dlg_setup;
import utils.Backup;

/**
 *
 * @author Guinness
 */
public class sql {

    public static void main(String[] args) {
        try {
            String path = "C:\\Users\\Guinness\\Desktop\\cop\\database.sql";
            Backup.Restoredbfromsql(path);
        } catch (InterruptedException | URISyntaxException ex) {
            Logger.getLogger(Dlg_setup.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
