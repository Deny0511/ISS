package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name="airports")

public class Airport {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )
    private int id;

    private String code;

    private String city;

    private String country;

    public Airport(){}

    public Airport(
            String code,
            String city,
            String country){

        this.code=code;
        this.city=city;
        this.country=country;
    }

    public int getId(){
        return id;
    }

    public String getCode(){
        return code;
    }

    public String getCity(){
        return city;
    }

    public String getCountry(){
        return country;
    }

    public void setCode(
            String code){

        this.code=code;
    }

    public void setCity(
            String city){

        this.city=city;
    }

    public void setCountry(
            String country){

        this.country=country;
    }

}