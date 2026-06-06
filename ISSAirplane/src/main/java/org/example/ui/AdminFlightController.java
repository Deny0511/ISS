package org.example.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import org.example.model.Flight;
import org.example.service.AirportService;
import org.example.service.FlightService;

import java.time.LocalDateTime;
import java.util.List;

public class AdminFlightController {

    @FXML
    private TableView<Flight> table;

    @FXML
    private TableColumn<Flight,String> colFrom;

    @FXML
    private TableColumn<Flight,String> colTo;

    @FXML
    private TableColumn<Flight,String> colDate;

    @FXML
    private TableColumn<Flight,Integer> colSeats;

    @FXML
    private TableColumn<Flight,Double> colPrice;

    @FXML
    private TextField departureField;

    @FXML
    private TextField destinationField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField seatsField;

    @FXML
    private TextField priceField;

    @FXML
    private Label messageLabel;

    private FlightService service;
    private AirportService
            airportService;

    public void setService(
            FlightService service, AirportService airportService) {

        this.service = service;
        this.airportService = airportService;

        loadData();
    }

    private void loadData(){

        List<Flight> flights =
                service.getAll();

        table.setItems(
                FXCollections
                        .observableArrayList(
                                flights
                        )
        );

        colFrom.setCellValueFactory(
                new PropertyValueFactory<>(
                        "departureCity"
                )
        );

        colTo.setCellValueFactory(
                new PropertyValueFactory<>(
                        "destinationCity"
                )
        );

        colDate.setCellValueFactory(
                new PropertyValueFactory<>(
                        "departureTime"
                )
        );

        colSeats.setCellValueFactory(
                new PropertyValueFactory<>(
                        "availableSeats"
                )
        );

        colPrice.setCellValueFactory(
                new PropertyValueFactory<>(
                        "price"
                )
        );

        table.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs,oldV,newV)->{

                            if(newV!=null){

                                departureField.setText(
                                        newV.getDepartureCity()
                                );

                                destinationField.setText(
                                        newV.getDestinationCity()
                                );

                                dateField.setText(
                                        newV.getDepartureTime()
                                                .toString()
                                );

                                seatsField.setText(
                                        String.valueOf(
                                                newV.getAvailableSeats()
                                        )
                                );

                                priceField.setText(
                                        String.valueOf(
                                                newV.getPrice()
                                        )
                                );
                            }

                        });

    }

    @FXML
    private void handleAdd(){

        try{

            Flight flight =
                    new Flight(

                            departureField.getText(),

                            destinationField.getText(),

                            LocalDateTime.parse(
                                    dateField.getText()
                            ),

                            Integer.parseInt(
                                    seatsField.getText()
                            ),

                            Double.parseDouble(
                                    priceField.getText()
                            )
                    );

            service.addFlight(
                    flight
            );

            loadData();

            messageLabel.setText(
                    "Flight added"
            );

        }
        catch(Exception e){

            messageLabel.setText(
                    e.getMessage()
            );
        }

    }

    @FXML
    private void handleUpdate(){

        Flight flight =
                table.getSelectionModel()
                        .getSelectedItem();

        if(flight==null)
            return;

        flight.setAvailableSeats(
                Integer.parseInt(
                        seatsField.getText()
                )
        );

        service.updateFlight(
                flight
        );

        loadData();

        messageLabel.setText(
                "Updated"
        );
    }

    @FXML
    private void handleDelete(){

        Flight flight =
                table.getSelectionModel()
                        .getSelectedItem();

        if(flight==null)
            return;

        service.deleteFlight(
                flight
        );

        loadData();

        messageLabel.setText(
                "Deleted"
        );

    }

    @FXML
    private void handleAirports()
            throws Exception{

        FXMLLoader loader =
                new FXMLLoader(
                        getClass()
                                .getResource(
                                        "/airports.fxml"
                                )
                );

        Scene scene =
                new Scene(
                        loader.load(),
                        700,
                        500
                );

        AirportController c =
                loader.getController();

        c.setService(
                airportService
        );

        Stage stage =
                new Stage();

        stage.setScene(
                scene
        );

        stage.show();
    }


}