package Model;

import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.SQLException;

public class EmployeeDAO {
    public static List<Employee> getUniWideList() {
        List<Employee> org_list = new ArrayList<Employee>();
        try {
            String sql = "SELECT * FROM employee ORDER BY 2 ";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String employee_id = rs.getString("id");
                String username = rs.getString("username");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String status = rs.getString("status");
                Employee org = new Employee(employee_id, username, firstName, lastName, password, role, status);
                org_list.add(org);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
        }
        return org_list;
    }

    public static List<String> getUniWideListStringName() {
        List<String> org_list = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM employee WHERE status = 'Active' ORDER BY 2";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String name = rs.getString("username");
                org_list.add(name);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
        }
        return org_list;
    }

    public static List<String> getUniWideListStringNameUser() {
        List<String> org_list = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM employee ORDER BY 2";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String name = rs.getString("username");
                String fn = rs.getString("firstName");
                String ln = rs.getString("lastName");
                String names = name + " " + fn + " " + ln;
                org_list.add(names);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
        }
        return org_list;
    }
}