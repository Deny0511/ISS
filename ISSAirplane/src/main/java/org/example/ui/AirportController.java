package org.example.ui;

import javafx.collections
        .FXCollections;

import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.scene.control
        .cell
        .PropertyValueFactory;

import org.example.model
        .Airport;

import org.example.service
        .AirportService;

public class AirportController {

    @FXML
    private TableView<Airport>
            table;

    @FXML
    private TableColumn<
            Airport,
            String>

            colCode;

    @FXML
    private TableColumn<
            Airport,
            String>

            colCity;

    @FXML
    private TableColumn<
            Airport,
            String>

            colCountry;

    @FXML
    private TextField
            codeField;

    @FXML
    private TextField
            cityField;

    @FXML
    private TextField
            countryField;

    @FXML
    private Label
            messageLabel;

    private AirportService
            service;

    public void setService(
            AirportService s){

        service=s;

        loadData();
    }

    private void loadData(){

        table.setItems(
                FXCollections
                        .observableArrayList(
                                service
                                        .getAll()
                        )
        );

        colCode
                .setCellValueFactory(
                        new PropertyValueFactory<>
                                ("code")
                );

        colCity
                .setCellValueFactory(
                        new PropertyValueFactory<>
                                ("city")
                );

        colCountry
                .setCellValueFactory(
                        new PropertyValueFactory<>
                                ("country")
                );

    }

    @FXML
    private void handleAdd(){

        Airport airport=
                new Airport(

                        codeField
                                .getText(),

                        cityField
                                .getText(),

                        countryField
                                .getText()
                );

        service.addAirport(
                airport
        );

        loadData();

        messageLabel
                .setText(
                        "Added"
                );

    }

    @FXML
    private void handleUpdate(){

        Airport airport=
                table
                        .getSelectionModel()
                        .getSelectedItem();

        if(airport==null)
            return;

        airport.setCode(
                codeField
                        .getText()
        );

        airport.setCity(
                cityField
                        .getText()
        );

        airport.setCountry(
                countryField
                        .getText()
        );

        service.updateAirport(
                airport
        );

        loadData();

    }

    @FXML
    private void handleDelete(){

        Airport airport=
                table
                        .getSelectionModel()
                        .getSelectedItem();

        if(airport==null)
            return;

        service.deleteAirport(
                airport
        );

        loadData();
    }

}