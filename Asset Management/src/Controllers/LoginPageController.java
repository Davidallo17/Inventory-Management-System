package Controllers;

import Model.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.commons.codec.digest.DigestUtils;
import org.controlsfx.control.Notifications;

public class LoginPageController implements Initializable {

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;


    static String userinput;
    @FXML
    private JFXTextField username;

    
    @FXML
    private void makeLogin(ActionEvent event) throws IOException {
        System.out.println("DO IT");
        if (isValidCredentials().equals("employee")) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/EmployeePage.fxml"));
            Parent root = (Parent) loader.load();
            Notifications notif = Notifications.create()
                        .title("Success!")
                        .darkStyle()
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER)
                        .text("Employee Login Successful");
                notif.show();
                
            EmployeePageController personController = loader.getController();
            personController.setName(username.getText());
            
            login.getScene().setRoot(root);

        } else if(isValidCredentials().equals("admin")) {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/administratorPage.fxml"));
            Scene home_page_scene = new Scene(root);
            Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

            Notifications notif = Notifications.create()
                        .title("Success!")
                        .darkStyle()
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER)
                        .text("Admin Login Successful");
                notif.show();
            app_stage.hide();
            app_stage.setScene(home_page_scene);
            app_stage.show();
            app_stage.setMaximized(true);

        } else {
            System.out.println(username);
            System.out.println(password);
            username.clear();
            password.clear();
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("Title of alert");
            a.setHeaderText(null);
            a.initStyle(StageStyle.UNDECORATED);
            a.setContentText("Wrong Credentials");
            a.showAndWait();
           
        }

    }

    private String isValidCredentials() {
        String let_in = "";

        Connection con = DBConnection.getConnection();
        Statement stmt = null;
        try {

            System.out.println("Opened database successfully");
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM employee WHERE username= " + "'" + username.getText() + "'"
                                              + " AND password= " + "'" + DigestUtils.md5Hex(DigestUtils.sha256Hex(password.getText())) + "'AND status = 'Active'");
            while ( rs.next() ) {
                if (rs.getString("username") != null && rs.getString("password") != null) {
                    if (rs.getString("role").equals("Admin")) {
                        String  usernames = rs.getString("username");
                        System.out.println( "username = " + usernames );
                        String passwords = rs.getString("password");
                        System.out.println("password = " +  passwords);
                       
                        let_in = "admin";
                    } else if (rs.getString("role").equals("Employee")) {
                        String  usernames = rs.getString("username");
                        System.out.println( "username = " + usernames );
                        String passwords = rs.getString("password");
                        System.out.println("password = " +  passwords);
                       
                        let_in = "employee";
                    }
                } else {
                    let_in = "fail";
                }

            }
            rs.close();
            stmt.close();
            con.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return let_in;
    }


    @FXML
    void enterUsername(ActionEvent event) {

    }

    @FXML
    void enterPassword(ActionEvent event) {

    }
    @FXML
    void CreateTable() {


    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        
    }

}
