package com.company.entities;

import java.util.List;

public class Route {
    private int id;
    private List<Train> trains;
    private Station destination;
    private Station origin;
    double distance;

    public Route(){}

    public Route(int id, List<Train> trains, Station destination, Station origin, double distance) {
        this.id = id;
        this.trains = trains;
        this.destination = destination;
        this.origin = origin;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public Station getOrigin() {
        return origin;
    }

    public void setOrigin(Station origin) {
        this.origin = origin;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String toString(){
        String result = this.origin.toString();
        result += this.destination.toString();
        result += "Distance: " + this.distance + "\n";
        for(int i = 0; i < trains.size(); ++i){
            if(trains.get(i) instanceof Boogie){
                Boogie boogie = (Boogie) trains.get(i);
                result += boogie.toString();
            }
            else if(trains.get(i) instanceof PassengerTrain){
                PassengerTrain passengerTrain = (PassengerTrain) trains.get(i);
                result += passengerTrain.toString();
            }
        }
        return result;
    }


}
