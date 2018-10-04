package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Brand {
    private  String id;
    private  String brand;

    public Brand(String id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public static List<String> getBrandString() {
        List<String> org_list = new ArrayList<String>();
        try {
            String sql = "SELECT type FROM brand ORDER BY type";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String name = rs.getString("type");
                org_list.add(name);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
        }
        return org_list;
    }
    public static List<String> getBrandString(String brand) {
        List<String> org_list = new ArrayList<String>();
        try {
            String sql = "SELECT type FROM brand ORDER BY type";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String name = rs.getString("type");
                org_list.add(name);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
        }
        return org_list;
    }

    public static String getBrandID() {
        String org_list = "";
        try {
            String sql = "SELECT type FROM brand";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String name = rs.getString("type");
                org_list = name;
            }
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
        }
        return org_list;
    }

}
