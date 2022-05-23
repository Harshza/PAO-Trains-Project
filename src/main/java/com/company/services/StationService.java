package com.company.services;

import com.company.entities.Station;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StationService implements StationInterface, CSVReaderWriter<Station>{
    private ArrayList<Station> stations = new ArrayList<>();

    private StationService(){
        read();
    }
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
//        System.out.println("id = ");
//        station.setId(scanner.nextInt());
        station.setId(getMaxId() + 1);

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

    @Override
    public String getAntet() {
        return "Id,Name,City,Country,Establishment Date\n";
    }

    public int getMaxId(){
        int max = 0;
        for(int i = 0; i < stations.size(); ++i){
            if(stations.get(i).getId() > max){
                max = stations.get(i).getId();
            }
        }
        return max;
    }
    @Override
    public Station processLine(String line) throws ParseException {
        // "Id,Name,City,Country,Establishment Date";
        // 1,Gara de Nord,Bucuresti,Romania,20/09/1960
        String[] fields = line.split(separator);
        int id = 0;
        if(Objects.equals(fields[0], "null")){
            id = getMaxId();
        } else {
            try {
                id = Integer.parseInt(fields[0]);
            } catch (Exception e) {
                System.out.println("The id must be an int");
            }
        }
        String name = fields[1];

        String city = fields[2];

        String country = fields[3];

        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fields[4]);

        return new Station(id, name, city, country, date);
    }

    @Override
    public String getFileName() {
        String path = "src/main/java/com/company/resources/data - Station.csv";
        return path;
    }

    @Override
    public String convertObjectToString(Station object) {
        Date date = object.getEstablishmentDate();
        String dateString = new SimpleDateFormat("dd/MM/yyyy").format(date);
        String line = object.getId() + separator + object.getName() + separator + object.getCity() + separator + object.getCountry() + separator+ dateString + "\n";
        return line;
    }

    @Override
    public void initList(List<Station> objects) {
        stations = new ArrayList<Station>(objects);
    }
}
