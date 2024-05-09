package com.example.projetjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorListPage implements Initializable {
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
    }
    private void refreshTable() {
        // Assuming you have a method getAllDoctors() in your ServiceDB class to retrieve all doctors
        List<Doctor> doctorList = service.getAllDoctors();
        ObservableList<Doctor> data = FXCollections.observableArrayList(doctorList);
        table.setItems(data);
    }

    private void initializeTableColumns() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}
