package org.example.utils;


import org.example.model.Flight;
import org.example.model.User;
import org.example.repository.FlightRepository;
import org.example.repository.UserRepository;

import java.time.LocalDateTime;

public class DataInitializer {

    public static void seed(UserRepository userRepo, FlightRepository flightRepo) {

        // 🔐 USERS
        userRepo.save(new User("user1", "pass1","admin"));
        userRepo.save(new User("user2", "pass2","user"));
        userRepo.save(new User("ana", "ana123","admin"));
        userRepo.save(new User("mihai", "mihai123","user"));

        // ✈️ FLIGHTS
        flightRepo.save(new Flight("Cluj", "Istanbul",
                LocalDateTime.now().plusDays(1), 100, 150));

        flightRepo.save(new Flight("Cluj", "Paris",
                LocalDateTime.now().plusDays(2), 80, 200));

        flightRepo.save(new Flight("Bucuresti", "Roma",
                LocalDateTime.now().plusDays(1), 60, 120));

        flightRepo.save(new Flight("Iasi", "Londra",
                LocalDateTime.now().plusDays(3), 50, 250));

        flightRepo.save(new Flight("Cluj", "Berlin",
                LocalDateTime.now().plusDays(4), 70, 180));

        flightRepo.save(new Flight("Timisoara", "Madrid",
                LocalDateTime.now().plusDays(2), 90, 220));

        flightRepo.save(new Flight("Cluj", "Roma",
                LocalDateTime.now().plusDays(5), 30, 140));

        flightRepo.save(new Flight("Bucuresti", "Istanbul",
                LocalDateTime.now().plusDays(1), 110, 160));
    }
}