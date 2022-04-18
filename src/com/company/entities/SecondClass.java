package com.company.entities;

public class SecondClass extends Ticket {
    private double discount;

    public SecondClass() {}

    public SecondClass(int id, double price, int seat, Route route, double discount) {
        super(id, price, seat, route);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public void ticketInfo() {
        System.out.println("Price: " + this.price);
        System.out.println("Seat: " + this.seat);
        this.route.routeInfo();
        System.out.println("Discount: " + this.discount);
    }
}
