package com.company.entities;

import java.util.ArrayList;

public class Boogie extends Train {
    private ArrayList<String> materials;
    private ArrayList<Double> quantity;

    public Boogie(){}

    public Boogie(int id, String name, double speed, int numberOWaggons, double fuelCost, ArrayList<String> materials, ArrayList<Double> quantity) {
        super(id, name, speed, numberOWaggons, fuelCost);
        this.materials = materials;
        this.quantity = quantity;
    }

    public ArrayList<String> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<String> materials) {
        this.materials = materials;
    }

    public ArrayList<Double> getQuantity() {
        return quantity;
    }

    public void setQuantity(ArrayList<Double> quantity) {
        this.quantity = quantity;
    }

    @Override
    public void trainInfo() {
        System.out.println("Train: " + this.name);
        System.out.println("Type: " + "boogie");
        System.out.println("Speed: " + this.speed);
        System.out.println("Fuel Cost: " + this.fuelCost);
        for(int i = 0; i < materials.size(); ++i){
            System.out.println("Material:" + materials.get(i) + "Quantity:" + quantity.get(i));
        }
    }
}
