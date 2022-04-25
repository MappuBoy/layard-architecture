package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXButton btnLogin;
    public JFXButton btnCancel;
    public JFXPasswordField txtPass;
    public AnchorPane root;


    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equalsIgnoreCase("admin") && txtPass.getText().equalsIgnoreCase("1234")) {
            new Alert(Alert.AlertType.CONFIRMATION, "Welcome to Manager DashBoard ").show();
            Stage window = (Stage) this.root.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/AdminPannelForm.fxml"))));//ToDo-AdminPannelForm.xml
            window.centerOnScreen();

        }else if (txtUserName.getText().equalsIgnoreCase("employee") && txtPass.getText().equalsIgnoreCase("1235")) {
            new Alert(Alert.AlertType.CONFIRMATION, "Welcome to Janaka Grosury ").show();
            Stage window = (Stage) this.root.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/DashBoard.fxml"))));
            window.centerOnScreen();

        } else {
            new Alert(Alert.AlertType.WARNING, "Please check your Username and Password").show();
            clearloginOnAction(null);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void userTxtOnAction(ActionEvent actionEvent) {txtPass.requestFocus();
    }

    public void passTxtOnAction(ActionEvent actionEvent) {btnLogin.requestFocus();
    }
    public void clearloginOnAction(ActionEvent actionEvent) {
        txtUserName.clear();
        txtPass.clear();

    }
}
