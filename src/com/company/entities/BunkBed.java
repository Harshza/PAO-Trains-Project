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
    public void ticketInfo() {
        System.out.println("Price: " + this.price);
        System.out.println("Seat: " + this.seat);
        this.route.routeInfo();
        System.out.println("Meals included:");
        mealsIncluded.forEach(System.out::println);
        if(this.bedPosition){
            System.out.println("Bed position: top");
        } else {
            System.out.println("Bed position: buttom");
        }
    }
}
