package Controllers;

import Model.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.commons.codec.digest.DigestUtils;
import org.controlsfx.control.Notifications;

public class AddAdminController implements Initializable {

    private static Statement stmt;

    @FXML
    private JFXTextField usernameEnterTextfield;

    @FXML
    private JFXTextField firstnameEnterTextfield;

    @FXML
    private JFXPasswordField passwordEnterTextfield;

    @FXML
    private JFXTextField lastnameEnterTextfield;

    double x, y;

    @FXML
    private JFXButton saveAddAdminButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    void saveAddAdminClicked(ActionEvent event) throws SQLException {
        String on = usernameEnterTextfield.getText();
        String ye = lastnameEnterTextfield.getText();
        String ad = firstnameEnterTextfield.getText();
        String pw = DigestUtils.md5Hex(DigestUtils.sha256Hex(usernameEnterTextfield.getText()));

       
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
                
        }}
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
            ps.setString(5, "Admin");
            ps.setString(6, "Active");
            ps.execute();
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
    }
        }   catch(SQLException e){
            System.out.print(e);       
        }finally{
              Image img = new Image("Images/icons8_Apply_48px.png");
                Notifications notif = Notifications.create()
                        .title("Delete Employee Complete")
                        .darkStyle()
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER)
                        .text("    "+ usernameEnterTextfield.getText()+" Successfully Added");
                notif.show();
         }
    }
    

    @FXML
    void cancelButtonClicked(ActionEvent event) {
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
