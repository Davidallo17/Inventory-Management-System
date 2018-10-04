package Model;

import javafx.beans.property.SimpleStringProperty;

public class Asset {
    private  SimpleStringProperty id;
    private  SimpleStringProperty username;
    private  SimpleStringProperty category;
    private  SimpleStringProperty brand;
    private  SimpleStringProperty model;
    private  SimpleStringProperty assetNum;
    private  SimpleStringProperty serialNum;
    private  SimpleStringProperty warrantyStartDate;
    private  SimpleStringProperty warrantyEndDate;
    private  SimpleStringProperty comments;
    private  SimpleStringProperty status;

    public Asset(String id, String username, String category, String brand, String model,  String assetNum, String serialNum, String warrantyStartDate, String warrantyEndDate, String comments, String status) {
        this.id = new SimpleStringProperty(id);
        this.username = new SimpleStringProperty(username);
        this.category = new SimpleStringProperty(category);
        this.brand = new SimpleStringProperty(brand);
        this.model = new SimpleStringProperty(model);
        this.assetNum = new SimpleStringProperty(assetNum);
        this.serialNum = new SimpleStringProperty(serialNum);
        this.warrantyStartDate = new SimpleStringProperty(warrantyStartDate);
        this.warrantyEndDate = new SimpleStringProperty(warrantyEndDate);
        this.comments = new SimpleStringProperty(comments);
        this.status = new SimpleStringProperty(status);
    }

    public String getId() {
        return id.get();
    }

    public void setId(SimpleStringProperty id) {
        this.id = id;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(SimpleStringProperty username) {
        this.username = username;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(SimpleStringProperty category) {
        this.category = category;
    }

    public String getBrand() {
        return brand.get();
    }

    public void setBrand(SimpleStringProperty brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model.get();
    }
    public String getModelString() {
        return (model.toString());
    }
    public void setModel(SimpleStringProperty model) {
        this.model = model;
    }
    public String getAssetNum() {
        return assetNum.get();
    }
    public String getAssetNumString() {
        return (assetNum.toString());
    }
    public void setAssetNum(SimpleStringProperty assetNum) {
        this.assetNum = assetNum;
    }

    public String getSerialNum() {
        return serialNum.get();
    }
    public String getSerialNumString() {
        return (serialNum.toString());
    }
    public void setSerialNum(SimpleStringProperty serialNum) {
        this.serialNum = serialNum;
    }

    public String getWarrantyStartDate() {
        return warrantyStartDate.get();
    }
    public String getWarrantyStartString() {
        return (warrantyStartDate.toString());
    }
    public void setWarrantyStartDate(SimpleStringProperty warrantyStartDate) {
        this.warrantyStartDate = warrantyStartDate;
    }

    public String getWarrantyEndDate() {
        return warrantyEndDate.get();
    }
    public String getWarrantyEndString() {
        return (warrantyEndDate.toString());
    }
    public void setWarrantyEndDate(SimpleStringProperty warrantyEndDate) {
        this.warrantyEndDate = warrantyEndDate;
    }

    public String getComments() {
        return comments.get();
    }
    public String getCommentsString() {
        return (comments.toString());
    }
    public void setComments(SimpleStringProperty comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(SimpleStringProperty status) {
        this.status = status;
    }

}
