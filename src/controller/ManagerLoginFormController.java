package controller;

import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerLoginFormController {
    public JFXPasswordField password;
    public AnchorPane root;



    public void OnActionBackButton(ActionEvent actionEvent) throws IOException {
        setUi("DashBoard");
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        if ( password.getText().equalsIgnoreCase("1234")) {
            new Alert(Alert.AlertType.CONFIRMATION, "Welcome to Janaka Grosury ").show();
            Stage window = (Stage) this.root.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ManagerDashBoardForm.fxml"))));
            window.centerOnScreen();

        } else {
            new Alert(Alert.AlertType.WARNING, "Please check your Username and Password").show();
            clearloginOnAction(null);
        }
    }
    public void clearloginOnAction(ActionEvent actionEvent) {
        password.clear();
    }
    private void setUi(String location) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/" + location + ".fxml")));
    }
}
