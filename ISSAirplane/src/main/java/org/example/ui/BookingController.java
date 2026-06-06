package org.example.ui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Booking;
import org.example.model.User;
import org.example.service.BookingService;

import java.util.List;

public class BookingController {

    @FXML
    private TableView<Booking> bookingsTable;

    @FXML
    private TableColumn<Booking, String> colFrom;

    @FXML
    private TableColumn<Booking, String> colDestination;

    @FXML
    private TableColumn<Booking, String> colDate;

    @FXML
    private TableColumn<Booking, Integer> colSeats;

    @FXML
    private TableColumn<Booking, String> colTourists;

    @FXML
    private TableColumn<Booking, Double> colPrice;

    @FXML
    private TableColumn<Booking, Boolean> colPaid;

    @FXML
    private Label messageLabel;


    private BookingService bookingService;
    private User user;

    public void setService(BookingService service,
                           User user) {

        this.bookingService = service;
        this.user = user;

        loadData();
    }

    private void loadData() {

        List<Booking> bookings =
                bookingService.getBookings(user);

        bookingsTable.setItems(
                FXCollections.observableArrayList(bookings)
        );

        colFrom.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue()
                                .getFlight()
                                .getDepartureCity()
                )
        );

        colDestination.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue()
                                .getFlight()
                                .getDestinationCity()
                )
        );

        colDate.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue()
                                .getFlight()
                                .getDepartureTime()
                                .toString()
                )
        );

        colSeats.setCellValueFactory(
                new PropertyValueFactory<>("seats")
        );

        colTourists.setCellValueFactory(
                new PropertyValueFactory<>("tourists")
        );

        colPrice.setCellValueFactory(
                data -> new SimpleObjectProperty<>(
                        data.getValue()
                                .getFlight()
                                .getPrice()
                )
        );

        colPaid.setCellValueFactory(
                new PropertyValueFactory<>("paid")
        );
    }

    @FXML
    private void handlePay() {

        Booking booking =
                bookingsTable.getSelectionModel()
                        .getSelectedItem();

        if (booking == null) {
            return;
        }

        bookingService.processPayment(booking);

        messageLabel.setText(
                "Payment successful!"
        );

        loadData();
    }



    @FXML
    private void handleCancel(){

        Booking booking =
                bookingsTable
                        .getSelectionModel()
                        .getSelectedItem();

        if(booking == null){
            return;
        }

        bookingService.cancelBooking(
                booking
        );

        messageLabel.setText(
                "Booking cancelled"
        );

        loadData();
    }
}