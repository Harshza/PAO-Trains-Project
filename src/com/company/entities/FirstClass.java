package com.company.entities;

import java.util.TreeSet;

public class FirstClass extends Ticket {
    protected TreeSet<String> mealsIncluded;

    public FirstClass() {}

    public TreeSet<String> getMealsIncluded() {
        return mealsIncluded;
    }

    public void setMealsIncluded(TreeSet<String> mealsIncluded) {
        this.mealsIncluded = mealsIncluded;
    }


    public FirstClass(int id, double price, int seat, Route route, TreeSet<String> mealsIncluded) {
        super(id, price, seat, route);
        this.mealsIncluded = mealsIncluded;
    }

    @Override
    public void ticketInfo() {
        System.out.println("Price: " + this.price);
        System.out.println("Seat: " + this.seat);
        this.route.routeInfo();
        System.out.println("Meals included:");
        mealsIncluded.forEach(System.out::println);
    }
}
