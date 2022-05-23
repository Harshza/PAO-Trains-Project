package com.company.entities;

public abstract class Train {
    protected int id;
    protected String name;
    protected double speed;
    protected int numberOWaggons;
    protected double fuelCost;

    public Train(){}

    public Train(int id, String name, double speed, int numberOWaggons, double fuelCost) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.numberOWaggons = numberOWaggons;
        this.fuelCost = fuelCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getNumberOWaggons() {
        return numberOWaggons;
    }

    public void setNumberOWaggons(int numberOWaggons) {
        this.numberOWaggons = numberOWaggons;
    }

    public double getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(double fuelCost) {
        this.fuelCost = fuelCost;
    }

}
