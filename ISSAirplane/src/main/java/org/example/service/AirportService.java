package org.example.service;

import org.example.model.Airport;
import org.example.repository.AirportRepository;

import java.util.List;

public class AirportService {

    private final
    AirportRepository repo;

    public AirportService(
            AirportRepository repo){

        this.repo=repo;
    }

    public List<Airport>
    getAll(){

        return repo.findAll();
    }

    public void addAirport(
            Airport airport){

        repo.save(
                airport
        );
    }

    public void updateAirport(
            Airport airport){

        repo.update(
                airport
        );
    }

    public void deleteAirport(
            Airport airport){

        repo.delete(
                airport
        );
    }

}