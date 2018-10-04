package Model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class AssetDAO {
    //Retrieves Data from Database
    public static List<Asset> getOfficerEmpList(String user) {
        List<Asset> of = new ArrayList<Asset>();
        try {
            String sql = "SELECT * FROM asset where username=" + user + "";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("id");
                String usrnm = rs.getString("username");
                String ctgry = rs.getString("category");
                String brnd = rs.getString("brand");
                String mdl = rs.getString("model");
                String asstnmber = rs.getString("assetNum");
                String srlnm = rs.getString("serialNum");
                String wrrntystrtdt = rs.getString("warrantyStartDate");
                String wrrntyenddt = rs.getString("warrantyEndDate");
                String cmmnts = rs.getString("comments");
                String stts = rs.getString("status");
                Asset officer = new Asset(id, usrnm, ctgry, brnd, mdl, asstnmber, srlnm, wrrntystrtdt, wrrntyenddt, cmmnts, stts);
                of.add(officer);
            }
            stmt.execute(sql);
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
        }
        return of;
    }

    public static List<Asset> getOfficerEmpList() {
        List<Asset> of = new ArrayList<Asset>();
        try {
            String sql = "SELECT * FROM asset ORDER BY 2";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("id");
                String stdn = rs.getString("username");
                String fn = rs.getString("category");
                String ln = rs.getString("brand");
                String mn = rs.getString("model");
                String col = rs.getString("assetNum");
                String org = rs.getString("serialNum");
                String sw = rs.getString("warrantyStartDate");
                String ew = rs.getString("warrantyEndDate");
                String orgP = rs.getString("comments");
                String st = rs.getString("status");
                Asset officer = new Asset(id, stdn, fn, ln, mn, col, org, sw, ew, orgP, st);
                of.add(officer);
            }
            stmt.execute(sql);
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
        }
        return of;
    }
    public static List<Asset> getUnassignedEmpList() {
        List<Asset> of = new ArrayList<Asset>();
        System.out.println("RS1");
        try {
            String sql = "SELECT * FROM asset where status = 'Unassigned'  ORDER BY 2";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                
                String id = rs.getString("id");
                String stdn = rs.getString("username");
                String fn = rs.getString("category");
                String ln = rs.getString("brand");
                String mn = rs.getString("model");
                String col = rs.getString("assetNum");
                String org = rs.getString("serialNum");
                String sw = rs.getString("warrantyStartDate");
                String ew = rs.getString("warrantyEndDate");
                String orgP = rs.getString("comments");
                String st = rs.getString("status");
                Asset officer = new Asset(id, stdn, fn, ln, mn, col, org, sw, ew, orgP, st);
                of.add(officer);
            }
            stmt.execute(sql);
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
        }
        return of;
    }
    public static List<Asset> getMyAssetsList(String user) {
        List<Asset> of = new ArrayList<Asset>();
        System.out.println("RS1");
        try {
            String sql = "SELECT * FROM asset where username = '" + user +"'  ORDER BY 2";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                
                String id = rs.getString("id");
                String stdn = rs.getString("username");
                String fn = rs.getString("category");
                String ln = rs.getString("brand");
                String mn = rs.getString("model");
                String col = rs.getString("assetNum");
                String org = rs.getString("serialNum");
                String sw = rs.getString("warrantyStartDate");
                String ew = rs.getString("warrantyEndDate");
                String orgP = rs.getString("comments");
                String st = rs.getString("status");
                Asset officer = new Asset(id, stdn, fn, ln, mn, col, org, sw, ew, orgP, st);
                of.add(officer);
            }
            stmt.execute(sql);
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
        }
        return of;
    }
    public static List<Asset> getDisposalEmpList() {
        List<Asset> of = new ArrayList<Asset>();
        System.out.println("RS1");
        try {
            String sql = "SELECT * FROM asset where status = 'For Disposal'  ORDER BY 2";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                
                String id = rs.getString("id");
                String stdn = rs.getString("username");
                String fn = rs.getString("category");
                String ln = rs.getString("brand");
                String mn = rs.getString("model");
                String col = rs.getString("assetNum");
                String org = rs.getString("serialNum");
                String sw = rs.getString("warrantyStartDate");
                String ew = rs.getString("warrantyEndDate");
                String orgP = rs.getString("comments");
                String st = rs.getString("status");
                Asset officer = new Asset(id, stdn, fn, ln, mn, col, org, sw, ew, orgP, st);
                of.add(officer);
            }
            stmt.execute(sql);
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
        }
        return of;
    }
    public static List<Asset> getOfficerUniwideList() {
        List<Asset> of = new ArrayList<Asset>();
        try {
            String sql = "SELECT * FROM asset ORDER BY 2";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("id");
                String usrnm = rs.getString("username");
                String ctgry = rs.getString("category");
                String brnd = rs.getString("brand");
                String mdl = rs.getString("model");
                String asstnmber = rs.getString("assetNum");
                String srlnm = rs.getString("serialNum");
                String wrrntystrtdt = rs.getString("warrantyStartDate");
                String wrrntyenddt = rs.getString("warrantyEndDate");
                String cmmnts = rs.getString("comments");
                String stts = rs.getString("status");
                Asset officer = new Asset(id, usrnm, ctgry, brnd, mdl, asstnmber, srlnm, wrrntystrtdt, wrrntyenddt, cmmnts, stts);
                of.add(officer);
            }
            stmt.execute(sql);
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
        }
        return of;
    }
    public static List<Asset> getOfficerUniwideList(String orgU) {
        List<Asset> of = new ArrayList<Asset>();
        try {
            String sql = "SELECT * FROM asset where username = '" + orgU + "' ORDER BY 2";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("id");
                String stdn = rs.getString("username");
                String fn = rs.getString("category");
                String ln = rs.getString("brand");
                String mn = rs.getString("model");
                String col = rs.getString("assetNum");
                String org = rs.getString("serialNum");
                String sw = rs.getString("warrantyStartDate");
                String ew = rs.getString("warrantyEndDate");
                String orgP = rs.getString("comments");
                String st = rs.getString("status");
                Asset officer = new Asset(id, stdn, fn, ln, mn, col, org, sw, ew, orgP, st);
                of.add(officer);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return of;
    }

    public static List<Asset> getOfficerEmployeeList(String owner) {
        List<Asset> of = new ArrayList<Asset>();
        try {
            String sql = "SELECT * FROM asset WHERE username = '" + owner + "";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("id");
                String stdn = rs.getString("username");
                String fn = rs.getString("category");
                String ln = rs.getString("brand");
                String mn = rs.getString("model");
                String col = rs.getString("assetNum");
                String org = rs.getString("serialNum");
                String sw = rs.getString("warrantyStartDate");
                String ew = rs.getString("warrantyEndDate");
                String orgP = rs.getString("comments");
                String st = rs.getString("status");
                Asset officer = new Asset(id, stdn, fn, ln, mn, col, org, sw, ew, orgP, st);
                of.add(officer);
            }
            stmt.execute(sql);
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
        }
        return of;
    }
}