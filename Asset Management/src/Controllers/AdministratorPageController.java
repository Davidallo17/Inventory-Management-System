package Controllers;

import Model.DBConnection;
import Model.Asset;
import Model.AssetDAO;
import Model.Brand;
import Model.CategoryDAO;
import Model.Employee;
import Model.EmployeeDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Predicate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.action.Action;
import org.controlsfx.control.Notifications;


public class AdministratorPageController implements Initializable {

    @FXML
    private TableView<Asset> tableviewAssets;

    @FXML
    private TableView<Asset> tableviewServer;
    @FXML
    private TableView<Employee> tableviewUsers;
    private ObservableList<Asset> tableviewUniwideList;
    private ObservableList<Employee> tableviewUniwideListUser;
    private ObservableList<Asset> tableviewUniwideListServer;

    @FXML
    private TableColumn<Asset, String> show_assetTagRow, show_categoryRow;

    @FXML
    private TableColumn<Asset, String> show_ownerRow;

    @FXML
    private TableColumn<Asset, String> show_startwarrantyuniwide;

    @FXML
    private TableColumn<Asset, String> show_endwarrantyuniwide;

    @FXML
    private TableColumn<Asset, String> show_modelRow;

    @FXML
    private TableColumn<Asset, String> show_brandRow;

    @FXML
    private TableColumn<Asset, String> show_commentsRow;

    @FXML
    private TableColumn<Asset, String> show_serialNumRow;

    @FXML
    private TableColumn<Asset, String> show_statusRow;

    @FXML
    private TableColumn<Employee, String> usernameTable, firstnameTable, lastnameTable, roleTable, statusTable;

    @FXML
    private TableColumn<Asset, String> categoryServer;

    @FXML
    private TableColumn<Asset, String> brandServer;

    @FXML
    private TableColumn<Asset, String> modelServer;

    @FXML
    private TableColumn<Asset, String> assetNumServer;

    @FXML
    private TableColumn<Asset, String> serialNumServer;

    @FXML
    private TableColumn<Asset, String> ExpServServer;

    @FXML
    private TableColumn<Asset, String> commentsServer;

    @FXML
    private TableColumn<Asset, String> statusServer;


    @FXML
    private JFXTextField serialNumServerEdit, searchAsset;

    @FXML
    private JFXListView brandList;
    @FXML
    private JFXListView categoryList;

    @FXML
    private ImageView Searchpic;

    @FXML
    private Button deleteOrgUniwide;

    @FXML
    private Button refreshButton;

    @FXML
    private JFXButton deleteServer;

    @FXML
    private JFXButton addServer;

    @FXML
    private JFXButton  updateServer;

    @FXML
    private JFXComboBox roleBox, statusBox;

    @FXML
    private JFXComboBox categoryBox;

    @FXML
    private JFXComboBox brandBox;

    @FXML
    private JFXTextField modelBox, searchAssetUser, addBrandTextfield, addCategoryTextfield;

    @FXML
    private JFXButton deleteAssetButton, addBrand, deleteBrand, addCategory, deleteCategory;

    @FXML
    private JFXTextField assetTagBox;

    @FXML
    private JFXTextField serialNumberBox, commentBox, firstnameTextfield, startwarrantyBox, endwarrantyBox;

    @FXML
    private JFXButton  addAdminButton;

    @FXML
    private JFXComboBox statusAssetBox;

    @FXML
    private MenuItem addUniwideBtn, exportBtnForDisposal;

    @FXML
    private Button updateAssetButton;

    @FXML
    private ObservableList<Asset> getOfficer;

    @FXML
    private ObservableList<Asset> getOfficeruniwide;
    @FXML
    private JFXPasswordField passwordTextfield ;

    @FXML
    private Button logoutButton;

    @FXML
    private Button addAssetButton;

    @FXML
    private Button addOfficerLocal;

    @FXML
    private Button editOfficer;

    @FXML
    private MenuItem addLocalBtn;

    @FXML
    private Button updateOrgLocal;

    @FXML
    private Button changePass;

    @FXML
    private MenuItem ExportOrgBtn;

    @FXML
    private Button updateEmployeeButton;

    private Connection conn;
    private Connection con = DBConnection.getConnection();
    private PreparedStatement pst;
    private PreparedStatement pst2;
    private ResultSet rs;
    private ResultSet rs2;


    @FXML
    private JFXListView<String> univOrg;
    private ObservableList<String> univOrgList;

    @FXML
    private JFXListView<String> BrandListView;
    private ObservableList<String> BrandList;

    @FXML
    private JFXListView<String> CategoryListView;
    private ObservableList<String> CategoryList;

    @FXML
    private JFXTextField usernameTextfield;

    @FXML
    private JFXTextField advisorUwide;

    @FXML
    private JFXTextField lastnameTextfield;

    @FXML
    private JFXTextArea orgdesUwide;

    @FXML
    private MenuItem exportBtn;
    @FXML
    private MenuItem importBtn;
    @FXML
    private MenuItem emptyTable;

    @FXML
    private  JFXComboBox ownerBox;


    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement2 = null;

   

    @FXML
    void addEmployeeButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/addEmployee.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Add Organization");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void addAdminButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/addAdmin.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Add Administrator");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void addAssetButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/addAsset.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Add Asset");
        stage.setScene(new Scene(root));
        stage.show();
        
        
    }

    @FXML
    void LogOutClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/LoginPage.fxml"));
        Scene adminPageScene = new Scene(root);
        Stage adminStage;
        adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        adminStage.setTitle("GISS Inventory Management System - Administrator Mode");
        adminStage.hide();
        adminStage.setScene(adminPageScene);
        adminStage.show();
    }

  public void setUnivOrgList() {
        List<String> org = EmployeeDAO.getUniWideListStringName();
        univOrgList = FXCollections.observableArrayList(org);
        univOrg.setItems(univOrgList);
    }

    public void setBrandList() {
        List<String> org = Brand.getBrandString();
        BrandList = FXCollections.observableArrayList(org);

        BrandListView.setItems(BrandList);

    }
    public void setCategoryList() {
        List<String> org = CategoryDAO.getCategoryList();
        CategoryList = FXCollections.observableArrayList(org);

        CategoryListView.setItems(CategoryList);
    }
    static String tempEmpID,TempBrandID,TempCategoryID,tempPassword,tempNameUser;
    

    private void setTableEmployee() {
        usernameTable.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
        firstnameTable.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        lastnameTable.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        roleTable.setCellValueFactory(new PropertyValueFactory<Employee, String>("role"));
        statusTable.setCellValueFactory(new PropertyValueFactory<Employee, String>("status"));
        tableviewUsers.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                tempEmpID = tableviewUsers.getSelectionModel().getSelectedItem().getEmployee_id();
                tempNameUser = tableviewUsers.getSelectionModel().getSelectedItem().getUsername();
                usernameTextfield.setText(tableviewUsers.getSelectionModel().getSelectedItem().getUsername());
                firstnameTextfield.setText(tableviewUsers.getSelectionModel().getSelectedItem().getFirstName());
                lastnameTextfield.setText(tableviewUsers.getSelectionModel().getSelectedItem().getLastName());
                roleBox.setValue(tableviewUsers.getSelectionModel().getSelectedItem().getRole());
                statusBox.setValue(tableviewUsers.getSelectionModel().getSelectedItem().getStatus());
                passwordTextfield.setText(tableviewUsers.getSelectionModel().getSelectedItem().getPassword());
                
            }
        });
    }
  
   
    public void getOfficerUniwideList() {
        List<Employee> classOfficers = EmployeeDAO.getUniWideList();
        tableviewUniwideListUser = FXCollections.observableArrayList(classOfficers);
        tableviewUsers.setItems(tableviewUniwideListUser);

        FilteredList<Employee> filteredDatae = new FilteredList<>(tableviewUniwideListUser, e -> true);
        searchAssetUser.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredDatae.setPredicate((Predicate<? super Employee>) al -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (al.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (al.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (al.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (al.getRole().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (al.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Employee> sortedDatae = new SortedList<>(filteredDatae);
        sortedDatae.comparatorProperty().bind(tableviewUsers.comparatorProperty());
        tableviewUsers.setItems(sortedDatae);
    }

    public void connectOfficer() {
       
        /* 7/31/2018 */
        BrandListView.setOnMouseClicked(e -> {

            // String univString = (String)BrandListView.getSelectionModel().getSelectedItem();
            String univString  = (String)BrandListView.getSelectionModel().getSelectedItem();
            List<String> brandlist = Brand.getBrandString(univString);
            TempBrandID = univString;
            
        });

        CategoryListView.setOnMouseClicked(e -> {

            String univString  = (String)CategoryListView.getSelectionModel().getSelectedItem();
            List<String> lists = CategoryDAO.getCategoryList(univString);
            TempCategoryID = univString;
            
        });
         univOrg.setOnMouseClicked(e -> {
            tableviewUniwideList.clear();
            String univString = (String)univOrg.getSelectionModel().getSelectedItem();
            List<Asset> lists = AssetDAO.getOfficerUniwideList(univString);
            ObservableList<Asset> olist = FXCollections.observableArrayList(lists);
            tableviewAssets.setItems(olist);
            
          
            FilteredList<Asset> filteredData = new FilteredList<>(olist, u -> true);
            searchAsset.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super Asset>) al -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
               
                
                

                if (al.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (al.getCategory().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (al.getBrand().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (al.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
                 
            });
           
        });
             
        SortedList<Asset> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableviewAssets.comparatorProperty());
        tableviewAssets.setItems(sortedData);
        });
         List<Asset> classOfficer = AssetDAO.getOfficerUniwideList();
        tableviewUniwideList = FXCollections.observableArrayList(classOfficer);
        tableviewAssets.setItems(tableviewUniwideList);
        
        FilteredList<Asset> filteredData = new FilteredList<>(tableviewUniwideList, e -> true);
        searchAsset.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super Asset>) al -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                 String filter=newValue.toString();
                
                String josze = String.valueOf(al.getAssetNum());

                if (al.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (al.getCategory().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } 
                else if (al.getBrand().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (al.getStatus().toLowerCase().contains(lowerCaseFilter)) {
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
        sortedData.comparatorProperty().bind(tableviewAssets.comparatorProperty());
        tableviewAssets.setItems(sortedData);
    }

    @FXML
    void refreshButtonClicked(ActionEvent event) {
        connectOfficer();
        setUnivOrgList();
        getOfficerUniwideList();
        setTableEmployee();

        usernameTextfield.setText(" ");
        firstnameTextfield.setText(" ");
        lastnameTextfield.setText(" ");
        roleBox.setValue(" ");
        statusBox.setValue(" ");
        passwordTextfield.setText(" ");

        ownerBox.setValue(" ");
        categoryBox.setValue(" ");
        brandBox.setValue(" ");
        modelBox.setText(" ");
        serialNumberBox.setText(" ");
        startwarrantyBox.setText(" ");
        endwarrantyBox.setText(" ");
        commentBox.setText(" ");
        assetTagBox.setText(" ");
        statusAssetBox.setValue(" ");

        brand.setAll(" ");
        getBrandCombobox();
        setBrandList();

        category.setAll(" ");
        getCategoryCombobox();
        setCategoryList();
    }

    ObservableList<Asset> masterData = FXCollections.observableArrayList();
    ObservableList<Asset> filteredData = FXCollections.observableArrayList();
    static String tempId,tempCategory,tempName,tempBrand;
   
    

    private void setTableUniwide() {

        show_ownerRow.setCellValueFactory(new PropertyValueFactory<Asset, String>("username"));
        
        show_categoryRow.setCellValueFactory(new PropertyValueFactory<Asset, String>("category"));
        show_brandRow.setCellValueFactory(new PropertyValueFactory<Asset, String>("brand"));
        show_modelRow.setCellValueFactory(new PropertyValueFactory<Asset, String>("model"));
        show_assetTagRow.setCellValueFactory(new PropertyValueFactory<Asset, String>("assetNum"));
        show_serialNumRow.setCellValueFactory(new PropertyValueFactory<Asset, String>("serialNum"));
        show_startwarrantyuniwide.setCellValueFactory(new PropertyValueFactory<Asset, String>("warrantyStartDate"));
        show_endwarrantyuniwide.setCellValueFactory(new PropertyValueFactory<Asset, String>("warrantyEndDate"));
        show_commentsRow.setCellValueFactory(new PropertyValueFactory<Asset, String>("comments"));
        show_statusRow.setCellValueFactory(new PropertyValueFactory<Asset, String>("status"));
        try {
            tableviewAssets.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    
                    tempId = tableviewAssets.getSelectionModel().getSelectedItem().getId();
                    ownerBox.setValue(tableviewAssets.getSelectionModel().getSelectedItem().getUsername());
                    categoryBox.setValue(tableviewAssets.getSelectionModel().getSelectedItem().getCategory());
                    brandBox.setValue(tableviewAssets.getSelectionModel().getSelectedItem().getBrand());
                    modelBox.setText(tableviewAssets.getSelectionModel().getSelectedItem().getModel());
                    assetTagBox.setText(tableviewAssets.getSelectionModel().getSelectedItem().getAssetNum());
                    serialNumberBox.setText(tableviewAssets.getSelectionModel().getSelectedItem().getSerialNum());
                    startwarrantyBox.setText(tableviewAssets.getSelectionModel().getSelectedItem().getWarrantyStartDate());
                    endwarrantyBox.setText(tableviewAssets.getSelectionModel().getSelectedItem().getWarrantyEndDate());
                    commentBox.setText(tableviewAssets.getSelectionModel().getSelectedItem().getComments());
                    statusAssetBox.setValue(tableviewAssets.getSelectionModel().getSelectedItem().getStatus());
                    tempName =tableviewAssets.getSelectionModel().getSelectedItem().getUsername();
                    tempCategory = tableviewAssets.getSelectionModel().getSelectedItem().getCategory();
                    tempBrand = tableviewAssets.getSelectionModel().getSelectedItem().getBrand();
                }

            });
        } catch(NullPointerException e) {
            System.out.print(e);
        }
    }

    private static String EXCEL_FILE_LOCATION = "C:\\Users\\Public\\EMPLOYEES.xlsx";
    private static String EXCEL_FILE_LOCATION2 = "C:\\Users\\Public\\ASSETS_LIST.xlsx";
    private static String EXCEL_FILE_LOCATION3 = "C:\\Users\\Public\\DISPOSAL_FORM.xlsx";
    public static Connection cons = null;
    public static PreparedStatement ps = null;

    @FXML
    void exportBtnClicked(ActionEvent event) throws FileNotFoundException, IOException, SQLException {
        
        Image img = new Image("Images/icons8_Microsoft_Excel_48px.png");
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Export Table Confirmation");
                a.setHeaderText("ASSETS_LIST.xlsx" );
                a.setGraphic(new ImageView(img));
                a.initStyle(StageStyle.UNIFIED);
                a.setContentText("Do you want to export this Table?");
                
                Optional <ButtonType> action = a.showAndWait();
                if(action.get() == ButtonType.OK){
        Connection con = DBConnection.getConnection();
        String query = "SELECT * FROM asset ORDER BY 2,3";
        preparedStatement = con.prepareStatement(query);
        rs = preparedStatement.executeQuery();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("All Assets");
        XSSFRow header = sheet.createRow(0);
        XSSFCell cell = header.createCell(0);
        int i;
        for (i = 0; i <= 9; i++) {
            sheet.setColumnWidth(i, 6000);
            CellStyle style = wb.createCellStyle();
            style.setWrapText(true);
            cell.setCellStyle(style);
        }

        header.createCell(1).setCellValue("Owner");
        header.createCell(2).setCellValue("Category");
        header.createCell(3).setCellValue("Brand");
        header.createCell(4).setCellValue("Model");
        header.createCell(5).setCellValue("Asset Number");
        header.createCell(6).setCellValue("Serial Number");
        header.createCell(7).setCellValue("Warranty Start Date");
        header.createCell(8).setCellValue("Warranty End Date");
        header.createCell(9).setCellValue("Comments");
        header.createCell(10).setCellValue("Status");
        int index = 1;
        while (rs.next()) {
            XSSFRow row = sheet.createRow(index);
            sheet.autoSizeColumn(10);
            row.createCell(1).setCellValue(rs.getString("username"));
            row.createCell(2).setCellValue(rs.getString("category"));
            row.createCell(3).setCellValue(rs.getString("brand"));
            row.createCell(4).setCellValue(rs.getString("model"));
            row.createCell(5).setCellValue(rs.getString("assetNum"));
            row.createCell(6).setCellValue(rs.getString("serialNum"));
            row.createCell(7).setCellValue(rs.getString("warrantyStartDate"));
            row.createCell(8).setCellValue(rs.getString("warrantyEndDate"));
            row.createCell(9).setCellValue(rs.getString("comments"));
            row.createCell(10).setCellValue(rs.getString("status"));
            index++;
        }
        Notifications notif = Notifications.create()
                        .title("Export Complete!")
                        .darkStyle()
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.CENTER)
                        .text("ASSETS_LIST.xlsx is saved in the User/Public/ folder");
                notif.show();
        rs.close();
        sheet.autoSizeColumn(5);
        FileOutputStream fileOut = new FileOutputStream(EXCEL_FILE_LOCATION2);
        System.out.print("SUCCESS");
        wb.write(fileOut);
        fileOut.close();
        con.close();
        }
    }

    @FXML
    void exportAssetDisposalClicked(ActionEvent event) throws FileNotFoundException, IOException, SQLException {
        
         Image img = new Image("Images/icons8_Microsoft_Excel_48px.png");
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Export Table Confirmation");
                a.setHeaderText("DISPOSAL_FORM.xlsx" );
                a.setGraphic(new ImageView(img));
                a.initStyle(StageStyle.UNIFIED);
                a.setContentText("Do you want to export this Table?");
                
                Optional <ButtonType> action = a.showAndWait();
                if(action.get() == ButtonType.OK){
        
        Connection con = DBConnection.getConnection();
        String query = "SELECT * FROM asset WHERE status = 'For Disposal'";
        preparedStatement = con.prepareStatement(query);
        rs = preparedStatement.executeQuery();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("For Disposal");
        XSSFRow header = sheet.createRow(0);
        XSSFCell cell = header.createCell(0);
        int i;
        for (i = 0; i <= 9; i++) {
            sheet.setColumnWidth(i, 6000);
            CellStyle style = wb.createCellStyle();
            style.setWrapText(true);
            cell.setCellStyle(style);
        }

        header.createCell(1).setCellValue("Owner");
        header.createCell(2).setCellValue("Category");
        header.createCell(3).setCellValue("Brand");
        header.createCell(4).setCellValue("Model");
        header.createCell(5).setCellValue("Asset Number");
        header.createCell(6).setCellValue("Serial Number");
        header.createCell(7).setCellValue("Warranty Start Date");
        header.createCell(8).setCellValue("Warranty End Date");
        header.createCell(9).setCellValue("Comments");
        header.createCell(10).setCellValue("Status");
        int index = 1;
        while (rs.next()) {
            XSSFRow row = sheet.createRow(index);
            sheet.autoSizeColumn(10);
            row.createCell(1).setCellValue(rs.getString("username"));
            row.createCell(2).setCellValue(rs.getString("category"));
            row.createCell(3).setCellValue(rs.getString("brand"));
            row.createCell(4).setCellValue(rs.getString("model"));
            row.createCell(5).setCellValue(rs.getString("assetNum"));
            row.createCell(6).setCellValue(rs.getString("serialNum"));
            row.createCell(7).setCellValue(rs.getString("warrantyStartDate"));
            row.createCell(8).setCellValue(rs.getString("warrantyEndDate"));
            row.createCell(9).setCellValue(rs.getString("comments"));
            row.createCell(10).setCellValue(rs.getString("status"));
            index++;
        }
        
         Notifications notif = Notifications.create()
                        .title("DISPOSAL_FORM.xlsx Created!")
                        .darkStyle()
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.CENTER)
                        .text("DISPOSAL_FORM.xlsx is saved in the User/Public/ folder");
                notif.show();
        rs.close();
        
        //Action ad = 
        sheet.autoSizeColumn(5);
        FileOutputStream fileOut = new FileOutputStream(EXCEL_FILE_LOCATION3);
        System.out.print("SUCCESS");
        wb.write(fileOut);
        fileOut.close();
        con.close();
    }
    }

    @FXML
    void exportOrgBtnClicked(ActionEvent event) throws FileNotFoundException, IOException, SQLException {
         Image img = new Image("Images/icons8_Microsoft_Excel_48px.png");
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Export Table Confirmation");
                a.setHeaderText("EMPLOYEES.xlsx" );
                a.setGraphic(new ImageView(img));
                a.initStyle(StageStyle.UNIFIED);
                a.setContentText("Do you want to export this Table?");
                
                Optional <ButtonType> action = a.showAndWait();
                if(action.get() == ButtonType.OK){
        
        try (Connection con = DBConnection.getConnection()) {
            String query2 = "SELECT * FROM employee";
            preparedStatement = con.prepareStatement(query2);
            rs = preparedStatement.executeQuery();

            XSSFWorkbook wb1 = new XSSFWorkbook();
            XSSFSheet sheet1 = wb1.createSheet("Employee List");
            XSSFRow header1 = sheet1.createRow(0);
            XSSFCell cell2 = header1.createCell(0);
            int j;
            for (j = 0; j <= 6; j++) {
                if(j == 4) {
                    sheet1.setColumnHidden(j, true);
                } else
                    sheet1.setColumnWidth(j, 6000);
                CellStyle style1 = wb1.createCellStyle();
                style1.setWrapText(true);
                cell2.setCellStyle(style1);
            }
            header1.createCell(0).setCellValue("ID");
            header1.createCell(1).setCellValue("User name");
            header1.createCell(2).setCellValue("First name");
            header1.createCell(3).setCellValue("Last name ");
            header1.createCell(4).setCellValue("Password");
            header1.createCell(5).setCellValue("Role");

            int index2 = 1;
            while (rs.next()) {
                XSSFRow row1 = sheet1.createRow(index2);
                sheet1.autoSizeColumn(10);
                row1.createCell(0).setCellValue(rs.getInt("id"));
                row1.createCell(1).setCellValue(rs.getString("username"));
                row1.createCell(2).setCellValue(rs.getString("firstname"));
                row1.createCell(3).setCellValue(rs.getString("lastname"));
                row1.createCell(4).setCellValue(rs.getString("password"));
                row1.createCell(4).setCellValue(rs.getString("role"));
                index2++;
            }
            sheet1.autoSizeColumn(5);
            FileOutputStream fileOut2 = new FileOutputStream(EXCEL_FILE_LOCATION);
            System.out.print("SUCCESS");
            wb1.write(fileOut2);
            fileOut2.close();
            preparedStatement.close();
            Notifications notif = Notifications.create()
                        .title("Assets List Created!")
                        .darkStyle()
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.CENTER)
                        .text("Assets List is saved in the User/Public/ folder");
                notif.show();
        }
        }
    }

    @FXML
    public void updateAssetButtonClicked(ActionEvent event) throws SQLException {
        Image img = new Image("Images/icons8_Edit_File_50px.png");
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Update Asset Confirmation");
                a.setHeaderText("Owner: "+ tempName +"\nCategory: " + tempCategory +"\nBrand: " + tempBrand);
                a.setGraphic(new ImageView(img));
                a.initStyle(StageStyle.UNIFIED);
                a.setContentText("Do you want to Update this Asset?");
                
                Optional <ButtonType> action = a.showAndWait();
                if(action.get() == ButtonType.OK){
        
        Connection con = DBConnection.getConnection();
        String query = "UPDATE asset SET username=?, category=?, brand=?, model=?, assetNum=?, serialNum=?,warrantyStartDate=?,warrantyEndDate=?, comments=?, status=? where id='" + tempId + "'";

        if (ownerBox.getSelectionModel().isEmpty() && categoryBox.getSelectionModel().isEmpty() && brandBox.getSelectionModel().isEmpty() && modelBox.getText().trim().isEmpty() && assetTagBox.getText().trim().isEmpty()
                && serialNumberBox.getText().trim().isEmpty() && startwarrantyBox.getText().trim().isEmpty() && endwarrantyBox.getText().trim().isEmpty() && commentBox.getText().trim().isEmpty() && statusAssetBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Oops!");
            alert.setHeaderText(null);
            alert.setContentText("Please click a row first");
            alert.showAndWait();
        } else {
            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, ownerBox.getValue().toString());
                preparedStatement.setString(2, categoryBox.getValue().toString());
                preparedStatement.setString(3, brandBox.getValue().toString());
                preparedStatement.setString(4, modelBox.getText());
                preparedStatement.setString(5, assetTagBox.getText());
                preparedStatement.setString(6, serialNumberBox.getText());
                preparedStatement.setString(7, startwarrantyBox.getText());
                preparedStatement.setString(8, endwarrantyBox.getText());
                preparedStatement.setString(9, commentBox.getText());
                preparedStatement.setString(10, statusAssetBox.getValue().toString());
                preparedStatement.execute();
                preparedStatement.close();
                connectOfficer();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                 Notifications notif = Notifications.create()
                        .title("Success!")
                        .darkStyle()
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER)
                        .text("Asset Successfully Updated");
                notif.show();
                   }
             }
        }
    }
     @FXML
        void emptyTableClicked(ActionEvent event) {
            Image img = new Image("Images/icons8_Erase_48px.png");
            Image imge = new Image("Images/icons8_High_Priority_48px_1.png");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Delete Employee Confirmation");
                a.setHeaderText("WARNING!: Are you sure to erase the table?\n Only do this when a backup is available and the table is corrupted");
                a.setGraphic(new ImageView(imge));
                a.initStyle(StageStyle.UNIFIED);
                a.setContentText("Do you want to delete the whole Asset Table?");
                
                Optional <ButtonType> action = a.showAndWait();
                if(action.get() == ButtonType.OK){
        try{
            ps = con.prepareStatement("DELETE FROM asset");
            ps.executeUpdate();
            ps.close();
            con.close();
              Notifications notif = Notifications.create()
                        .title("Success!")
                        .darkStyle()
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER)
                        .text("Asset Table Successfully Emptied");
                notif.show();
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
    }
 @FXML
    void importBtnClicked(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        Connection con = DBConnection.getConnection();
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\Public\\"));
        fc.getExtensionFilters().addAll(
        new ExtensionFilter("Excel Files","*.xlsx"));
              
        File selectedFile = fc.showOpenDialog(null);
        
        if(selectedFile!= null){
            String j = ""+selectedFile.getAbsoluteFile()+"";  
            try{
                ps = con.prepareStatement("INSERT INTO asset(id, username, category, brand, model, assetNum, serialNum, warrantyStartDate, warrantyEndDate, comments,status) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                
               
                FileInputStream fileIn = new FileInputStream(new File(j));
                XSSFWorkbook wbe = new XSSFWorkbook(fileIn);
                XSSFSheet sheet = wbe.getSheetAt(0);
                System.out.println("Importing...");
                Row row;
                for(int i=1; i<=sheet.getLastRowNum(); i++){
                    row = sheet.getRow(i);
                    Random rand = new Random();

                    int  n = rand.nextInt(999999999) + 1;
                   
                    ps.setInt(1, n);
                    ps.setString(2, row.getCell(1).getStringCellValue());
                    ps.setString(3, row.getCell(2).getStringCellValue());
                    ps.setString(4, row.getCell(3).getStringCellValue());
                    ps.setString(5, row.getCell(4).getStringCellValue());
                    ps.setString(6, row.getCell(5).getStringCellValue().toString());
                    ps.setString(7, row.getCell(6).getStringCellValue().toString());
                    ps.setString(8, row.getCell(7).getStringCellValue().toString());
                    ps.setString(9, row.getCell(8).getStringCellValue().toString());
                    ps.setString(10, row.getCell(9).getStringCellValue().toString());
                    ps.setString(11, row.getCell(10).getStringCellValue().toString());
                    
                    ps.execute();
                }
                 Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("SUCCESS");
            alert2.setHeaderText(null);
            alert2.setContentText("ASSET IMPORTED SUCCESSFULLY");
            alert2.showAndWait();
                wbe.close();
                fileIn.close();
              //rs.close();
                
            }catch(SQLException | FileNotFoundException ex){
                Logger.getLogger(AdministratorPageController.class.getName()).log(Level.SEVERE, null, ex);
            
            }catch(IOException ex){
                Logger.getLogger(AdministratorPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("ERROR");
            alert2.setHeaderText(null);
            alert2.setContentText("File is not valid");
            alert2.showAndWait();
            con.close();
        }
            }
    @FXML
    public void updateEmployeeButtonClicked (ActionEvent event) throws SQLException {
        Image img = new Image("Images/icons8_Registration_48px.png");
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Update Employee Confirmation");
                a.setHeaderText("Name: "+ tempNameUser );
                a.setGraphic(new ImageView(img));
                a.initStyle(StageStyle.UNIFIED);
                a.setContentText("Do you want to Update this Employee?");
                
                Optional <ButtonType> action = a.showAndWait();
                if(action.get() == ButtonType.OK){
        Connection con = DBConnection.getConnection();
        String query = "update employee set username=?, firstName=?, lastName=?,role=?,status=?,password=? where id='" + tempEmpID + "'";

        if (usernameTextfield.getText().trim().isEmpty() && lastnameTextfield.getText().trim().isEmpty() && firstnameTextfield.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Oops!");
            alert.setHeaderText(null);
            alert.setContentText("Please click the Employee to be updated before clicking update button.");
            alert.showAndWait();
        } else {
            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, usernameTextfield.getText());
                preparedStatement.setString(2, firstnameTextfield.getText());
                preparedStatement.setString(3, lastnameTextfield.getText());
                preparedStatement.setString(4, roleBox.getValue().toString());
                preparedStatement.setString(5, statusBox.getValue().toString());
                 try{
        //Check if there are duplicates
       
        Statement stmt = con.createStatement() ;
        ResultSet resultset = stmt.executeQuery("SELECT * FROM employee where id='" + tempEmpID +"'");
        while(resultset.next()){
            System.out.println(resultset.getString("password") + " VS "+passwordTextfield.getText());
        if(resultset.getString("password").equals(passwordTextfield.getText())){
             preparedStatement.setString(6, passwordTextfield.getText().toString());
             
        }else {
            preparedStatement.setString(6, DigestUtils.md5Hex(DigestUtils.sha256Hex(passwordTextfield.getText().toString())));  
        }
        }
    }catch(SQLException ae){ System.out.print(ae);}

                preparedStatement.execute();
                preparedStatement.close();
                getOfficerUniwideList();
            } catch (SQLException e) {
                System.out.println(e);

            } finally {
                List<Employee> classOfficers = EmployeeDAO.getUniWideList();
                tableviewUniwideListUser = FXCollections.observableArrayList(classOfficers);
                tableviewUsers.setItems(tableviewUniwideListUser);
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

    @FXML
    public void deleteAssetButtonClicked (ActionEvent event) throws SQLException {
        Image img = new Image("Images/icons8_Delete_Bin_50px.png");
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Delete Asset Confirmation");
                a.setHeaderText("Owner: "+ tempName +"\nCategory: " + tempCategory +"\nBrand: " + tempBrand);
                a.setGraphic(new ImageView(img));
                a.initStyle(StageStyle.UNIFIED);
                a.setContentText("Do you want to delete this Asset?");
                
                Optional <ButtonType> action = a.showAndWait();
                if(action.get() == ButtonType.OK){
                     Connection con = DBConnection.getConnection();
        String query = "DELETE FROM asset where id='" + tempId + "'";

        if (ownerBox.getSelectionModel().isEmpty() && categoryBox.getSelectionModel().isEmpty() && brandBox.getSelectionModel().isEmpty() && modelBox.getText().trim().isEmpty() && assetTagBox.getText().trim().isEmpty()
                && serialNumberBox.getText().trim().isEmpty() && startwarrantyBox.getText().trim().isEmpty() && endwarrantyBox.getText().trim().isEmpty() && commentBox.getText().trim().isEmpty() && statusAssetBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Oops!");
            alert.setHeaderText(null);
            alert.setContentText("Please click the asset to be updated before clicking Delete button.");
            alert.showAndWait();
        } else {
            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connectOfficer();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                List<Employee> classOfficers = EmployeeDAO.getUniWideList();
                tableviewUniwideListUser = FXCollections.observableArrayList(classOfficers);
                tableviewUsers.setItems(tableviewUniwideListUser);
                Notifications notif = Notifications.create()
                        .title("Success!")
                        .darkStyle()
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER)
                        .text("Asset Successfully Deleted");
                notif.show();
                
                
                }
       
            }
        }
    }

    @FXML
    public void deleteEmployeeButtonClicked (ActionEvent event) throws SQLException {
         Image img = new Image("Images/icons8_Denied_50px.png");
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Delete Employee Confirmation");
                a.setHeaderText("Name: "+ tempNameUser);
                a.setGraphic(new ImageView(img));
                a.initStyle(StageStyle.UNIFIED);
                a.setContentText("Do you want to delete this Employee?");
                
                Optional <ButtonType> action = a.showAndWait();
                if(action.get() == ButtonType.OK){
        
        Connection con = DBConnection.getConnection();
        String query = "DELETE FROM employee where id='" + tempEmpID + "'";


        if (usernameTextfield.getText().trim().isEmpty() || roleBox.getSelectionModel().isEmpty() || statusBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Oops!");
            alert.setHeaderText(null);
            alert.setContentText("Please click the Employee to be updated before clicking Delete button.");
            alert.showAndWait();
        } else {
            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connectOfficer();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
               List<Employee> classOfficers = EmployeeDAO.getUniWideList();
                tableviewUniwideListUser = FXCollections.observableArrayList(classOfficers);
                tableviewUsers.setItems(tableviewUniwideListUser);
                Notifications notif = Notifications.create()
                        .title("Delete Employee Complete")
                        .darkStyle()
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER)
                        .text(usernameTextfield.getText() +" Successfully Deleted");
                notif.show();
                
                 }
            }
        }
    }

    ObservableList<String> owner = FXCollections.observableArrayList();
    public void getOrgNameCombobox() {
        try {
            String sql = "SELECT username FROM employee ORDER BY username";
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                owner.add(rs.getString("username"));
                ownerBox.setItems(owner);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    ObservableList<String> brand = FXCollections.observableArrayList();
    public void getBrandCombobox() {
        Connection con = DBConnection.getConnection();
        try {
            String of = new String();
            String sql = "SELECT type FROM brand ORDER BY type";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                brand.add(rs.getString("type"));
                brandBox.setItems(brand);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addBrandClicked(ActionEvent event) throws SQLException {
        String on = addBrandTextfield.getText();
        Connection con = DBConnection.getConnection();

        PreparedStatement ps;
        ps = con.prepareStatement("INSERT INTO brand(type) VALUES (?)");
        if(on.isEmpty() || on.equals(" ")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Oops!");
            alert.setHeaderText(null);
            alert.setContentText("Unable to add");
            alert.showAndWait();
        } else {
            ps.setString(1, on);
            ps.execute();

            ps.close();
            con.close();
           Notifications notif = Notifications.create()
                        .title("Success!")
                        .darkStyle()
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER)
                        .text("Brand Option Successfully Added");
                notif.show();

        }
       brand.setAll(" ");
        getBrandCombobox();
        setBrandList();

       
    }
    @FXML
    public void deleteBrandClicked (ActionEvent event) throws SQLException {
        Connection con = DBConnection.getConnection();
        String query = "DELETE FROM brand where type ='" + TempBrandID + "'";

        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connectOfficer();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Notifications notif = Notifications.create()
                        .title("Success!")
                        .darkStyle()
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER)
                        .text("Brand Option Successfully Deleted");
                notif.show();
        }
    }

    @FXML
    public void deleteCategoryClicked (ActionEvent event) throws SQLException {
        Connection con = DBConnection.getConnection();
        String query = "DELETE FROM category where type='" + TempCategoryID + "'";

        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connectOfficer();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
           Notifications notif = Notifications.create()
                        .title("Success!")
                        .darkStyle()
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER)
                        .text("Category Option Successfully Deleted");
                notif.show();
        }
    }


    ObservableList<String> category = FXCollections.observableArrayList();
    public void getCategoryCombobox() {

        try {
            String sql = "SELECT type FROM category ORDER BY type";
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {

                category.add(rs.getString("type"));
                categoryBox.setItems(category);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void addCategoryClicked(ActionEvent event) throws SQLException {
        String on = addCategoryTextfield.getText();
        Connection con = DBConnection.getConnection();

        PreparedStatement ps;
        ps = con.prepareStatement("INSERT INTO category(type) VALUES (?)");
        if(on.isEmpty() || on.equals(" ")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Oops!");
            alert.setHeaderText(null);
            alert.setContentText("Unable to add");
            alert.showAndWait();
        } else {
            ps.setString(1, on);
            ps.execute();
        }

       Notifications notif = Notifications.create()
                        .title("Success!")
                        .darkStyle()
                        .hideAfter(Duration.seconds(2))
                        .position(Pos.CENTER)
                        .text("Category Option Successfully Added");
                notif.show();
        category.setAll(" ");
        getCategoryCombobox();
        setCategoryList();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableUniwide();
       
        setUnivOrgList();
        getOfficerUniwideList();
        connectOfficer();
        getOrgNameCombobox();
        setTableEmployee();

        getBrandCombobox();
        setBrandList();

        getCategoryCombobox();
        setCategoryList();

        ObservableList<String> statusAsset = FXCollections.observableArrayList(
                "Assigned",
                "Unassigned",
                "For Disposal"
                                             );

        statusAssetBox.setItems(statusAsset);
        ObservableList<String> roles = FXCollections.observableArrayList(
                                           "Admin",
                                           "Employee"
                                       );
        roleBox.setItems(roles);

        ObservableList<String> statusEmployee = FXCollections.observableArrayList(
                "Active",
                "Disabled"
                                                );
        statusBox.setItems(statusEmployee);
        
        
      
};
    }
