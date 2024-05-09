package com.example.projetjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupPage {
    @FXML
    private TextField ageTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    void submit(ActionEvent event) {
        String ageText  = ageTextField.getText();
        String email = emailTextField.getText();
        String name = nameTextField.getText();
        String password = passwordTextField.getText();
        if (isValidInput(ageText, email, name, password)) {
            int age = Integer.parseInt(ageText);

            // Create a new instance of ServiceDB and use it
            ServiceDB service = new ServiceDB();

            // Assuming ServiceDB has a method to handle registration
            service.addUser(new User(age, name, email, password));

            // Navigate to the home page after registration
            navigateToHomePage();
        } else {
            // Handle invalid input (e.g., show an error message)
        }
    }

    @FXML
    void goToLogin(ActionEvent event) {
        Stage primaryStage = (Stage) ageTextField.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
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

    private boolean isValidInput(String ageText, String email, String name, String password) {
        try {
            int age = Integer.parseInt(ageText);
            return age > 0 && !email.isEmpty() && !name.isEmpty() && !password.isEmpty();
        } catch (NumberFormatException e) {
            return false; // Age is not a valid number
        }
    }

    private void navigateToHomePage() {
        Stage primaryStage = (Stage) ageTextField.getScene().getWindow();
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
}