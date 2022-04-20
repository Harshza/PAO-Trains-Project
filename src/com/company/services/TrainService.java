package com.company.services;

import com.company.entities.Train;
import com.company.entities.Boogie;
import com.company.entities.PassengerTrain;

import java.util.ArrayList;
import java.util.Scanner;

public class TrainService implements TrainInterface{
    private ArrayList<Train> trains = new ArrayList<>();

    private static TrainService instance;

    public static TrainService getInstance(){
        if(instance == null){
            instance = new TrainService();
        }
        return instance;
    }

    public ArrayList<Train> getTrains() {
        ArrayList<Train> trainsCopy = new ArrayList<>();
        trainsCopy.addAll(this.trains);
        return trainsCopy;
    }

    public Train getTicketById(int index){
        Train train = null;
        for(int i = 0; i < this.trains.size(); ++i){
            if(this.trains.get(i).getId() == index){
                if(this.trains.get(i) instanceof Boogie){
                    train = (Boogie) this.trains.get(i);
                }
                else if (this.trains.get(i) instanceof PassengerTrain){
                    train = (PassengerTrain) this.trains.get(i);
                }
            }
        }
        return train;
    }

    public void updateTrain(int index, Train train){
        for(int i = 0; i < this.trains.size(); ++i){
            if(this.trains.get(i).getId() == index){
                this.trains.remove(i);
                this.trains.add(index, train);
                break;
            }
        }
    }

    public void addTrain(Train train){
        this.trains.add(train);
    }

    public void deteleTrain(int index){
        for(int i = 0; i < this.trains.size(); ++i){
            if(this.trains.get(i).getId() == index) {
                this.trains.remove(i);
                break;
            }
        }
    }

    public Train readTrain(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("0 - Boogie");
        System.out.println("1 - Passenger Train");
        int option = scanner.nextInt();
        try {
            option = scanner.nextInt();
        } catch (Exception e){
            System.out.println("Provide int between 0 and 1");
            option = scanner.nextInt();
        }
        if(option == 0){
            Boogie boogie = new Boogie();
            System.out.println("id = ");
            int id;
            try {
                id = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Provide int");
                id = scanner.nextInt();
            }
            boogie.setId(id);

            System.out.println("name = ");
            boogie.setName(scanner.next());

            System.out.println("speed = ");
            double speed;
            try {
                speed = scanner.nextDouble();
            } catch (Exception e){
                System.out.println("Provide double");
                speed = scanner.nextDouble();
            }

            boogie.setSpeed(speed);

            System.out.println("number of waggons = ");
            int wangons;
            try {
                wangons = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Provide int");
                wangons = scanner.nextInt();
            }
            boogie.setNumberOWaggons(wangons);

            System.out.println("fuel cost = ");
            double fuel;
            try {
                fuel = scanner.nextDouble();
            } catch (Exception e){
                System.out.println("Provide double");
                fuel = scanner.nextDouble();
            }
            boogie.setFuelCost(fuel);

            System.out.println("number of materials and quantities = ");
            int nr;
            try {
                nr = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Provide int");
                nr = scanner.nextInt();
            }
            ArrayList<String> mat = new ArrayList<>();
            ArrayList<Double> qua = new ArrayList<>();
            System.out.println("materials and quantities = ");
            for(int i = 0; i < nr; ++i){
                System.out.println("material = ");
                mat.add(scanner.next());
                System.out.println("quantity = ");
                qua.add(scanner.nextDouble());
            }
            boogie.setMaterials(mat);
            boogie.setQuantity(qua);

            return boogie;
        } else {
            PassengerTrain passengerTrain = new PassengerTrain();
            System.out.println("id = ");
            int id;
            try {
                id = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Provide int");
                id = scanner.nextInt();
            }
            passengerTrain.setId(id);

            System.out.println("name = ");
            passengerTrain.setName(scanner.next());

            System.out.println("speed = ");
            double speed;
            try {
                speed = scanner.nextDouble();
            } catch (Exception e){
                System.out.println("Provide double");
                speed = scanner.nextDouble();
            }
            passengerTrain.setSpeed(speed);

            System.out.println("number of waggons = ");
            int nr;
            try {
                nr = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Provide int");
                nr = scanner.nextInt();
            }

            passengerTrain.setNumberOWaggons(nr);

            System.out.println("fuel cost = ");
            double fuel;
            try {
                fuel = scanner.nextDouble();
            } catch (Exception e){
                System.out.println("Provide double");
                fuel = scanner.nextDouble();
            }

            passengerTrain.setFuelCost(fuel);

            System.out.println("number of seats = ");
            try {
                nr = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Provide int");
                nr = scanner.nextInt();
            }

            passengerTrain.setNumberOfSeats(nr);

            System.out.println("number of classes = ");
            try {
                nr = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Provide int");
                nr = scanner.nextInt();
            }
            passengerTrain.setNumberOfClasses(nr);

            return passengerTrain;
        }
    }
}
