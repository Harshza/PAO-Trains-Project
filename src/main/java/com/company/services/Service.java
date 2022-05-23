package com.company.services;



import com.company.config.DatabaseConfiguration;
import com.company.entities.*;
import com.company.repository.BoogieRepository;
import com.company.repository.PassengerTrainRepository;
import com.company.repository.StationRepository;
import com.company.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Service {
    private static Service instance;
    private static TicketService ticketService = TicketService.getInstance();
    private static TrainService trainService = TrainService.getInstance();
    private static UserService userService = UserService.getInstance();
    private static RouteService routeService = RouteService.getInstance();
    private static StationService stationsService = StationService.getInstance();

    private static AuditService auditService = AuditService.getInstance();

    private static UserRepository userRepository = UserRepository.getInstance();
    private static BoogieRepository boogieRepository = BoogieRepository.getInstance();
    private static PassengerTrainRepository passengerTrainRepository = PassengerTrainRepository.getInstance();
    private static StationRepository stationRepository = StationRepository.getInstance();
    private Scanner scanner = new Scanner(System.in);

    public static Service getInstance(){
        if(instance == null){
            instance = new Service();
        }
        return instance;
    }

    public void writeResults(){
        ticketService.write(ticketService.getTickets());

        trainService.write(trainService.getTrains());

        userService.write(userService.getUsers());

        routeService.write(routeService.getRoutes());

        stationsService.write(stationsService.getStations());
    }

    public void createTabels(){
        stationRepository.createTable();
        userRepository.createTable();
        passengerTrainRepository.createTable();
        boogieRepository.createTable();
    }

    public void databaseMenu() throws ParseException {
        createTabels();
        while(true) {
            System.out.println("0 - Station");
            System.out.println("1 - Boogie");
            System.out.println("2 - Passenger Train");
            System.out.println("3 - User");
            System.out.println("4 - Exit");
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 4) {
                        break;
                    } else {
                        System.out.println("Enter a number between 0 and 4");
                    }
                } catch (Exception e) {
                    System.out.println("Enter a number between 0 and 4");
                }
            }
            if(option== 0) {
                databaseStationMenu();
            } else if(option == 1) {
                databaseBoogieMenu();
            } else if(option == 2) {
                databasePassengerTrainMenu();
            } else if (option == 3) {
                databaseUserMenu();
            } else {
                break;
            }
        }
        DatabaseConfiguration.closeDatabaseConnection();
    }

    public void databaseStationMenu() throws ParseException {
        while(true) {
            System.out.println("0 - Insert Station into Database");
            System.out.println("1 - Get Station by Id from Database");
            System.out.println("2 - Get Station by Name from Database");
            System.out.println("3 - Update Station's Name in Database");
            System.out.println("4 - Delete Station from Database");
            System.out.println("5 - Display Stations from Database");
            System.out.println("6 - Exit");
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 6) {
                        break;
                    } else {
                        System.out.println("Enter a number between 0 and 6");
                    }
                } catch (Exception e) {
                    System.out.println("Enter a number between 0 and 6");
                }
            }
            if (option == 0) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Insert Station into Database", timeStamp);

                Station station = stationsService.readStation();
                stationRepository.insertStation(station);
            } else if (option == 1) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Get Station by Id from Database", timeStamp);

                System.out.println("id = ");
                int index;
                while (true) {
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter a number");
                    }
                }
                Station station = stationRepository.getStationById(index);
                if (station != null) {
                    System.out.println(station.toString());
                } else {
                    System.out.println("Station doesn't exist");
                }
            } else if(option == 2) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Get Station by Name from Database", timeStamp);

                System.out.println("name = ");
                String name = scanner.nextLine();
                Station station = stationRepository.getStationByName(name);
                if (station != null) {
                    System.out.println(station.toString());
                } else {
                    System.out.println("Station doesn't exist");
                }
            } else if(option == 3) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Update Station's Name in Database", timeStamp);

                System.out.println("name = ");
                String name = scanner.nextLine();

                System.out.println("id = ");
                int index;
                while (true) {
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter a number");
                    }
                }
                stationRepository.updateStationName(name, index);
                Station station = stationRepository.getStationById(index);
                if(station != null){
                    System.out.println("Done");
                } else {
                    System.out.println("Station doesn't exist");
                }
            } else if(option == 4) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Delete Station from Database", timeStamp);

                System.out.println("id = ");
                int index;
                while (true) {
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter a number");
                    }
                }
                Station station = stationRepository.getStationById(index);
                stationRepository.deleteStation(index);

                if(station != null){
                    System.out.println("Done");
                } else {
                    System.out.println("Station doesn't exist");
                }
            } else if(option == 5) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Display Stations from Database", timeStamp);

                stationRepository.displayStations();
            } else {
                break;
            }
        }
    }

    public void databaseUserMenu() throws ParseException {
        while(true) {
            System.out.println("0 - Insert User into Database");
            System.out.println("1 - Get User by Id from Database");
            System.out.println("2 - Get User by Name from Database");
            System.out.println("3 - Update User's Name in Database");
            System.out.println("4 - Delete User from Database");
            System.out.println("5 - Display Users from Database");
            System.out.println("6 - Exit");
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 6) {
                        break;
                    } else {
                        System.out.println("Enter a number between 0 and 6");
                    }
                } catch (Exception e) {
                    System.out.println("Enter a number between 0 and 6");
                }
            }
            if (option == 0) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Insert User into Database", timeStamp);

                User user = userService.readUser();
                userRepository.insertUser(user);
            } else if (option == 1) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Get User by Id from Database", timeStamp);

                System.out.println("id = ");
                int index;
                while (true) {
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter a number");
                    }
                }
                User user = userRepository.getUserById(index);
                if (user != null) {
                    System.out.println(user.toString());
                } else {
                    System.out.println("User doesn't exist");
                }
            } else if(option == 2) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Get User by Name from Database", timeStamp);

                System.out.println("name = ");
                String name = scanner.nextLine();
                User user = userRepository.getUserByName(name);
                if (user != null) {
                    System.out.println(user.toString());
                } else {
                    System.out.println("User doesn't exist");
                }
            } else if(option == 3) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Update User's Name in Database", timeStamp);

                System.out.println("name = ");
                String name = scanner.nextLine();

                System.out.println("id = ");
                int index;
                while (true) {
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter a number");
                    }
                }
                userRepository.updateUserName(name, index);
                User user = userRepository.getUserById(index);
                if(user != null){
                    System.out.println("Done");
                } else {
                    System.out.println("User doesn't exist");
                }
            } else if(option == 4) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Delete User from Database", timeStamp);

                System.out.println("id = ");
                int index;
                while (true) {
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter a number");
                    }
                }
                User user = userRepository.getUserById(index);
                userRepository.deleteUser(index);

                if(user != null){
                    System.out.println("Done");
                } else {
                    System.out.println("User doesn't exist");
                }
            } else if(option == 5) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Display Users from Database", timeStamp);

                userRepository.displayUsers();
            } else {
                break;
            }
        }
    }

    public void databaseBoogieMenu(){
        while(true) {
            System.out.println("0 - Insert Boogie into Database");
            System.out.println("1 - Get Boogie by Id from Database");
            System.out.println("2 - Get Boogie by Name from Database");
            System.out.println("3 - Update Boogie's Name in Database");
            System.out.println("4 - Delete Boogie from Database");
            System.out.println("5 - Display Boogies from Database");
            System.out.println("6 - Exit");
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 6) {
                        break;
                    } else {
                        System.out.println("Enter a number between 0 and 6");
                    }
                } catch (Exception e) {
                    System.out.println("Enter a number between 0 and 6");
                }
            }
            if (option == 0) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Insert Boogie into Database", timeStamp);

                Boogie boogie = (Boogie) trainService.readTrain();
                boogieRepository.insertBoogie(boogie);
            } else if (option == 1) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Get Boogie by Id from Database", timeStamp);

                System.out.println("id = ");
                int index;
                while (true) {
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter a number");
                    }
                }
                Boogie boogie = boogieRepository.getBoogieById(index);
                if (boogie != null) {
                    System.out.println(boogie.toString());
                } else {
                    System.out.println("Boogie doesn't exist");
                }
            } else if(option == 2) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Get Boogie by Name from Database", timeStamp);

                System.out.println("name = ");
                String name = scanner.nextLine();
                Boogie boogie = boogieRepository.getBoogieByName(name);
                if (boogie != null) {
                    System.out.println(boogie.toString());
                } else {
                    System.out.println("Boogie doesn't exist");
                }
            } else if(option == 3) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Update Boogie's Name in Database", timeStamp);

                System.out.println("name = ");
                String name = scanner.nextLine();

                System.out.println("id = ");
                int index;
                while (true) {
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter a number");
                    }
                }
                boogieRepository.updateBoogieName(name, index);
                Boogie boogie = boogieRepository.getBoogieById(index);
                if(boogie != null){
                    System.out.println("Done");
                } else {
                    System.out.println("Boogie doesn't exist");
                }
            } else if(option == 4) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Delete Boogie from Database", timeStamp);

                System.out.println("id = ");
                int index;
                while (true) {
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter a number");
                    }
                }
                Boogie boogie = boogieRepository.getBoogieById(index);
                boogieRepository.deleteBoogie(index);

                if(boogie != null){
                    System.out.println("Done");
                } else {
                    System.out.println("Boogie doesn't exist");
                }
            } else if(option == 5) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Display Boogies from Database", timeStamp);

                boogieRepository.displayBoogies();
            } else {
                break;
            }
        }
    }

    public void databasePassengerTrainMenu(){
        while(true) {
            System.out.println("0 - Insert Passenger Train into Database");
            System.out.println("1 - Get Passenger Train by Id from Database");
            System.out.println("2 - Get Passenger Train by Name from Database");
            System.out.println("3 - Update Passenger Train's Name in Database");
            System.out.println("4 - Delete Passenger Train from Database");
            System.out.println("5 - Display Passenger Trains from Database");
            System.out.println("6 - Exit");
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 6) {
                        break;
                    } else {
                        System.out.println("Enter a number between 0 and 6");
                    }
                } catch (Exception e) {
                    System.out.println("Enter a number between 0 and 6");
                }
            }
            if (option == 0) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Insert Passenger Train into Database", timeStamp);

                PassengerTrain passengerTrain = (PassengerTrain) trainService.readTrain();
                passengerTrainRepository.insertPassengerTrain(passengerTrain);
            } else if (option == 1) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Get Passenger Train by Id from Database", timeStamp);

                System.out.println("id = ");
                int index;
                while (true) {
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter a number");
                    }
                }
                PassengerTrain passengerTrain = passengerTrainRepository.getPassengerTrainById(index);
                if (passengerTrain != null) {
                    System.out.println(passengerTrain.toString());
                } else {
                    System.out.println("Passenger Train doesn't exist");
                }
            } else if(option == 2) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Get Passenger Train by Name from Database", timeStamp);

                System.out.println("name = ");
                String name = scanner.nextLine();
                PassengerTrain passengerTrain = passengerTrainRepository.getPassengerTrainByName(name);
                if (passengerTrain != null) {
                    System.out.println(passengerTrain.toString());
                } else {
                    System.out.println("Passenger Train doesn't exist");
                }
            } else if(option == 3) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Update Passenger Train's Name in Database", timeStamp);

                System.out.println("name = ");
                String name = scanner.nextLine();

                System.out.println("id = ");
                int index;
                while (true) {
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter a number");
                    }
                }
                passengerTrainRepository.updatePassengerTrainName(name, index);
                PassengerTrain passengerTrain = passengerTrainRepository.getPassengerTrainById(index);
                if(passengerTrain != null){
                    System.out.println("Done");
                } else {
                    System.out.println("Passenger Train doesn't exist");
                }
            } else if(option == 4) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Delete Passenger Train from Database", timeStamp);

                System.out.println("id = ");
                int index;
                while (true) {
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter a number");
                    }
                }
                PassengerTrain passengerTrain = passengerTrainRepository.getPassengerTrainById(index);
                passengerTrainRepository.deletePassengerTrain(index);

                if(passengerTrain != null){
                    System.out.println("Done");
                } else {
                    System.out.println("Passenger Train doesn't exist");
                }
            } else if(option == 5) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                auditService.audit("Display Passenger Trains from Database", timeStamp);

                passengerTrainRepository.displayPassengerTrains();
            } else {
                break;
            }
        }
    }

    public void printOptions(){
        System.out.println("0 - Tickets");
        System.out.println("1 - Trains");
        System.out.println("2 - Routes");
        System.out.println("3 - Stations");
        System.out.println("4 - Users");
        System.out.println("5 - Exit");
    }

    public void menu() throws ParseException {
        while(true){
            printOptions();
            int option = scanner.nextInt();
            if(option == 0){
                while(true){
                    System.out.println(" 0 - Get All");
                    System.out.println(" 1 - Get By Id");
                    System.out.println(" 2 - Add");
                    System.out.println(" 3 - Update");
                    System.out.println(" 4 - Delete");
                    System.out.println(" 5 - Exit");
                    int opt = scanner.nextInt();
                    if(opt == 0){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Print all Tickets" , timeStamp);
                        for(int i = 0; i < ticketService.getTickets().size(); ++i){
                            System.out.println(ticketService.getTickets().get(i).toString());
                        }
                    } else if(opt == 1){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Print Ticket" , timeStamp);
                        int index = scanner.nextInt();
                        for(int i = 0; i < ticketService.getTickets().size(); ++i){
                            if(ticketService.getTickets().get(i).getId() == index){
                                System.out.println(ticketService.getTickets().get(i).toString());
                                break;
                            }
                        }
                    } else if(opt == 2){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Add Ticket" , timeStamp);
                        Ticket ticket = ticketService.readTicket();
                        ticketService.addTicket(ticket);
                    } else if(opt == 3){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Update Ticket" , timeStamp);
                        int index = scanner.nextInt();
                        for(int i = 0; i < ticketService.getTickets().size(); ++i){
                            if(ticketService.getTickets().get(i).getId() == index){
                                Ticket ticket = ticketService.readTicket();
                                ticketService.updateTicket(index, ticket);
                                break;
                            }
                        }
                    } else if(opt == 4){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Delete Ticket" , timeStamp);
                        int index = scanner.nextInt();
                        ticketService.deteleTicket(index);
                    } else {
                        break;
                    }
                }
            } else if(option == 1){
                while(true){
                    System.out.println(" 0 - Get All");
                    System.out.println(" 1 - Get By Id");
                    System.out.println(" 2 - Add");
                    System.out.println(" 3 - Update");
                    System.out.println(" 4 - Delete");
                    System.out.println(" 5 - Exit");
                    int opt = scanner.nextInt();
                    if(opt == 0){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Print all Trains" , timeStamp);
                        for(int i = 0; i < trainService.getTrains().size(); ++i){
                            System.out.println(trainService.getTrains().get(i).toString());
                        }
                    } else if(opt == 1){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Print Train" , timeStamp);
                        int index = scanner.nextInt();
                        for(int i = 0; i < trainService.getTrains().size(); ++i){
                            if(trainService.getTrains().get(i).getId() == index){
                                System.out.println(trainService.getTrains().get(i).toString());
                                break;
                            }
                        }
                    } else if(opt == 2){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Add Train" , timeStamp);
                        Train train = trainService.readTrain();
                        trainService.addTrain(train);
                    } else if(opt == 3){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Update Train" , timeStamp);
                        int index = scanner.nextInt();
                        for(int i = 0; i < trainService.getTrains().size(); ++i){
                            if(trainService.getTrains().get(i).getId() == index){
                                Train train = trainService.readTrain();
                                trainService.updateTrain(index, train);
                                break;
                            }
                        }
                    } else if(opt == 4){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Delete Train" , timeStamp);
                        int index = scanner.nextInt();
                        trainService.deteleTrain(index);
                    } else {
                        break;
                    }
                }

            } else if(option == 2){
                while(true){
                    System.out.println(" 0 - Get All");
                    System.out.println(" 1 - Get By Id");
                    System.out.println(" 2 - Add");
                    System.out.println(" 3 - Update");
                    System.out.println(" 4 - Delete");
                    System.out.println(" 5 - Exit");
                    int opt = scanner.nextInt();
                    if(opt == 0){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Print all Routes" , timeStamp);
                        for(int i = 0; i < routeService.getRoutes().size(); ++i){
                            System.out.println(routeService.getRoutes().get(i).toString());
                        }
                    } else if(opt == 1){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Print Route" , timeStamp);
                        int index = scanner.nextInt();
                        for(int i = 0; i < routeService.getRoutes().size(); ++i){
                            if(routeService.getRoutes().get(i).getId() == index){
                                System.out.println(routeService.getRoutes().get(i).toString());
                                break;
                            }
                        }
                    } else if(opt == 2){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Add Route" , timeStamp);
                        Route route = routeService.readRoute();
                        routeService.addRoute(route);
                    } else if(opt == 3){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Update Route" , timeStamp);
                        int index = scanner.nextInt();
                        for(int i = 0; i < routeService.getRoutes().size(); ++i){
                            if(routeService.getRoutes().get(i).getId() == index){
                                Route route = routeService.readRoute();
                                routeService.updateRoute(index, route);
                                break;
                            }
                        }
                    } else if(opt == 4){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Delete Route" , timeStamp);
                        int index = scanner.nextInt();
                        routeService.deteleRoute(index);
                    } else {
                        break;
                    }
                }
            } else if(option == 3){
                while(true){
                    System.out.println(" 0 - Get All");
                    System.out.println(" 1 - Get By Id");
                    System.out.println(" 2 - Add");
                    System.out.println(" 3 - Update");
                    System.out.println(" 4 - Delete");
                    System.out.println(" 5 - Exit");
                    int opt = scanner.nextInt();
                    if(opt == 0){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Print all Stations" , timeStamp);
                        for(int i = 0; i < stationsService.getStations().size(); ++i){
                            System.out.println(stationsService.getStations().get(i).toString());
                        }
                    } else if(opt == 1){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Print Station" , timeStamp);
                        int index = scanner.nextInt();
                        for(int i = 0; i < stationsService.getStations().size(); ++i){
                            if(stationsService.getStations().get(i).getId() == index){
                                System.out.println(stationsService.getStations().get(i).toString());
                                break;
                            }
                        }
                    } else if(opt == 2){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Add Station" , timeStamp);
                        Station station = stationsService.readStation();
                        stationsService.addStation(station);
                    } else if(opt == 3){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Update Station" , timeStamp);
                        int index = scanner.nextInt();
                        for(int i = 0; i < stationsService.getStations().size(); ++i){
                            if(stationsService.getStations().get(i).getId() == index){
                                Station station = stationsService.readStation();
                                stationsService.updateStation(index, station);
                                break;
                            }
                        }
                    } else if(opt == 4){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Delete Station" , timeStamp);
                        int index = scanner.nextInt();
                        stationsService.deteleStation(index);
                    } else {
                        break;
                    }
                }
            } else if(option == 4){
                while(true){
                    System.out.println(" 0 - Get All");
                    System.out.println(" 1 - Get By Id");
                    System.out.println(" 2 - Add");
                    System.out.println(" 3 - Update");
                    System.out.println(" 4 - Delete");
                    System.out.println(" 5 - Exit");
                    int opt = scanner.nextInt();
                    if(opt == 0){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Print all Users" , timeStamp);
                        for(int i = 0; i < userService.getUsers().size(); ++i){
                            System.out.println(userService.getUsers().get(i).toString());
                        }
                    } else if(opt == 1){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Print User" , timeStamp);
                        int index = scanner.nextInt();
                        for(int i = 0; i < userService.getUsers().size(); ++i){
                            if(userService.getUsers().get(i).getId() == index){
                                System.out.println(userService.getUsers().get(i).toString());
                                break;
                            }
                        }
                    } else if(opt == 2){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Add User" , timeStamp);
                        User user = userService.readUser();
                        userService.addUser(user);
                    } else if(opt == 3){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Update User" , timeStamp);
                        int index = scanner.nextInt();
                        for(int i = 0; i < userService.getUsers().size(); ++i){
                            if(userService.getUsers().get(i).getId() == index){
                                User user = userService.readUser();
                                userService.updateUser(index, user);
                                break;
                            }
                        }
                    } else if(opt == 4){
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String timeStamp = date.format(new Date());
                        auditService.audit("Delete User" , timeStamp);
                        int index = scanner.nextInt();
                        userService.deteleUser(index);
                    } else {
                        break;
                    }
                }
            } else if(option == 5){
                break;
            }
        }
        writeResults();
    }

}
