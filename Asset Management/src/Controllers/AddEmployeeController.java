package Controllers;

import Model.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.codec.digest.DigestUtils;
import org.controlsfx.dialog.Wizard;

public class AddEmployeeController implements Initializable {

    private static Statement stmt;

    @FXML
    private JFXTextField empUsername;

    @FXML
    private JFXTextField empFirstname;

    @FXML
    private JFXTextField empLastname;

    double x, y;

    @FXML
    private JFXButton uniwideSaveBtn;

    @FXML
    private JFXButton UniwideCancel;


    @FXML
    void saveUniwideClicked(ActionEvent event) {
        String on = empUsername.getText();
        String ye = empLastname.getText();
        String ad = empFirstname.getText();
        String pw = DigestUtils.md5Hex(DigestUtils.sha256Hex(empUsername.getText()));
         try{
        //Check if there are duplicates
        String result="good";
        Connection con = DBConnection.getConnection();
        Statement stmt = con.createStatement() ;
        ResultSet resultset = stmt.executeQuery("SELECT * FROM employee");
        while(resultset.next()){
        if(resultset.getString("username").equalsIgnoreCase(on)){
             result = "no";  
              Alert a = new Alert(AlertType.ERROR);
                 a.setTitle("ERROR!");
                a.setHeaderText(null);
                a.initStyle(StageStyle.UNIFIED);
                a.setContentText("Username already exist.  \nCannot enter username: " + on);
                a.showAndWait();  
                
        }
    }
       
   
    if(result.equals("good")){
        PreparedStatement ps;
        ps = con.prepareStatement(" INSERT INTO employee (username, firstname, lastname, password, role, status) VALUES(?,?,?,?,?,?)");
        if(on.equalsIgnoreCase("admin") || on.equalsIgnoreCase("administrator")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Oops!");
            alert.setHeaderText(null);
            alert.initStyle(StageStyle.UNIFIED);
            alert.setContentText("The username can't be named admin.");
            alert.showAndWait();
        } else {
            ps.setString(1, on);
        }
        ps.setString(2, ad);
        ps.setString(3, ye);
        ps.setString(4, pw);
        ps.setString(5, "Employee");
        ps.setString(6, "Active");
        ps.execute();
        
        Alert a = new Alert(AlertType.INFORMATION);
        a.setTitle("Success!");
        a.setHeaderText(null);
        a.initStyle(StageStyle.UNIFIED);
        a.setContentText("Successfully added an Employee\n The default password is the username");
        a.showAndWait();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
        catch(SQLException e){
            System.out.print(e);
                     
        }
    
       
    }


    @FXML
    void cancelBtnClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML
    void windowDragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void windowPressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }
}
