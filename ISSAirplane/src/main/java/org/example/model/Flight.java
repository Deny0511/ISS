package org.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String departureCity;
    private String destinationCity;
    private LocalDateTime departureTime;
    private int availableSeats;
    private double price;

    public Flight() {}

    public Flight(String from, String to, LocalDateTime time, int seats, double price) {
        this.departureCity = from;
        this.destinationCity = to;
        this.departureTime = time;
        this.availableSeats = seats;
        this.price = price;
    }

    public int getId() { return id; }
    public String getDepartureCity() { return departureCity; }
    public String getDestinationCity() { return destinationCity; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public int getAvailableSeats() { return availableSeats; }
    public double getPrice() { return price; }


    public void setAvailableSeats(int seats) {
        this.availableSeats = seats;
    }
}