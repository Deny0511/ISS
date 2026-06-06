package org.example.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.model.User;
import org.example.service.AirportService;
import org.example.service.AuthService;
import org.example.service.BookingService;
import org.example.service.FlightService;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private AuthService authService;
    private FlightService flightService;
    private BookingService bookingService;
    private Stage stage;
    private AirportService airportService;

    public void setServices(AuthService authService, FlightService flightService,BookingService bookingService,AirportService airportService, Stage stage) {
        this.authService = authService;
        this.flightService = flightService;
        this.bookingService = bookingService;
        this.airportService = airportService;
        this.stage = stage;
    }

    @FXML
    private void handleLogin() {

        try {

            User user = authService.login(
                    usernameField.getText(),
                    passwordField.getText()
            );

            if(user.getRole()
                    .equals("ADMIN")){

                FXMLLoader loader =
                        new FXMLLoader(
                                getClass()
                                        .getResource(
                                                "/adminFlights.fxml"
                                        )
                        );

                Scene scene =
                        new Scene(
                                loader.load(),
                                1000,
                                700
                        );

                AdminFlightController controller =
                        loader.getController();

                controller.setService(
                        flightService,
                        airportService

                );

                stage.setScene(
                        scene
                );

            }
            else{

                FXMLLoader loader =
                        new FXMLLoader(
                                getClass()
                                        .getResource(
                                                "/search.fxml"
                                        )
                        );

                Scene scene =
                        new Scene(
                                loader.load(),
                                1000,
                                700
                        );

                SearchController controller =
                        loader.getController();

                controller.setService(
                        flightService,
                        bookingService,
                        user
                );

                stage.setScene(
                        scene
                );
            }

        }
        catch (Exception e){

            messageLabel.setText(
                    "Invalid credentials"
            );
        }
    }
}