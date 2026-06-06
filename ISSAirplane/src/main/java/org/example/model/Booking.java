package org.example.model;
import jakarta.persistence.*;


@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Flight flight;

    private int seats;

    private String tourists;

    private boolean paid;

    public Booking() {}

    public Booking(User user, Flight flight,
                   int seats, String tourists, boolean paid) {
        this.user = user;
        this.flight = flight;
        this.seats = seats;
        this.tourists = tourists;
        this.paid = paid;
    }

    public int getId() { return id; }
    public User getUser() { return user; }
    public Flight getFlight() { return flight; }
    public int getSeats() { return seats; }
    public String getTourists() { return tourists; }
    public boolean isPaid() { return paid; }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}