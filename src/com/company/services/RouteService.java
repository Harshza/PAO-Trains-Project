package com.company.services;

import com.company.entities.Route;
import com.company.entities.Train;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RouteService {
    private ArrayList<Route> routes = new ArrayList<>();

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

    Route readRoute() throws ParseException {
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
            trains.add(trainService.readTrain());
        }
        route.setTrains(trains);

        System.out.println("destination = ");
        route.setDestination(stationService.readStation());

        System.out.println("origin = ");
        route.setOrigin(stationService.readStation());

        System.out.println("distance = ");
        route.setDistance(scanner.nextDouble());

        return route;
    }

}
