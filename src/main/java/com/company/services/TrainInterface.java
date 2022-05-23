package com.company.services;

import com.company.entities.Boogie;
import com.company.entities.PassengerTrain;
import com.company.entities.Train;

import java.util.ArrayList;
import java.util.Scanner;

public interface TrainInterface {
    public ArrayList<Train> getTrains();

    public Train getTicketById(int index);

    public void updateTrain(int index, Train train);

    public void addTrain(Train train);

    public void deteleTrain(int index);

    public Train readTrain();
}
