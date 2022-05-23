package com.company.services;

import com.company.entities.*;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RouteService implements RouteInterface, CSVReaderWriter<Route>{
    private ArrayList<Route> routes = new ArrayList<>();

    private RouteService(){
        read();
    }
    private static RouteService instance;

    public static RouteService getInstance(){
        if(instance == null){
            instance = new RouteService();
        }
        return instance;
    }

    public ArrayList<Route> getRoutes() {
        ArrayList<Route> routesCopy = new ArrayList<>();
        routesCopy.addAll(this.routes);
        return routesCopy;
    }

    public Route getRouteById(int index){
        Route route = new Route();
        for(int i = 0; i < this.routes.size(); ++i){
            if(this.routes.get(i).getId() == index){
                route = this.routes.get(i);
            }
        }
        return route;
    }

    public void updateRoute(int index, Route route){
        for(int i = 0; i < this.routes.size(); ++i){
            if(this.routes.get(i).getId() == index){
                this.routes.remove(i);
                this.routes.add(index, route);
                break;
            }
        }
    }

    public void addRoute(Route route){
        this.routes.add(route);
    }

    public void deteleRoute(int index){
        for(int i = 0; i < this.routes.size(); ++i){
            if(this.routes.get(i).getId() == index) {
                this.routes.remove(i);
                break;
            }
        }
    }

    public Route readRoute() throws ParseException {
        Route route = new Route();
        Scanner scanner = new Scanner(System.in);
        TrainService trainService = TrainService.getInstance();
        StationService stationService = StationService.getInstance();
        System.out.println("id = ");
        route.setId(scanner.nextInt());

        List<Train> trains = new ArrayList<>();
        System.out.println("number of trains = ");
        int nr = scanner.nextInt();
        System.out.println("trains = ");
        for(int i = 0; i < nr; ++i){
            Train train = trainService.readTrain();
            trains.add(train);
            trainService.addTrain(train);
        }
        route.setTrains(trains);

        System.out.println("destination = ");
        Station dst = stationService.readStation();
        route.setDestination(dst);
        stationService.addStation(dst);

        System.out.println("origin = ");
        Station src = stationService.readStation();
        stationService.addStation(src);
        route.setOrigin(src);

        System.out.println("distance = ");
        route.setDistance(scanner.nextDouble());

        return route;
    }

    @Override
    public String getAntet() {
        return "";
    }

    @Override
    public Route processLine(String line) throws ParseException {
        //Id,Destination Station Id,Origin Station Id,Distance
        String[] fields = line.split(separator);
        //System.out.println(line);
        int id = 0;
        try{
            id = Integer.parseInt(fields[0]);
        } catch (Exception e){
            System.out.println("The id must be an int");
        }

        int idDest = 0;
        try{
            idDest = Integer.parseInt(fields[1]);
        } catch (Exception e){
            System.out.println("The id must be an int");
        }

        int idSrc = 0;
        try{
            idSrc = Integer.parseInt(fields[2]);
        } catch (Exception e){
            System.out.println("The id must be an int");
        }
        double dist = 0.0;
        try{
            dist = Double.parseDouble(fields[3]);
        } catch (Exception e){
            System.out.println("The dist must be a double");
        }
        StationService stationService = StationService.getInstance();
        Station dest = new Station();
        Station src = new Station();
        dest.setId(-1);
        src.setId(-1);
        try{
            dest = stationService.getStationById(idDest);
        } catch (Exception e){
            System.out.println("The station doesnt exist");
        }

        try{
            src = stationService.getStationById(idSrc);
        } catch (Exception e){
            System.out.println("The station doesnt exist");
        }
        return new Route(id, new ArrayList<>(), dest, src, dist);
    }

    @Override
    public String getFileName() {
        return "src/main/java/com/company/resources/data - Route.csv";
    }

    @Override
    public String convertObjectToString(Route object) {
        String res1;
        if(object.getDestination().getId() == -1){
            res1 = "null";
        } else {
            res1 = String.valueOf(object.getDestination().getId());
        }

        String res2;
        if(object.getOrigin().getId() == -1){
            res2= "null";
        } else {
            res2= String.valueOf(object.getDestination().getId());
        }
        String line = object.getId() + separator + res1 + separator + res2 + separator + object.getDistance() + "\n";
        return line;
    }

    @Override
    public void initList(List<Route> objects) {
        routes = new ArrayList<>(objects);
    }

    @Override
    public List<Route> read(){
        String fileName = this.getFileName();
        File file = new File(fileName);
        String extraFileName = "src/main/java/com/company/resources/data - Route_Info.csv";
        File extraFile = new File(extraFileName);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<Route> result;

            try {
                List<Route> resultLines = new ArrayList<Route>();
                bufferedReader.readLine(); // skip first line
                String currentLine = bufferedReader.readLine();

                while (true) {
                    if (currentLine == null) {
                        result = resultLines;
                        break;
                    }
                    Route obj = this.processLine(currentLine);
                    resultLines.add(obj);
                    currentLine = bufferedReader.readLine();
                }
                BufferedReader extra = new BufferedReader(new FileReader(extraFile));
                try{
                    extra.readLine();
                    String line = extra.readLine();
                    while (true) {
                        if (line == null) {
                            break;
                        }
                        String[] fields = line.split(separator);
                        int id = Integer.parseInt(fields[0]);
                        Route route = resultLines.stream()
                                .filter(r -> r.getId() == id)
                                .findAny()
                                .orElse(null);
                        if(route != null){
                            if(route.getTrains().size() == 0){
                                List<Train> objs = new ArrayList<Train>();
                                int idTrain = -1;
                                try{
                                    idTrain = Integer.parseInt(fields[1]);
                                } catch (Exception e){
                                    System.out.println("The id must be an int");
                                }
                                TrainService trainService = TrainService.getInstance();
                                for(int i = 0; i < trainService.getTrains().size(); ++i){
                                    Train train = trainService.getTrains().get(i);
                                    if(train.getId() == idTrain){
                                        if(train instanceof Boogie){
                                            Boogie boogie = (Boogie) train;
                                            objs.add(boogie);
                                        } else if(train instanceof PassengerTrain){
                                            PassengerTrain pass = (PassengerTrain) train;
                                            objs.add(pass);
                                        }
                                        break;
                                    }
                                }
                                route.setTrains(objs);
                            } else {
                                List<Train> objs = route.getTrains();
                                int idTrain = -1;
                                try{
                                    idTrain = Integer.parseInt(fields[1]);
                                } catch (Exception e){
                                    System.out.println("The id must be an int");
                                }
                                TrainService trainService = TrainService.getInstance();
                                for(int i = 0; i < trainService.getTrains().size(); ++i){
                                    Train train = trainService.getTrains().get(i);
                                    if(train.getId() == idTrain){
                                        if(train instanceof Boogie){
                                            Boogie boogie = (Boogie) train;
                                            objs.add(boogie);
                                        } else if(train instanceof PassengerTrain){
                                            PassengerTrain pass = (PassengerTrain) train;
                                            objs.add(pass);
                                        }
                                        break;
                                    }
                                }
                                route.setTrains(objs);
                            }

                            int index = 0;
                            for(Route element : resultLines){
                                if(element.getId() == route.getId()){
                                    resultLines.set(index, route);
                                    break;
                                }
                                index += 1;
                            }
                        }

                        line = extra.readLine();
                    }
                } catch (Throwable t){
                    try {
                        extra.close();
                    } catch (Throwable s) {
                        t.addSuppressed(s);
                    }
                    throw t;
                }
                result = resultLines;

            } catch (Throwable anything) {
                try {
                    bufferedReader.close();
                } catch (Throwable something) {
                    anything.addSuppressed(something);
                }
                throw anything;
            }

            //bufferedReader.close();
            initList(result);
            return result;
        } catch (FileNotFoundException e1) {
            System.out.println("File not found");
            initList(Collections.emptyList());
            return Collections.emptyList();
        } catch (IOException | ParseException e2) {
            System.out.println("Cannot read from file");
            initList(Collections.emptyList());
            return Collections.emptyList();
        }
    }

    @Override
    public void write(List<Route> objects){
        String fileName = this.getFileName();
        File file = new File(fileName);

        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
            try{
                String CSVline = "Id,Destination Station Id,Origin Station Id,Distance\n";
                bufferedWriter.write(CSVline);
            } catch (Throwable anything){
                throw anything;
            }
            if(objects != null){
                for(Route object : objects){
                    try{
                        String CSVline = this.convertObjectToString(object);
                        bufferedWriter.write(CSVline);
                    } catch (Throwable anything){
                        throw anything;
                    }
                }

            }
            bufferedWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        fileName = "src/main/java/com/company/resources/data - Route_Info.csv";
        file = new File(fileName);

        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
            try{
                String CSVline = "Id,Train Id\n";
                bufferedWriter.write(CSVline);
            } catch (Throwable anything){
                throw anything;
            }
            if(objects != null){
                for(Route object : objects){
                    List<Train> objs = object.getTrains();
                    int nr = objs.size();
                    while(nr > 0){
                        String CSVline = object.getId() + separator;
                        if(objs.size() > 0){
                            CSVline += objs.get(0).getId();
                            objs.remove(0);
                        } else {
                            CSVline += "null";
                        }

                        try{
                            CSVline +=  "\n";
                            bufferedWriter.write(CSVline);
                        } catch (Throwable anything){
                            throw anything;
                        }
                        nr -= 1;
                    }
                }

            }
            bufferedWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
