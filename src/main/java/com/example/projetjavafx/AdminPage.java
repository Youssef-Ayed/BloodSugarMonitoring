package com.example.projetjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminPage implements Initializable {
    @FXML
    private TextField locationTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TableColumn<Doctor, Integer> idColumn;

    @FXML
    private TableColumn<Doctor, String> nameColumn;

    @FXML
    private TableColumn<Doctor, String> locationColumn;

    @FXML
    private TableColumn<Doctor, Integer> phoneColumn;

    @FXML
    private TableColumn<Doctor, Integer> priceColumn;

    @FXML
    private TableView<Doctor> table;

    ServiceDB service = new ServiceDB();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTableColumns();
        refreshTable();
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Set text fields with row values
                locationTextField.setText(newSelection.getLocation());
                nameTextField.setText(newSelection.getName());
                phoneTextField.setText(String.valueOf(newSelection.getPhone()));
                priceTextField.setText(String.valueOf(newSelection.getPrice()));
            }
        });
    }

    private void refreshTable() {
        // Assuming you have a method getAllDoctors() in your ServiceDB class to retrieve all doctors
        List<Doctor> doctorList = service.getAllDoctors();
        ObservableList<Doctor> data = FXCollections.observableArrayList(doctorList);
        table.setItems(data);
    }

    private void initializeTableColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void add(ActionEvent event) {
        try {
            String name = nameTextField.getText();
            String location = locationTextField.getText();
            int phone = Integer.parseInt(phoneTextField.getText());
            int price = Integer.parseInt(priceTextField.getText());

            Doctor newDoctor = new Doctor();
            newDoctor.setName(name);
            newDoctor.setLocation(location);
            newDoctor.setPhone(phone);
            newDoctor.setPrice(price);

            boolean success = service.createDoctor(newDoctor);
            if (success) {
                showAlert("Success", "Doctor added successfully.");
                refreshTable();
            } else {
                showAlert("Error", "Failed to add doctor.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numeric values for phone and price.");
        }
    }

    @FXML
    void delete(ActionEvent event) {
        // Assuming you have a Doctor object selectedDoctor representing the selected row in the table
        Doctor selectedDoctor = (Doctor) table.getSelectionModel().getSelectedItem();
        if (selectedDoctor == null) {
            showAlert("Error", "Please select a doctor to delete.");
            return;
        }

        boolean success = service.deleteDoctor(selectedDoctor.getId());
        if (success) {
            showAlert("Success", "Doctor deleted successfully.");
            refreshTable();
        } else {
            showAlert("Error", "Failed to delete doctor.");
        }
    }


    @FXML
    void update(ActionEvent event) {
        // Assuming you have a Doctor object selectedDoctor representing the selected row in the table
        Doctor selectedDoctor = (Doctor) table.getSelectionModel().getSelectedItem();
        if (selectedDoctor == null) {
            showAlert("Error", "Please select a doctor to update.");
            return;
        }

        try {
            String name = nameTextField.getText();
            String location = locationTextField.getText();
            int phone = Integer.parseInt(phoneTextField.getText());
            int price = Integer.parseInt(priceTextField.getText());

            selectedDoctor.setName(name);
            selectedDoctor.setLocation(location);
            selectedDoctor.setPhone(phone);
            selectedDoctor.setPrice(price);

            boolean success = service.updateDoctor(selectedDoctor);
            if (success) {
                showAlert("Success", "Doctor updated successfully.");
                refreshTable();
            } else {
                showAlert("Error", "Failed to update doctor.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numeric values for phone and price.");
        }
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
