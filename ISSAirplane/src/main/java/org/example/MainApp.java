package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.repository.AirportRepository;
import org.example.repository.BookingRepository;
import org.example.repository.FlightRepository;
import org.example.repository.UserRepository;
import org.example.service.AirportService;
import org.example.service.AuthService;
import org.example.service.BookingService;
import org.example.service.FlightService;
import org.example.ui.LoginController;
import org.example.utils.DataInitializer;

public class MainApp extends Application {

    private AuthService authService;
    private FlightService flightService;
    private BookingService bookingService;
    private AirportService airportService;

    @Override
    public void start(Stage stage) throws Exception {

        UserRepository userRepo = new UserRepository();
        FlightRepository flightRepo = new FlightRepository();
        BookingRepository bookingRepo = new BookingRepository();
        AirportRepository airportRepo = new AirportRepository();

        authService = new AuthService(userRepo);
        flightService = new FlightService(flightRepo);
        bookingService = new BookingService(bookingRepo, flightRepo);
        airportService = new AirportService(airportRepo);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Scene scene = new Scene(loader.load(), 900, 700);

        LoginController controller = loader.getController();
        controller.setServices(
                authService,
                flightService,
                bookingService,
                airportService,
                stage );

        stage.setTitle("Flight App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}