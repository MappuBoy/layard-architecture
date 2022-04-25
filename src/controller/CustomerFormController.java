package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.CustomerTM;
import view.tm.Ordertm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    public AnchorPane customerRoot;
    public TableView<CustomerTM> tbCustomer;
    public JFXTextField txtCustFirstName;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCity;
    public JFXTextField txtTitle;
    public JFXTextField txtCustId;
    public JFXTextField txtProvince;
    public JFXTextField txtcityCode;

    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBo(BOFactory.BOTypes.CUSTOMER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initzializ the table customer
        tbCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Cust_id"));
        tbCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Cust_Title"));
        tbCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Cust_name"));
        tbCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Cust_address"));
        tbCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("City"));
        tbCustomer.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Province"));
        tbCustomer.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("PostalCode"));

        //get selected row to textField
        tbCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtCustId.setText(newValue.getCust_id());
            txtTitle.setText(newValue.getCust_Title());
            txtCustFirstName.setText(newValue.getCust_name());
            txtCustAddress.setText(newValue.getCust_address());
            txtCity.setText(newValue.getCity());
            txtProvince.setText(newValue.getProvince());
            txtcityCode.setText(newValue.getPostalCode());
        });

        //getALLCustomer details
        getAllCustomerData();

    }

    private void getAllCustomerData() {
        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();

            ObservableList<CustomerTM> list = FXCollections.observableArrayList();
            for (CustomerDTO s : allCustomers) {
                list.add(new CustomerTM(s.getCust_id(),s.getCust_Title(),s.getCust_name(),s.getCust_address(),
                        s.getCity(),s.getProvince(),s.getPostalCode()));
            }
            tbCustomer.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) {

        try {
            boolean deleteCustomer = customerBO.deleteCustomer(txtCustId.getText());
            if (deleteCustomer) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Delete Successful!!", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer Delete Unsuccessful!!", ButtonType.OK).show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        clearTextOnAction();

        getAllCustomerData();
    }

    public void btnBackButton(ActionEvent actionEvent) throws IOException {
        setUi("ManagerDashBoardForm");
    }

    public void IdOnAction(ActionEvent actionEvent) {
        try {
            CustomerDTO customerDTO = customerBO.searchCustomer(txtCustId.getText());

            if (customerDTO != null){
                txtTitle.setText(customerDTO.getCust_Title());
                txtCustFirstName.setText(customerDTO.getCust_name());
                txtCustAddress.setText(customerDTO.getCust_address());
                txtCity.setText(customerDTO.getCity());
                txtProvince.setText(customerDTO.getProvince());
                txtcityCode.setText(customerDTO.getPostalCode());
            }else {
                new Alert(Alert.AlertType.WARNING, "No Customer Found!!!",ButtonType.OK).show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clearTextOnAction() {
        txtCustId.clear();
        txtTitle.clear();
        txtCustFirstName.clear();
        txtCustAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtcityCode.clear();
    }

    private void setUi(String location) throws IOException {
        customerRoot.getChildren().clear();
        customerRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/" + location + ".fxml")));
    }
}
