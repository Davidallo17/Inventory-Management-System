package Model;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection con = null;
    //FOR RPI SERVER
    public static Connection getConnection() {
        try {
            String ip = InetAddress.getByName("giss").getHostAddress();
            String url = "jdbc:mysql://" + ip + ":3306/assets?zeroDateTimeBehavior=convertToNull";
            con = DriverManager.getConnection(url, "admin", "gissadmin");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //FOR LOCAL SERVER
//    public static String url = "jdbc:sqlite:C:\\Users\\Public\\GISS_IMS.db";
//   // public static Connection con = null;
//        public static Connection getConnection(){
//        try {
//            con = DriverManager.getConnection(url);
//            return con;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    
    
    
}
