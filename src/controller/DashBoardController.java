package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.OrderDetailBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDetailDTO;
import dto.OrdersDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.CartTm;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class DashBoardController {

    public AnchorPane mainRoot;
    public TextField lbCurrentDate;
    public JFXButton btnBack;
    public Label lbOrder;
    public JFXTextField txtCustmerId;
    public JFXTextField txtCustmerName;
    public JFXTextField txtCustmerTitle;
    public JFXTextField txtCustmerAddress;
    public JFXTextField txtCustmerCity;
    public JFXTextField txtCustmerProvince;
    public JFXTextField txtCustmerCode;
    public AnchorPane managerRoot;
    public JFXTextField txtAddQty;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtId;
    public JFXTextField txtItemType;
    public JFXTextField txtPackSize;
    public JFXTextField txtDiscription;
    public TextField txtOrderTotal;
    public TextField txtOrderCash;
    public TextField txtOrderBalance;
    public TableView<CartTm> tblOrderDetail;
    public TextField ldCurrenttime;
    public ImageView addCustomerButton;
    ObservableList<CartTm> obList = FXCollections.observableArrayList();
    int cartSelectedRowForRemove = -1;

    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBo(BOFactory.BOTypes.CUSTOMER);
    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBo(BOFactory.BOTypes.ITEM);
    OrderDetailBO orderBO = (OrderDetailBO) BOFactory.getInstance().getBo(BOFactory.BOTypes.ORDERDETAIL);

    public void initialize() {
        tblOrderDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Item_Code"));
        tblOrderDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("OrderQty"));
        tblOrderDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Discount"));

        loadDateAndTime();

        tblOrderDetail.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });

        addCustomerButton.setPickOnBounds(true);

        addCustomerButton.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("add button clicked");
                try {
                    addCustomerOnAction(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        generateOrderId();

    }

    private void generateOrderId() {

        try {
            String lastOrderId = orderBO.getLastOrderId();

            if (lastOrderId != null) {
                lastOrderId = lastOrderId.split("[A-Z]")[1];
                if (Integer.parseInt(lastOrderId) < 9) {
                    lastOrderId = "O00" + (Integer.parseInt(lastOrderId) + 1);
                    lbOrder.setText(lastOrderId);
                } else if (Integer.parseInt(lastOrderId) < 100) {
                    lastOrderId = "O0" + (Integer.parseInt(lastOrderId) + 1);
                    lbOrder.setText(lastOrderId);
                }
            } else {
                lbOrder.setText("O001");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) this.mainRoot.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"))));
        window.centerOnScreen();
    }

    public void SearchCustomerOnAction(ActionEvent actionEvent) {
        try {
            CustomerDTO customerDTO = customerBO.searchCustomer(txtCustmerId.getText());
            if (customerDTO != null) {
                txtCustmerName.setText(customerDTO.getCust_name());
                txtCustmerTitle.setText(customerDTO.getCust_Title());
                txtCustmerAddress.setText(customerDTO.getCust_address());
                txtCustmerCity.setText(customerDTO.getCity());
                txtCustmerProvince.setText(customerDTO.getProvince());
                txtCustmerCode.setText(customerDTO.getPostalCode());
            } else {
                new Alert(Alert.AlertType.WARNING, "Customer Not Found!!!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AddCustomerForm");
    }

    public void addOrderDetailOnAction(ActionEvent actionEvent) {
        String itemId = txtId.getText();
        /*String packsize = txtPackSize.getText();
        String discription = txtDiscription.getText();*/
        double unitprice = Double.parseDouble(txtUnitPrice.getText());
        int qtyonHand = Integer.parseInt(txtQtyOnHand.getText());
        int customerQty = Integer.parseInt(txtAddQty.getText());
        double total = customerQty * unitprice;

        if (qtyonHand < customerQty) {
            new Alert(Alert.AlertType.WARNING, "Invalid amount").show();
            return;
        }

        CartTm tm = new CartTm(itemId, customerQty, total);
        int rowNumber = isExists(tm);
        if (isExists(tm) == -1) {//adding new value
            obList.add(tm);
        } else {//update
            CartTm temptm = obList.get(rowNumber);
            CartTm newTm = new CartTm(temptm.getItem_Code(), temptm.getOrderQty(), temptm.getDiscount());
            if (qtyonHand < temptm.getOrderQty()) {
                new Alert(Alert.AlertType.WARNING, "Invalid amount").show();
                return;
            }

            obList.remove(rowNumber);
            obList.add(newTm);

        }

        tblOrderDetail.setItems(obList);
        calCulateCost();
    }

    public void removeOrderDetailOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove == -1) {
            new Alert(Alert.AlertType.WARNING, "please select a row").show();
        } else {
            obList.remove(cartSelectedRowForRemove);
            tblOrderDetail.refresh();
        }
    }

    public void SearchIdOnAction(ActionEvent actionEvent) {
        try {
            ItemDTO itemDTO = itemBO.searchItem(txtId.getText());
            if (itemDTO != null) {
                txtDiscription.setText(itemDTO.getDescription());
                txtPackSize.setText(itemDTO.getPackSize());
                txtQtyOnHand.setText(itemDTO.getQtyOnHand() + "");
                txtItemType.setText(itemDTO.getItem_Type());
                txtUnitPrice.setText(itemDTO.getUnitPrice() + "");
            } else {
                new Alert(Alert.AlertType.WARNING, "please select a row").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void managerLoginOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ManagerDashBoardForm");
    }

    public void calculateBalance(ActionEvent actionEvent) {
        if (!txtOrderCash.getText().isEmpty()) {
            double orderTotal = Double.parseDouble(txtOrderTotal.getText());
            double custCash = Double.parseDouble(txtOrderCash.getText());
            double cashBalance = custCash - orderTotal;

            cashBalance = cashBalance * 100;
            cashBalance = (int) cashBalance;
            cashBalance = cashBalance / 100;

            txtOrderBalance.setText(Double.toString(cashBalance));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Please Enter Amount to calculate Balance").show();
        }
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {

        ArrayList<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();

        for (CartTm cartTm : obList) {
            orderDetailDTOList.add(new OrderDetailDTO(lbOrder.getText(), cartTm.getItem_Code(), cartTm.getOrderQty(),
                    cartTm.getDiscount()));
        }

        OrdersDTO ordersDTO = new OrdersDTO(lbOrder.getText(), txtCustmerId.getText(),
                lbCurrentDate.getText(),
                Double.parseDouble(txtOrderTotal.getText()), orderDetailDTOList);

        try {
            if (cashPayments()) {
                boolean placeOrder = orderBO.placeOrder(ordersDTO);
                if (placeOrder) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Order Purches Succssfully", ButtonType.OK).show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Order Purches UnSuccessful Cheack it again....", ButtonType.OK).show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Cash Should be Greater than total....", ButtonType.OK).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        clearAllTextFields();

        generateOrderId();
    }

    public boolean cashPayments() {
        double total = Double.parseDouble(txtOrderTotal.getText());
        double cash = Double.parseDouble(txtOrderCash.getText());
        return cash > total;
    }

    public void printOnAction(ActionEvent actionEvent) {
    }

    private void setUi(String location) throws IOException {
        mainRoot.getChildren().clear();
        mainRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/" + location + ".fxml")));
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lbCurrentDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            ldCurrenttime.setText(currentTime.getHour() + " : " + currentTime.getMinute() + " : " + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    private int isExists(CartTm tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItem_Code().equals(obList.get(i).getItem_Code())) {
                return i;
            }
        }
        return -1;
    }

    void calCulateCost() {
        double ttl = 0;
        for (CartTm tm : obList) {
            ttl += tm.getDiscount();
        }
        txtOrderTotal.setText(String.valueOf(ttl));
    }

    //------------------------------------------------------------------------------------------------
    /*public static boolean addOrder(Orders orders) {
     *//*try {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO order VALUES(?,?,?,?,?,?,?)");
        preparedStatement.setObject(1, orders.getOrderId());
        preparedStatement.setObject(2, orders.getCust_id());
        preparedStatement.setObject(3, orders.getOrder_Date());
        preparedStatement.setObject(4, orders.getCost());

        return preparedStatement.executeUpdate() > 0;

    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    return false;*//*
}*/

    /*public static boolean addOrderDetails(ArrayList<OrderDetail> arrayList) throws SQLException, ClassNotFoundException {
        for (OrderDetail orderDetail : arrayList) {
            boolean addOrderDetails = addOrderDetails(orderDetail);
            if (!addOrderDetails) {
                return false;
            }
        }
        return true;
    }*/

    /*public static boolean addOrderDetails(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
     *//*Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orderdetail VALUES(?,?,?,?,?,?,?)");
        preparedStatement.setObject(1, orderDetail.getOrderId());
        preparedStatement.setObject(2, orderDetail.getItem_Code());
        preparedStatement.setObject(3, orderDetail.getOrderQty());
        preparedStatement.setObject(4, orderDetail.getDiscount());
        return preparedStatement.executeUpdate() > 0 ;*//*
    }*/

    /*public static boolean updateItemQty(ArrayList<OrderDetail> arrayList) throws SQLException, ClassNotFoundException {
        for (OrderDetail orderDetail  : arrayList) {
            boolean updateQty = updateItemQty(orderDetail);
            if (!updateQty) {
                return false;
            }
        }
        return true;
    }*/

    /*public static boolean updateItemQty(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
     *//*PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE item SET qtyOnHand=qtyOnHand-? WHERE Item_Id=?");
        statement.setObject(1, orderDetail.getOrderQty());
        statement.setObject(2, orderDetail.getDiscount());
        return statement.executeUpdate() > 0;*//*

    }*/

    private void clearAllTextFields() {
        txtCustmerId.clear();
        txtCustmerTitle.clear();
        txtCustmerName.clear();
        txtCustmerAddress.clear();
        txtCustmerCity.clear();
        txtCustmerProvince.clear();
        txtCustmerCode.clear();
        txtId.clear();
        txtDiscription.clear();
        txtQtyOnHand.clear();
        txtAddQty.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtItemType.clear();
        txtOrderTotal.clear();
        txtOrderCash.clear();
        txtOrderBalance.clear();
        for (int i = 0; i < tblOrderDetail.getItems().size(); i++) {
            tblOrderDetail.getItems().clear();
        }
    }
}
