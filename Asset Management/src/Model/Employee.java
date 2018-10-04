package Model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import org.apache.commons.codec.digest.DigestUtils;

public class Employee {
    private String Employee_id;
    private SimpleStringProperty username;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private String password;
    private SimpleStringProperty role;
    private SimpleStringProperty status;

    public Employee(String employee_id, String username, String firstName, String lastName, String password, String role, String status) {
        this.Employee_id = employee_id;
        this.username = new SimpleStringProperty(username);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.password = password;
        this.role = new SimpleStringProperty(role);
        this.status = new SimpleStringProperty(status);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(SimpleStringProperty status) {
        this.status = status;
    }

    public void getfromUsername() throws SQLException {
        Connection con = DBConnection.getConnection();
        Statement stmt = con.createStatement();
        String sql = "select id from employee";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
            this.Employee_id = rs.getString("username");
        }
        rs.close();
        stmt.close();
        con.close();
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(SimpleStringProperty role) {
        this.role = role;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(SimpleStringProperty username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(SimpleStringProperty firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(SimpleStringProperty lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.md5Hex(DigestUtils.sha256Hex(password));
    }


    @Override
    public String toString() {
        return this.username.get();
    }

    public String getEmployee_id() {
        return Employee_id;
    }
}
