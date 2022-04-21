package com.company.entities;

public class PassengerTrain extends Train {
    private int numberOfSeats;
    private int numberOfClasses;

    public PassengerTrain(){}

    public PassengerTrain(int id, String name, double speed, int numberOWaggons, double fuelCost, int numberOfSeats, int numberOfClasses) {
        super(id, name, speed, numberOWaggons, fuelCost);
        this.numberOfSeats = numberOfSeats;
        this.numberOfClasses = numberOfClasses;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    @Override
    public String toString() {
        String result = "Train: " + this.name + "\n" + "Type: " + "boogie" + "\n" + "Speed: " + this.speed + "\n" + "Fuel Cost: " + this.fuelCost + "\n";
        result += "Number of seats: " + this.numberOfSeats + "\n";
        result += "Number of classes: " + this.numberOfClasses + "\n";
        return result;
    }

}
