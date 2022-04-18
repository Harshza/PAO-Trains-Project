package com.company.entities;

import java.util.Date;

public class Station {
    private int id;
    private String name;
    private String city;
    private String country;
    private Date establishmentDate;

    public Station(){}

    public Station(int id, String name, String city, String country, Date establishmentDate) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.establishmentDate = establishmentDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public void stationInfo(){
        System.out.println("Name: " + this.name);
        System.out.println("City: " + this.city);
        System.out.println("Country: " + this.country);
        System.out.println("Data of Establishment: " + this.establishmentDate);
    }
}
