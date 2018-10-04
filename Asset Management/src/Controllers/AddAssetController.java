package Controllers;

import Model.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.controlsfx.control.Notifications;


public class AddAssetController implements Initializable {

    private static Statement stmt;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXTextArea comments;

    @FXML
    private JFXTextField assetNum;

    @FXML
    private JFXTextField model;

    @FXML
    private JFXTextField serialNum;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private JFXComboBox<String> username;

    @FXML
    private JFXComboBox<String> status;

    @FXML
    private JFXComboBox<String> category;
    @FXML
    private JFXComboBox<String> brand;


    double x, y;
    private Connection conn;

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

    @FXML
    void saveBtnClicked(ActionEvent event) throws SQLException, UnsupportedTemporalTypeException {
        String add_username = username.getValue();
        String add_category = category.getValue();
        String add_brand = brand.getValue();
        String add_model = model.getText();
        String add_assetNumber = assetNum.getText();
        String add_serialNumber = serialNum.getText();
        String add_comments = comments.getText();
        String add_status = status.getValue();

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps;
            ps = con.prepareStatement("INSERT INTO asset(username, category, brand, model, assetNum, serialNum, warrantyStartDate, warrantyEndDate, comments,status) VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, add_username);
            ps.setString(2, add_category);
            ps.setString(3, add_brand);
            ps.setString(4, add_model);

            if(add_username.equals("admin")) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Warning");
                a.initStyle(StageStyle.UNDECORATED);
                a.setHeaderText(null);
                a.setContentText("Unable to add asset to admin");
                a.showAndWait();
            } else {
                ps.setString(1, add_username);
            }

            if(add_assetNumber == null) {
                ps.setString(5, null);
            } else {
                ps.setString(5, add_assetNumber);
            }
            ps.setString(6, add_serialNumber);

            if(startDate.getValue() == null) {
                ps.setString(7, null);
            } else {
                ps.setString(7, ((TextField)startDate.getEditor()).getText());
            }

            if(endDate.getValue() == null) {
                ps.setString(8, null);
            } else {
                ps.setString(8, ((TextField)endDate.getEditor()).getText());
            }
            ps.setString(9, add_comments);
            ps.setString(10, add_status);
            ps.execute();

          
        }finally{ Notifications notif = Notifications.create()
                        .title("Success!")
                        .darkStyle()
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER)
                        .text("Asset Successfully Added");
                notif.show();}
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    void cancelBtnClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    ObservableList<String> owner = FXCollections.observableArrayList();
    public void getEmployeeNameComboBox() {
        Connection con = DBConnection.getConnection();
        try {
            String of = new String();
            String sql = "SELECT username FROM employee ORDER BY username ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                owner.add(rs.getString("username"));
                username.setItems(owner);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    ObservableList<String> brandtype = FXCollections.observableArrayList();
    public void getBrandCombobox() {
        Connection con = DBConnection.getConnection();
        try {
            String of = new String();
            String sql = "SELECT type FROM brand ORDER BY type";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                brandtype.add(rs.getString("type"));
                brand.setItems(brandtype);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    ObservableList<String> categorytype = FXCollections.observableArrayList();
    public void getCategoryCombobox() {
        Connection con = DBConnection.getConnection();
        try {
            String of = new String();
            String sql = "SELECT type FROM category ORDER BY type";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                categorytype.add(rs.getString("type"));
                category.setItems(categorytype);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getBrandCombobox();
        getCategoryCombobox();
       

        ObservableList<String> statuse = FXCollections.observableArrayList(
                                             "Assigned",
                                             "Unassigned",
                                             "For Disposal");
        status.setItems(statuse);
        getEmployeeNameComboBox();

        username.setMaxHeight(30);
        LocalDate localDate = LocalDate.now();
        startDate.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LLLL-dd");
            String formattedString = localDate.format(formatter);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return formatter.format(date);
                } else {
                    return null;
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, formatter);
                } else {
                    return null;
                }
            }
        });
        endDate.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LLLL-dd");
            String formattedString = localDate.format(formatter);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return formatter.format(date);
                } else {
                    return null;
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, formatter);
                } else {
                    return null;
                }
            }
        });
    }
}
