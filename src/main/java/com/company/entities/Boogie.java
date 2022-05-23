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
    public String toString() {
        String result = "Train: " + this.name + "\n" + "Type: " + "boogie" + "\n" + "Speed: " + this.speed + "\n" + "Fuel Cost: " + this.fuelCost + "\n";
        for(int i = 0; i < materials.size(); ++i){
            result += "Material:" + materials.get(i) + "\n" +  "Quantity:" + quantity.get(i) + "\n";
        }
        return result;
    }
}
