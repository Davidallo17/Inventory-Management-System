package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public static List<String> getCategoryList() {
        List<String> org_list = new ArrayList<String>();
        try {
            String sql = "SELECT type FROM category ORDER BY type";
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
    public static List<String> getCategoryList(String brand) {
        List<String> org_list = new ArrayList<String>();
        try {
            String sql = "SELECT type FROM category";
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
}
