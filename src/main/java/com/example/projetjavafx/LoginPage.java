package com.example.projetjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginPage {
    @FXML
    private TextField emailTextFIeld;

    @FXML
    private TextField passwordTextField;



    @FXML
    void submit(ActionEvent event) {
        String email = emailTextFIeld.getText();
        String password = passwordTextField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please enter both email and password.");
            return;
        }
        ServiceDB service = new ServiceDB();

        String loginSuccess = service.login(email, password);

        if (Objects.equals(loginSuccess, "user")) {
            goToHome();

        }else if(Objects.equals(loginSuccess, "admin")){
            goToAdmin();
        }
        else {
            showAlert("Error", "Invalid email or password.");
        }
    }

    private void goToHome() {
        Stage primaryStage = (Stage) emailTextFIeld.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return; // Handle the exception
        }

        // Create a new scene with the loaded root
        Scene scene = new Scene(root);

        // Set the scene to the primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void goToAdmin() {
        Stage primaryStage = (Stage) emailTextFIeld.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminPage.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return; // Handle the exception
        }

        // Create a new scene with the loaded root
        Scene scene = new Scene(root);

        // Set the scene to the primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @FXML
    void goToSignUp(ActionEvent event) {
        Stage primaryStage = (Stage) emailTextFIeld.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signupPage.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return; // Handle the exception
        }

        // Create a new scene with the loaded root
        Scene scene = new Scene(root);

        // Set the scene to the primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
