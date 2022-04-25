package controller;

import bo.BOFactory;
import bo.custom.ItemBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import view.tm.Itemtm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StoreController implements Initializable {

    public AnchorPane StoreRoot;
    public TableView<Itemtm> tbStore;
    public JFXTextField txtDiscrion;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtpacksize;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtCode;
    public JFXTextField txtItemType;
    public JFXButton addBtn;
    public ImageView updateBtn;
    public ImageView deleteBtn;
    public ImageView addImgBtn;

    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBo(BOFactory.BOTypes.ITEM);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initzializ the table customer
        tbStore.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Item_Code"));
        tbStore.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tbStore.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("packSize"));
        tbStore.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tbStore.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tbStore.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Item_Type"));

        //get selected row to textField
        tbStore.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtCode.setText(newValue.getItem_Code());
            txtDiscrion.setText(newValue.getDescription());
            txtpacksize.setText(newValue.getPackSize());
            txtQtyOnHand.setText(newValue.getQtyOnHand()+"");
            txtUnitPrice.setText(newValue.getUnitPrice()+"");
            txtItemType.setText(newValue.getItem_Type());
        });

        //getALLCustomer details
        getAllItemDetails();

        //Initialize the buttons
        addImgBtn.setPickOnBounds(true);

        updateBtn.setPickOnBounds(true);

        deleteBtn.setPickOnBounds(true);

        //set images actions
        addImgBtn.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("add button clicked");
                addItemOnAction(null);
            }
        });

        updateBtn.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("update button clicked");
                updateOnAction(null);
            }
        });

        deleteBtn.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("delete button clicked");
                deleteCustomerOnAction(null);
            }
        });

    }

    private void getAllItemDetails() {
        try {
            ArrayList<ItemDTO> allItem = itemBO.getAllItem();

            ObservableList<Itemtm> list = FXCollections.observableArrayList();
            for (ItemDTO s : allItem) {
                list.add(new Itemtm(s.getItem_Code(), s.getDescription(), s.getPackSize(), s.getQtyOnHand(),
                        s.getUnitPrice(), s.getItem_Type()));
            }
            tbStore.setItems(list);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addItemOnAction(ActionEvent actionEvent) {
        try {
            boolean addItem = itemBO.addItem(new ItemDTO(txtCode.getText(), txtDiscrion.getText(), txtpacksize.getText(),
                    Integer.parseInt(txtQtyOnHand.getText()), Double.parseDouble(txtUnitPrice.getText()),
                    txtItemType.getText()));

            if (addItem) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Added Successfully!!!!", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Item Added UnSuccessfully!!!!", ButtonType.OK).show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        cleaerItemOnAction(null);

        getAllItemDetails();

    }

    public void searchCodeOnAction(ActionEvent actionEvent) {
        try {
            ItemDTO itemDTO = itemBO.searchItem(txtCode.getText());
            if (itemDTO != null) {
                txtpacksize.setText(itemDTO.getPackSize());
                txtItemType.setText(itemDTO.getItem_Type());
                txtDiscrion.setText(itemDTO.getDescription());
                txtUnitPrice.setText(itemDTO.getUnitPrice() + "");
                txtQtyOnHand.setText(itemDTO.getQtyOnHand() + "");
            } else {
                new Alert(Alert.AlertType.ERROR, "Item Can't found!!!", ButtonType.OK).show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {

        try {
            boolean updateItem = itemBO.updateItem(new ItemDTO(txtCode.getText(), txtDiscrion.getText(), txtpacksize.getText(),
                    Integer.parseInt(txtQtyOnHand.getText()), Double.parseDouble(txtUnitPrice.getText()),
                    txtItemType.getText()));

            if (updateItem) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Added Successfully!!!!", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Item Added UnSuccessfully!!!!", ButtonType.OK).show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        cleaerItemOnAction(null);

        getAllItemDetails();

    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) {

        try {
            boolean deleteItem = itemBO.deleteItem(txtCode.getText());
            if (deleteItem) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item delete Successfully!!!!", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Item delete UnSuccessfully!!!!", ButtonType.OK).show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        cleaerItemOnAction(null);

        getAllItemDetails();

    }

    public void btnBackButton(ActionEvent actionEvent) throws IOException {
        setUi("ManagerDashBoardForm");
    }

    private void setUi(String location) throws IOException {
        StoreRoot.getChildren().clear();
        StoreRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/" + location + ".fxml")));
    }

    public void discriptionOnAction(ActionEvent actionEvent) {
        txtpacksize.requestFocus();
    }

    public void unitPriceOnAction(ActionEvent actionEvent) {
        txtItemType.requestFocus();
    }

    public void packSizeOnAction(ActionEvent actionEvent) {
        txtQtyOnHand.requestFocus();
    }

    public void availableOnAction(ActionEvent actionEvent) {
        txtUnitPrice.requestFocus();
    }

    public void itemTypeOnAction(ActionEvent actionEvent) {
        addBtn.requestFocus();
    }

    public void cleaerItemOnAction(ActionEvent actionEvent) {
        txtCode.clear();
        txtDiscrion.clear();
        txtpacksize.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
        txtItemType.clear();

    }

}
