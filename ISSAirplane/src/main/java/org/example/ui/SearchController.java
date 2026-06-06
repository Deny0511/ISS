package org.example.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.model.Flight;
import org.example.model.User;
import org.example.service.BookingService;
import org.example.service.FlightService;

import java.util.List;

public class SearchController {

    @FXML private TextField departureField;
    @FXML private TextField destinationField;

    @FXML private TableView<Flight> table;
    @FXML private TableColumn<Flight, String> colFrom;
    @FXML private TableColumn<Flight, String> colTo;
    @FXML private TableColumn<Flight, String> colTime;
    @FXML private TableColumn<Flight, Integer> colSeats;
    @FXML private TableColumn<Flight, Double> colPrice;

    @FXML private Label detailsLabel;

    @FXML
    private TextField seatsField;

    @FXML
    private TextArea touristsArea;

    private FlightService flightService;
    private BookingService bookingService;
    private ObservableList<Flight> allFlights;
    private User user;

    public void setService(FlightService service,BookingService bookingService, User user) {
        this.flightService = service;
        this.bookingService = bookingService;
        this.user = user;
        loadData();
    }

    private void loadData() {
        List<Flight> flights = flightService.getAll();
        allFlights = FXCollections.observableArrayList(flights);
        table.setItems(allFlights);

        colFrom.setCellValueFactory(new PropertyValueFactory<>("departureCity"));
        colTo.setCellValueFactory(new PropertyValueFactory<>("destinationCity"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        colSeats.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.setOnMouseClicked(e -> {
            Flight f = table.getSelectionModel().getSelectedItem();
            if (f != null) {
                detailsLabel.setText(
                        "Seats: " + f.getAvailableSeats() +
                                " | Price: " + f.getPrice()
                );
            }
        });
    }

    @FXML
    private void handleSearch() {
        String from = departureField.getText();
        String to = destinationField.getText();

        ObservableList<Flight> filtered = allFlights.filtered(f -> {
            boolean matchesFrom = from.isEmpty() ||
                    f.getDepartureCity().toLowerCase().contains(from.toLowerCase());

            boolean matchesTo = to.isEmpty() ||
                    f.getDestinationCity().toLowerCase().contains(to.toLowerCase());

            return matchesFrom && matchesTo;
        });

        table.setItems(filtered);
    }

    @FXML
    private void handleReset() {
        table.setItems(allFlights);
    }


    @FXML
    private void handleMyBookings() throws Exception {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource("/bookings.fxml")
                );

        Scene scene =
                new Scene(loader.load(), 700, 500);

        BookingController controller =
                loader.getController();

        controller.setService(
                bookingService,
                user
        );

        Stage stage = new Stage();
        stage.setTitle("My Bookings");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBook() {

        try {

            Flight selectedFlight =
                    table.getSelectionModel().getSelectedItem();

            if (selectedFlight == null) {
                detailsLabel.setText("Select a flight");
                return;
            }

            int seats =
                    Integer.parseInt(seatsField.getText());

            String tourists =
                    touristsArea.getText();

            bookingService.bookFlight(
                    user,
                    selectedFlight,
                    seats,
                    tourists
            );

            detailsLabel.setText(
                    "Booking successful!"
            );

            loadData();

        } catch (Exception e) {

            detailsLabel.setText(
                    e.getMessage()
            );
        }
    }

}