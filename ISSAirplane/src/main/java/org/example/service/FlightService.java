package org.example.service;



import org.example.model.Flight;
import org.example.repository.FlightRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {

    private final FlightRepository repo;

    public FlightService(FlightRepository repo) {
        this.repo = repo;
    }

    public List<Flight> search(String destination, LocalDate date) {
        return repo.findAll().stream()
                .filter(f -> f.getDestinationCity().equals(destination)
                        && f.getDepartureTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    public Flight getDetails(int id) {
        return repo.findById(id);
    }

    public List<Flight> getAll() {
        return repo.findAll();
    }


    public void addFlight(
            Flight flight){

        repo.save(flight);
    }

    public void updateFlight(
            Flight flight){

        repo.update(flight);
    }

    public void deleteFlight(
            Flight flight){

        repo.delete(flight);
    }

}