package Controllers;

import static Controllers.AdministratorPageController.tempName;
import Model.Asset;
import Model.AssetDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import Model.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.commons.codec.digest.DigestUtils;
import org.controlsfx.control.Notifications;


public class EmployeePageController implements Initializable {
    @FXML
    private JFXTextField modelEmployeeBox, SearchbarEmp,changeFirstname,changeLastname,changeUsername;

    @FXML
    private JFXButton updateAssetEmployeeButton;

    @FXML
    private JFXButton updateGISSaccountbtn,DisposalBtn,unassignedBtn,myAssetsBtn;

    @FXML
    private JFXComboBox<String> ownerEmployeeBox;

    @FXML
    private JFXPasswordField changePassword;
   

    @FXML
    private JFXComboBox<String> brandEmployeeBox;

    @FXML
    private JFXComboBox<String> statusEmployeeBox;

    @FXML
    private JFXTextField startwarrantyEditEmp,firstnameEmp,lastnameEmp;

    @FXML
    private JFXTextField endwarrantyEditEmp;

    @FXML
    private JFXTextField assetNumberEmployeeBox;

    @FXML
    private TableColumn<Asset, String> orgPositionEmp;

    @FXML
    private TableColumn<Asset, String> show_EmployeeEndwarranty, show_EmployeeSerialNum, show_EmployeeBrand, show_EmployeeAssetNum, show_EmployeeStatus, show_EmployeeComments, show_EmployeeStartwarranty;

    @FXML
    public Label changeName;

    @FXML
    private TableColumn<Asset, String> show_EmployeeOwner;


    @FXML
    private JFXButton refreshEmployeeButton;

    @FXML
    private JFXButton LogOut;

    @FXML
    private TableColumn<Asset, String> show_EmployeeModel;

    @FXML
    private JFXTextField commentsEmployeeBox;

    @FXML
    private JFXTextField serialNumEmployeeBox;

    @FXML
    private TableView<Asset> tableviewEmployee;

    @FXML
    private JFXComboBox<String> categoryEmployeeBox;

    @FXML
    private TableColumn<Asset, String> show_EmployeeCategory;
    @FXML
    private TableColumn<Asset, String> startwarrantyEmp;
    @FXML
    private TableColumn<Asset, String> endwarrantyEmp;
    private ObservableList<Asset> tableviewUniwideList;

    static String tempEmpId;
    static String tempEmpName,tempBrand,tempCategory;
    
    
PreparedStatement preparedStatement = null;

    @FXML
    void LogOutClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/LoginPage.fxml"));
        Scene adminPageScene = new Scene(root);

        Stage adminStage;
        adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        adminStage.setTitle("GISS Inventory Management System - Login Page");
        adminStage.hide();
        adminStage.setScene(adminPageScene);
        adminStage.show();
    }
    LoginPageController sa = new LoginPageController();


    @FXML
    void refreshEmployeeButtonClicked(ActionEvent event) {
        ownerEmployeeBox.setValue(" ");
        categoryEmployeeBox.setValue(" ");
        brandEmployeeBox.setValue(" ");
        modelEmployeeBox.setText(" ");
        serialNumEmployeeBox.setText(" ");
        commentsEmployeeBox.setText(" ");
        assetNumberEmployeeBox.setText(" ");
        startwarrantyEditEmp.setText(" ");
        endwarrantyEditEmp.setText(" ");
        statusEmployeeBox.setValue(" ");
        getOfficerUniwide();
    }



    public void getOfficerUniwide() {
        //Retrieves Data from the Database
        List<Asset> classOfficer = AssetDAO.getOfficerEmpList();
        tableviewUniwideList = FXCollections.observableArrayList(classOfficer);
        tableviewEmployee.setItems(tableviewUniwideList);
        //Filters Data from the Table Thru Search Bar
        FilteredList<Asset> filteredData = new FilteredList<>(tableviewUniwideList, e -> true);
        SearchbarEmp.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super Asset>) al -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (al.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (al.getCategory().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (al.getBrand().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (al.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (al.getModelString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (al.getWarrantyEndString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (al.getWarrantyStartString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (al.getCommentsString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (al.getSerialNumString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (al.getAssetNumString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Asset> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableviewEmployee.comparatorProperty());
        tableviewEmployee.setItems(sortedData);
    }
    public void GetUnassigned() {
    //Retrieves Data from the Database
    System.out.println(tableviewUniwideList);
        List<Asset> classOfficers = AssetDAO.getUnassignedEmpList();
        
        
        tableviewUniwideList = FXCollections.observableArrayList(classOfficers);
        System.out.println(tableviewUniwideList);
        tableviewEmployee.setItems(tableviewUniwideList);
}
    
    public void getDisposalEmpList() {
    //Retrieves Data from the Database
    System.out.println(tableviewUniwideList);
        List<Asset> classOfficers = AssetDAO.getDisposalEmpList();
        tableviewUniwideList = FXCollections.observableArrayList(classOfficers);
        System.out.println(tableviewUniwideList);
        tableviewEmployee.setItems(tableviewUniwideList);
}
    
        public void GetMyAssets() {
    //Retrieves Data from the Database
    System.out.println(tableviewUniwideList);
        List<Asset> classOfficers = AssetDAO.getMyAssetsList(changeName.getText());
        tableviewUniwideList = FXCollections.observableArrayList(classOfficers);
        System.out.println(tableviewUniwideList);
        tableviewEmployee.setItems(tableviewUniwideList);
}
    
    @FXML
    public void updateAssetEmployeeButtonClicked(ActionEvent event) throws SQLException {
        
       Image img = new Image("Images/icons8_Edit_File_50px.png");
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Update Asset Confirmation");
                a.setHeaderText("Owner: "+ tempEmpName +"\nCategory: " + tempCategory +"\nBrand: " + tempBrand);
                a.setGraphic(new ImageView(img));
                a.initStyle(StageStyle.UNIFIED);
                a.setContentText("Do you want to Update this Asset?");
                
                Optional <ButtonType> action = a.showAndWait();
                if(action.get() == ButtonType.OK){
        Connection con = DBConnection.getConnection();
        String query = "update asset set username=?, category=?, brand=?, model=?, assetNum=?, serialNum=?,warrantyStartDate=?,warrantyEndDate=?, comments=?, status=? where id='" + tempId + "'";
        PreparedStatement preparedStatement = null;
        if (ownerEmployeeBox.getSelectionModel().isEmpty() && categoryEmployeeBox.getSelectionModel().isEmpty() && brandEmployeeBox.getSelectionModel().isEmpty() && modelEmployeeBox.getText().trim().isEmpty() && assetNumberEmployeeBox.getText().trim().isEmpty()
                && serialNumEmployeeBox.getText().trim().isEmpty() && startwarrantyEditEmp.getText().trim().isEmpty() && endwarrantyEditEmp.getText().trim().isEmpty() && commentsEmployeeBox.getText().trim().isEmpty() && statusEmployeeBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Please click the row to be updated before clicking update button.");
            alert.showAndWait();
        }else if(ownerEmployeeBox.isDisabled() && categoryEmployeeBox.isDisabled() && brandEmployeeBox.isDisabled() && modelEmployeeBox.isDisabled() && assetNumberEmployeeBox.isDisabled()
                && serialNumEmployeeBox.isDisabled() && startwarrantyEditEmp.isDisabled() && endwarrantyEditEmp.isDisabled() && commentsEmployeeBox.isDisabled() && statusEmployeeBox.isDisabled()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Cannot Update Particular Item.");
            alert.showAndWait();
        }
        else {
            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, ownerEmployeeBox.getValue().toString());
                preparedStatement.setString(2, categoryEmployeeBox.getValue().toString());
                preparedStatement.setString(3, brandEmployeeBox.getValue().toString());
                preparedStatement.setString(4, modelEmployeeBox.getText());
                preparedStatement.setString(5, assetNumberEmployeeBox.getText());
                preparedStatement.setString(6, serialNumEmployeeBox.getText());
                preparedStatement.setString(7, startwarrantyEditEmp.getText());
                preparedStatement.setString(8, endwarrantyEditEmp.getText());
                preparedStatement.setString(9, commentsEmployeeBox.getText());
                preparedStatement.setString(10, statusEmployeeBox.getValue().toString());
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                Image imge = new Image("Images/icons8_Checked_48px_1.png");
                 Notifications notif = Notifications.create()
                        .title("Success!")
                        .darkStyle()
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER)
                        .graphic(new ImageView(imge))
                        .text("Asset Successfully Updated");
                notif.show();
                con.close();
                }
            }
        }
    }

    public void connectOfficer() {
        tableviewUniwideList.clear();
        List<Asset> lists = AssetDAO.getOfficerUniwideList(changeName.getText());
        ObservableList<Asset> olist = FXCollections.observableArrayList(lists);
        tableviewEmployee.setItems(olist);
    }
    public void setName(String name) {
        changeName.setText(name);
         String username = "";
         String firstname = "";
         String lastname = "";
         String password = "";
         
        Connection con = DBConnection.getConnection();
        Statement stmt = null;
        
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username, firstname, lastname, password FROM employee WHERE username='"+ changeName.getText()+"'");
            while ( rs.next() ) {
                    username = rs.getString("username");
                    firstname = rs.getString("firstname");
                    lastname = rs.getString("lastname");
                    password = rs.getString("password"); 
                    System.out.println(password);
            }
            changeUsername.setText(username);
            changePassword.setText(password);
            changeFirstname.setText(firstname);
            changeLastname.setText(lastname);
            
            
            System.out.println(firstname);
            System.out.println(lastname);
            System.out.println(password);
            
            System.out.println("Firstname "+changeFirstname.getText());
            System.out.println("Lastname "+changeLastname.getText());
            System.out.println("Username "+changeUsername.getText());
            System.out.println("Name " +changeName.getText());
            System.out.println("PW " +changePassword.getText());
            
            rs.close();
            stmt.close();
            con.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

    }
 
     @FXML
    public void updateGISSaccount (ActionEvent event) throws SQLException {
        System.out.println("Change Name: " +changeName.getText());
        Image img = new Image("Images/icons8_Registration_48px.png");
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Update Employee Confirmation");
                a.setHeaderText("Name: "+ changeName.getText() );
                a.setGraphic(new ImageView(img));
                a.initStyle(StageStyle.UNIFIED);
                a.setContentText("Are you sure you want to update?");
                
                Optional <ButtonType> action = a.showAndWait();
                if(action.get() == ButtonType.OK){
        Connection con = DBConnection.getConnection();
        String query = "update employee set username=?, firstName=?, lastName=?,password=? where username='" + changeName.getText() + "'";

       
        if (changeUsername.getText().trim().isEmpty() && changeFirstname.getText().trim().isEmpty() && changeLastname.getText().trim().isEmpty() && changePassword.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Oops!");
            alert.setHeaderText(null);
            alert.setContentText("Please provide the following information.");
            alert.showAndWait();
        } else {
            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, changeUsername.getText());
                preparedStatement.setString(2, changeFirstname.getText());
                preparedStatement.setString(3, changeLastname.getText());
                
                
                 try{
        //Check if there are duplicates
        System.out.println("Part 2 - Update ");
        Statement stmt = con.createStatement() ;
        ResultSet resultset = stmt.executeQuery("SELECT * FROM employee where username='" + changeName.getText() +"'");
        while(resultset.next()){
            System.out.println(resultset.getString("password") + " VS "+ changePassword.getText());
        if(resultset.getString("password").equals(changePassword.getText())){
             preparedStatement.setString(4, changePassword.getText().toString());
             
        }else {
            preparedStatement.setString(4, DigestUtils.md5Hex(DigestUtils.sha256Hex(changePassword.getText().toString())));  
        }
        }
    }catch(SQLException ae){ System.out.print("Exception: " +ae);}

                preparedStatement.execute();
                preparedStatement.close();
                
            } catch (SQLException e) {
                System.out.println(e);

            } finally {
                
                Notifications notif = Notifications.create()
                        .title("Success!")
                        .darkStyle()
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER)
                        .text("Employee Successfully Updated");
                notif.show();
                con.close();
            }
        }
            }
        
    }
    static String tempId;
    private void setTableEmp() {
        show_EmployeeOwner.setCellValueFactory(new PropertyValueFactory<Asset, String>("username"));
        show_EmployeeCategory.setCellValueFactory(new PropertyValueFactory<Asset, String>("category"));
        show_EmployeeBrand.setCellValueFactory(new PropertyValueFactory<Asset, String>("brand"));
        show_EmployeeModel.setCellValueFactory(new PropertyValueFactory<Asset, String>("model"));
        show_EmployeeAssetNum.setCellValueFactory(new PropertyValueFactory<Asset, String>("assetNum"));
        show_EmployeeSerialNum.setCellValueFactory(new PropertyValueFactory<Asset, String>("serialNum"));
        show_EmployeeStartwarranty.setCellValueFactory(new PropertyValueFactory<Asset, String>("warrantyStartDate"));
        show_EmployeeEndwarranty.setCellValueFactory(new PropertyValueFactory<Asset, String>("warrantyEndDate"));
        show_EmployeeComments.setCellValueFactory(new PropertyValueFactory<Asset, String>("comments"));
        show_EmployeeStatus.setCellValueFactory(new PropertyValueFactory<Asset, String>("status"));
        // SHOW DATA FROM TABLE TO EDIT ON CLICK ROW
        tableviewEmployee.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) throws NullPointerException {
                tempId = tableviewEmployee.getSelectionModel().getSelectedItem().getId();
                tempEmpName = tableviewEmployee.getSelectionModel().getSelectedItem().getUsername();
                tempCategory = tableviewEmployee.getSelectionModel().getSelectedItem().getCategory();
                tempBrand = tableviewEmployee.getSelectionModel().getSelectedItem().getBrand();
                
                ownerEmployeeBox.setValue(tableviewEmployee.getSelectionModel().getSelectedItem().getUsername());
                categoryEmployeeBox.setValue(tableviewEmployee.getSelectionModel().getSelectedItem().getCategory());
                brandEmployeeBox.setValue(tableviewEmployee.getSelectionModel().getSelectedItem().getBrand());
                modelEmployeeBox.setText(tableviewEmployee.getSelectionModel().getSelectedItem().getModel());
                assetNumberEmployeeBox.setText(tableviewEmployee.getSelectionModel().getSelectedItem().getAssetNum());
                serialNumEmployeeBox.setText(tableviewEmployee.getSelectionModel().getSelectedItem().getSerialNum());
                startwarrantyEditEmp.setText(tableviewEmployee.getSelectionModel().getSelectedItem().getWarrantyStartDate());
                endwarrantyEditEmp.setText(tableviewEmployee.getSelectionModel().getSelectedItem().getWarrantyEndDate());
                commentsEmployeeBox.setText(tableviewEmployee.getSelectionModel().getSelectedItem().getComments());
                statusEmployeeBox.setValue(tableviewEmployee.getSelectionModel().getSelectedItem().getStatus());

                if(ownerEmployeeBox.getValue().equals(changeName.getText())  || (!ownerEmployeeBox.getValue().equals(changeName.getText()) && statusEmployeeBox.getValue().equals("Unassigned"))) {
                    categoryEmployeeBox.setDisable(true);
                    brandEmployeeBox.setDisable(true);
                    modelEmployeeBox.setDisable(true);
                    assetNumberEmployeeBox.setDisable(false);
                    serialNumEmployeeBox.setDisable(false);
                    startwarrantyEditEmp.setDisable(false);
                    endwarrantyEditEmp.setDisable(false);
                    commentsEmployeeBox.setDisable(false);
                    statusEmployeeBox.setDisable(false);
                    ownerEmployeeBox.setDisable(false);

                } else {
                    categoryEmployeeBox.setDisable(true);
                    brandEmployeeBox.setDisable(true);
                    modelEmployeeBox.setDisable(true);
                    assetNumberEmployeeBox.setDisable(true);
                    serialNumEmployeeBox.setDisable(true);
                    startwarrantyEditEmp.setDisable(true);
                    endwarrantyEditEmp.setDisable(true);
                    commentsEmployeeBox.setDisable(true);
                    statusEmployeeBox.setDisable(true);
                    ownerEmployeeBox.setDisable(true);

                }
            }

        });
    }
    ObservableList<String> owner = FXCollections.observableArrayList();

    public void getOrgNameCombobox() {
        try {

            String sql = "SELECT username FROM employee";
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                owner.add(rs.getString("username"));
                ownerEmployeeBox.setItems(owner);

            }

            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
  
    public void start(Stage stage) throws Exception{
      Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/EmployeePageController.fxml"));
        stage.getIcons().add(new Image("/Images/icons8_Edit_Property_50px.png")); 
        stage.setTitle("GISS - Inventory Management System");
        
       // stage.setResizable(false);
        stage.setScene(new Scene(root,1300,708));
    stage.show();
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        setTableEmp();
        getOfficerUniwide();
        // connectOfficer();
        getOrgNameCombobox();
        ObservableList<String> category = FXCollections.observableArrayList(
                                              "Desktop",
                                              "hard Drive",
                                              "IP Phone",
                                              "Keyboard",
                                              "Laptop",
                                              "Monitor",
                                              "Router",
                                              "Server",
                                              "Switch");
        categoryEmployeeBox.setItems(category);

        ObservableList<String> brands = FXCollections.observableArrayList(
                                            "3Com",
                                            "A4Tech",
                                            "Acer",
                                            "AOC",
                                            "Apple",
                                            "Asus",
                                            "CDR King",
                                            "Cisco",
                                            "Dell",
                                            "Fujitsu",
                                            "Gigabyte",
                                            "Hitachi",
                                            "Lenovo",
                                            "LG",
                                            "MSI",
                                            "Nortel",
                                            "Samsung",
                                            "Seagate",
                                            "Viewsonic"
                                        );
        brandEmployeeBox.setItems(brands);

        ObservableList<String> statuse = FXCollections.observableArrayList(
                                             "Assigned",
                                             "Unassigned",
                                             "For Disposal"
                                         );
        statusEmployeeBox.setItems(statuse);
    }

}