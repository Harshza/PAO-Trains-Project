package com.company.services;

import com.company.entities.Station;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class StationService implements StationInterface{
    private ArrayList<Station> stations = new ArrayList<>();

    private static StationService instance;

    public static StationService getInstance(){
        if(instance == null){
            instance = new StationService();
        }
        return instance;
    }

    public ArrayList<Station> getStations() {
        ArrayList<Station> stationsCopy = new ArrayList<>();
        stationsCopy.addAll(this.stations);
        return stationsCopy;
    }

    public Station getStationById(int index){
        Station station = new Station();
        for(int i = 0; i < this.stations.size(); ++i){
            if(this.stations.get(i).getId() == index){
                station = this.stations.get(i);
            }
        }
        return station;
    }

    public void updateStation(int index, Station station){
        for(int i = 0; i < this.stations.size(); ++i){
            if(this.stations.get(i).getId() == index){
                this.stations.remove(i);
                this.stations.add(index, station);
                break;
            }
        }
    }

    public void addStation(Station station){
        this.stations.add(station);
    }

    public void deteleStation(int index){
        for(int i = 0; i < this.stations.size(); ++i){
            if(this.stations.get(i).getId() == index) {
                this.stations.remove(i);
                break;
            }
        }
    }

    public Station readStation() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        Station station = new Station();
        System.out.println("id = ");
        station.setId(scanner.nextInt());

        System.out.println("name = ");
        station.setName(scanner.next());

        System.out.println("city = ");
        station.setCity(scanner.next());

        System.out.println("country = ");
        station.setCountry(scanner.next());

        System.out.println("date = ");
        String date = scanner.next();
        Date date_date = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        station.setEstablishmentDate(date_date);

        return station;
    }

}
