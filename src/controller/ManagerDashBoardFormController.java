package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.OrderDetailBO;
import dto.CustomerDTO;
import dto.OrdersDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.CustomerTM;
import view.tm.Ordertm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManagerDashBoardFormController implements Initializable {

    public AnchorPane adminPage;
    public Label lblItemCount;
    public Label lblCustomerCount;
    public Label lblOrderCount;
    public TableView<Ordertm> tbOrder;

    OrderDetailBO orderBO = (OrderDetailBO) BOFactory.getInstance().getBo(BOFactory.BOTypes.ORDERDETAIL);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBo(BOFactory.BOTypes.CUSTOMER);
    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBo(BOFactory.BOTypes.ITEM);

    public void initialize(URL location, ResourceBundle resources) {

        tbOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        tbOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Cust_Id"));
        tbOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Order_Date"));
        tbOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Cost"));

        //get all count from the item table
        getItemCount();

        //get all count from the customer table
        getCustomerCount();

        //get all count from the order table
        getOrderCount();

        //load All the Prescription details to the table
        getAllOrders();
    }

    private void getAllOrders() {
        try {
            ArrayList<OrdersDTO> allOrders = orderBO.getAllOrders();
            ObservableList<Ordertm> list = FXCollections.observableArrayList();
            for (OrdersDTO order : allOrders) {
                list.add(new Ordertm(order.getOrderId(),order.getCust_id(),order.getOrder_Date(),order.getCost()));
            }
            tbOrder.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getOrderCount() {
        try {
            int orderCount = orderBO.getOrderCount();

            if (orderCount > 0) {
                lblOrderCount.setText(String.valueOf(orderCount));
            } else {
                lblOrderCount.setText("0");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getCustomerCount() {
        try {
            int customerCount = customerBO.getCustomerCount();
            if (customerCount > 0) {
                lblCustomerCount.setText(String.valueOf(customerCount));
            } else {
                lblOrderCount.setText("0");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getItemCount() {
        try {
            int itemCount = itemBO.getItemCount();
            if (itemCount > 0) {
                lblItemCount.setText(String.valueOf(itemCount));
            } else {
                lblItemCount.setText("0");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

