package com.company.services;

import com.company.entities.Station;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public interface StationInterface {
    public ArrayList<Station> getStations();

    public Station getStationById(int index);

    public void updateStation(int index, Station station);

    public void addStation(Station station);

    public void deteleStation(int index);

    Station readStation() throws ParseException;
}
