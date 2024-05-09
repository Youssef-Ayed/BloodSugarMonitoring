package com.example.projetjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePage {
    @FXML
    private TextField bloodLevel;

    @FXML
    void check(ActionEvent event) {
        try {
            double sugarLevel = Double.parseDouble(bloodLevel.getText());

            // Check the sugar blood level and display a custom message
            String message = getCustomMessage(sugarLevel);
            showAlert("Sugar Blood Level", message);
            if (sugarLevel> 100 || sugarLevel < 70){
                openDoctorsListPopup();
            }

        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input. Please enter a valid numeric value for blood level.");
        }
    }

    private String getCustomMessage(double sugarLevel) {
        if (sugarLevel < 70) {
            return "Low blood sugar level. Take appropriate action.";
        } else if (sugarLevel >= 70 && sugarLevel <= 100) {
            return "Normal blood sugar level.";
        } else if (sugarLevel > 100 && sugarLevel <= 125) {
            return "Pre-diabetic blood sugar level. Consult a doctor.";
        } else {
            return "High blood sugar level. Seek immediate medical attention.";
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void openDoctorsListPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("doctorListPage.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Doctors List");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            showAlert("Error", "Failed to open Doctors List popup.");
        }
    }

    public void validateInput(KeyEvent keyEvent) {
        String input = bloodLevel.getText();
        if (!input.matches("\\d{0,3}([.,]\\d{0,2})?")) {
            bloodLevel.setText(input.replaceAll("[^\\d.,]", ""));
        }
    }
}
