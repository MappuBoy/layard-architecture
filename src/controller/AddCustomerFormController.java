package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    public AnchorPane AddCustomerRoot;
    public JFXTextField txtTitle;
    public JFXTextField txtProvince;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtId;
    public JFXTextField txtPostalCode;
    public JFXTextField txtCity;
    public JFXButton addBtn;
    public ImageView addImgBtn;

    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBo(BOFactory.BOTypes.CUSTOMER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addImgBtn.setPickOnBounds(true);

        addImgBtn.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("add button clicked");
                addICustomerOnAction(null);
            }
        });
    }

    public void addICustomerOnAction(ActionEvent actionEvent) {
        try {
            boolean addCustomer = customerBO.addCustomer(new CustomerDTO(txtId.getText(), txtTitle.getText(),
                    txtName.getText(), txtAddress.getText(), txtCity.getText(), txtProvince.getText(),
                    txtPostalCode.getText()));

            if (addCustomer) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added Successfully!!!!!", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Customer Added Unsuccessfully!!!!!", ButtonType.OK,ButtonType.CANCEL).show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        clearTextOnAction();
    }

    public void btnBackButton(ActionEvent actionEvent) throws IOException {
        setUi("DashBoard");
    }
    //--------------------------------------------------------------------------------------------------

    public void titleOnAction(ActionEvent actionEvent) {
        txtName.requestFocus();
    }

    public void provinceOnAction(ActionEvent actionEvent) {
        txtPostalCode.requestFocus();
    }

    public void nameOnAction(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void addressOnAction(ActionEvent actionEvent) {
        txtCity.requestFocus();
    }

    public void idOnAction(ActionEvent actionEvent) {
        txtTitle.requestFocus();
    }

    public void codeOnAction(ActionEvent actionEvent) {
        addBtn.requestFocus();
    }

    public void cityOnAction(ActionEvent actionEvent) {
        txtProvince.requestFocus();
    }

    //----------------------------------------------------------------------------------------

    public void clearTextOnAction() {
        txtId.clear();
        txtTitle.clear();
        txtName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
    }

    private void setUi(String location) throws IOException {
        AddCustomerRoot.getChildren().clear();
        AddCustomerRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/" + location + ".fxml")));
    }
}
