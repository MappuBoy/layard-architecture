package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AdminPannelFormController  {
    public TextField txtCurrentDate;
    public TextField txtAdminCurrentDate;
    public AnchorPane managerRoot;
    public JFXButton btnBack;

    public void initialize() throws IOException {
        loadDefault();

        genarateTime();

        generateCurrentDate();
    }

    private void generateCurrentDate() {
        txtAdminCurrentDate.setText(LocalDate.now().toString());
    }

    private void genarateTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e ->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            txtCurrentDate.setText(LocalTime.now().format(formatter));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        txtCurrentDate.setText(LocalDate.now().toString());
    }

    private void loadDefault() throws IOException {
        setUi("ManagerDashBoardForm");
    }
    public void storeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("StoreForm");
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerForm");
    }
    private void setUi(String location) throws IOException {
        managerRoot.getChildren().clear();
        managerRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/" + location + ".fxml")));
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ManagerDashBoardForm");
    }
}
