package com.company.entities;

import java.util.ArrayList;
import java.util.TreeSet;

public class BunkBed extends FirstClass {
    // True => top bed; False => bottom bed
    private boolean bedPosition;

    public BunkBed() {}

    public BunkBed(int id, double price, int seat, Route route, ArrayList<String> mealsIncluded, boolean bedPosition) {
        super(id, price, seat, route, new TreeSet<>(mealsIncluded));
        this.bedPosition = bedPosition;
    }

    public boolean isBedPosition() {
        return bedPosition;
    }

    public void setBedPosition(boolean bedPosition) {
        this.bedPosition = bedPosition;
    }

    @Override
    public String toString() {
        final String[] result = {"Price: " + this.price + "\n"};
        result[0] += "Seat: " + this.seat + "\n";
        result[0] += route.toString();
        result[0] += "Meals included:\n";
        final String[] meals = {""};
        mealsIncluded.forEach(meal -> {
            meals[0] += meal + "\n";
        });
        result[0] += meals[0];
        if(this.bedPosition){
            result[0] += "Bed position: top\n";
        } else {
            result[0] += "Bed position: buttom\n";
        }
        return result[0];
    }
}
