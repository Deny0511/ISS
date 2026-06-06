package org.example.service;

import org.example.model.Booking;
import org.example.model.Flight;
import org.example.model.User;
import org.example.repository.BookingRepository;
import org.example.repository.FlightRepository;

import java.util.List;

public class BookingService {

    private final BookingRepository bookingRepo;
    private final FlightRepository flightRepo;

    public BookingService(BookingRepository bookingRepo,
                          FlightRepository flightRepo) {
        this.bookingRepo = bookingRepo;
        this.flightRepo = flightRepo;
    }

    public void bookFlight(User user,
                           Flight flight,
                           int seats,
                           String tourists) {

        if (flight.getAvailableSeats() < seats) {
            throw new RuntimeException(
                    "Not enough seats available");
        }

        flight.setAvailableSeats(
                flight.getAvailableSeats() - seats
        );

        flightRepo.update(flight);

        Booking booking =
                new Booking(user, flight,
                        seats, tourists, false);

        bookingRepo.save(booking);
    }

    public List<Booking> getBookings(User user) {
        return bookingRepo.findByUser(user);
    }

    public void processPayment(Booking booking) {
        booking.setPaid(true);
        bookingRepo.update(booking);
    }

    public void cancelBooking(
            Booking booking){

        Flight flight =
                booking.getFlight();

        flight.setAvailableSeats(
                flight.getAvailableSeats()
                        + booking.getSeats()
        );

        flightRepo.update(flight);

        bookingRepo.delete(booking);
    }

}