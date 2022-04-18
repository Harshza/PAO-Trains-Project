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
    public void trainInfo() {
        System.out.println("Train: " + this.name);
        System.out.println("Type: " + "passanger train");
        System.out.println("Speed: " + this.speed);
        System.out.println("Fuel Cost: " + this.fuelCost);
        System.out.println("Number of seats: " + this.numberOfSeats);
        System.out.println("Number of classes: " + this.numberOfClasses);
    }
}
