/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myinstaller.user_logs;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mijzcx.synapse.desk.utils.Lg;
import mijzcx.synapse.desk.utils.SqlStringUtil;
import utils.MyConnection;

/**
 *
 * @author Guinness
 */
public class User_logs {

    public static class to_user_logs {

        public final int id;
        public final String user_id;
        public final String user_screen_name;
        public final String ip_address;
        public final String created_at;
        public final String updated_at;
        public final String created_by;
        public final String updated_by;

        public to_user_logs(int id, String user_id, String user_screen_name, String ip_address, String created_at, String updated_at, String created_by, String updated_by) {
            this.id = id;
            this.user_id = user_id;
            this.user_screen_name = user_screen_name;
            this.ip_address = ip_address;
            this.created_at = created_at;
            this.updated_at = updated_at;
            this.created_by = created_by;
            this.updated_by = updated_by;
        }
    }

    public static void add_data(to_user_logs to_user_logs) {
        try {
            Connection conn = MyConnection.connect();
            String s0 = "insert into user_logs("
                    + "user_id"
                    + ",user_screen_name"
                    + ",ip_address"
                    + ",created_at"
                    + ",updated_at"
                    + ",created_by"
                    + ",updated_by"
                    + ")values("
                    + ":user_id"
                    + ",:user_screen_name"
                    + ",:ip_address"
                    + ",:created_at"
                    + ",:updated_at"
                    + ",:created_by"
                    + ",:updated_by"
                    + ")";

            s0 = SqlStringUtil.parse(s0)
                    .setString("user_id", to_user_logs.user_id)
                    .setString("user_screen_name", to_user_logs.user_screen_name)
                    .setString("ip_address", to_user_logs.ip_address)
                    .setString("created_at", to_user_logs.created_at)
                    .setString("updated_at", to_user_logs.updated_at)
                    .setString("created_by", to_user_logs.created_by)
                    .setString("updated_by", to_user_logs.updated_by)
                    .ok();

            PreparedStatement stmt = conn.prepareStatement(s0);
            stmt.execute();
            Lg.s(User_logs.class, "Successfully Added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyConnection.close();
        }
    }

    public static void update_data(to_user_logs to_user_logs) {
        try {
            Connection conn = MyConnection.connect();
            String s0 = "update user_logs set "
                    + "user_id= :user_id "
                    + ",user_screen_name= :user_screen_name "
                    + ",ip_address= :ip_address "
                    + ",created_at= :created_at "
                    + ",updated_at= :updated_at "
                    + ",created_by= :created_by "
                    + ",updated_by= :updated_by "
                    + " where id='" + to_user_logs.id + "' "
                    + " ";

            s0 = SqlStringUtil.parse(s0)
                    .setString("user_id", to_user_logs.user_id)
                    .setString("user_screen_name", to_user_logs.user_screen_name)
                    .setString("ip_address", to_user_logs.ip_address)
                    .setString("created_at", to_user_logs.created_at)
                    .setString("updated_at", to_user_logs.updated_at)
                    .setString("created_by", to_user_logs.created_by)
                    .setString("updated_by", to_user_logs.updated_by)
                    .ok();

            PreparedStatement stmt = conn.prepareStatement(s0);
            stmt.execute();
            Lg.s(User_logs.class, "Successfully Updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyConnection.close();
        }
    }

    public static void delete_data(to_user_logs to_user_logs) {
        try {
            Connection conn = MyConnection.connect();
            String s0 = "delete from user_logs  "
                    + " where id='" + to_user_logs.id + "' "
                    + " ";

            PreparedStatement stmt = conn.prepareStatement(s0);
            stmt.execute();
            Lg.s(User_logs.class, "Successfully Deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyConnection.close();
        }
    }

    public static List<to_user_logs> ret_data(String where) {
        List<to_user_logs> datas = new ArrayList();

        try {
            Connection conn = MyConnection.connect();
            String s0 = "select "
                    + "id"
                    + ",user_id"
                    + ",user_screen_name"
                    + ",ip_address"
                    + ",created_at"
                    + ",updated_at"
                    + ",created_by"
                    + ",updated_by"
                    + " from user_logs"
                    + " " + where;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(s0);
            while (rs.next()) {
                int id = rs.getInt(1);
                String user_id = rs.getString(2);
                String user_screen_name = rs.getString(3);
                String ip_address = rs.getString(4);
                String created_at = rs.getString(5);
                String updated_at = rs.getString(6);
                String created_by = rs.getString(7);
                String updated_by = rs.getString(8);

                to_user_logs to = new to_user_logs(id, user_id, user_screen_name, ip_address, created_at, updated_at, created_by, updated_by);
                datas.add(to);
            }
            return datas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyConnection.close();
        }
    }

    public static String getIpAddress() {
        String ip = "";
        InetAddress IP;
        try {
            IP = InetAddress.getLocalHost();
            ip = IP.getHostAddress();

        } catch (UnknownHostException ex) {
            Logger.getLogger(User_logs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ip;
    }

    public static void main(String[] args) {
        getIpAddress();
    }
}
