package com.company.services;



import com.company.entities.Route;
import com.company.entities.Station;
import com.company.entities.Ticket;
import com.company.entities.Train;
import com.company.entities.User;

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
                        int index = scanner.nextInt();
                        for(int i = 0; i < ticketService.getTickets().size(); ++i){
                            if(ticketService.getTickets().get(i).getId() == index){
                                System.out.println(ticketService.getTickets().get(i).toString());
                                break;
                            }
                        }
                    } else if(opt == 2){
                        Ticket ticket = ticketService.readTicket();
                        ticketService.addTicket(ticket);
                    } else if(opt == 3){
                        int index = scanner.nextInt();
                        for(int i = 0; i < ticketService.getTickets().size(); ++i){
                            if(ticketService.getTickets().get(i).getId() == index){
                                Ticket ticket = ticketService.readTicket();
                                ticketService.updateTicket(index, ticket);
                                break;
                            }
                        }
                    } else if(opt == 4){
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
                        for(int i = 0; i < trainService.getTrains().size(); ++i){
                            System.out.println(trainService.getTrains().get(i).toString());
                        }
                    } else if(opt == 1){
                        int index = scanner.nextInt();
                        for(int i = 0; i < trainService.getTrains().size(); ++i){
                            if(trainService.getTrains().get(i).getId() == index){
                                System.out.println(trainService.getTrains().get(i).toString());
                                break;
                            }
                        }
                    } else if(opt == 2){
                        Train train = trainService.readTrain();
                        trainService.addTrain(train);
                    } else if(opt == 3){
                        int index = scanner.nextInt();
                        for(int i = 0; i < trainService.getTrains().size(); ++i){
                            if(trainService.getTrains().get(i).getId() == index){
                                Train train = trainService.readTrain();
                                trainService.updateTrain(index, train);
                                break;
                            }
                        }
                    } else if(opt == 4){
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
                        for(int i = 0; i < routeService.getRoutes().size(); ++i){
                            System.out.println(routeService.getRoutes().get(i).toString());
                        }
                    } else if(opt == 1){
                        int index = scanner.nextInt();
                        for(int i = 0; i < routeService.getRoutes().size(); ++i){
                            if(routeService.getRoutes().get(i).getId() == index){
                                System.out.println(routeService.getRoutes().get(i).toString());
                                break;
                            }
                        }
                    } else if(opt == 2){
                        Route route = routeService.readRoute();
                        routeService.addRoute(route);
                    } else if(opt == 3){
                        int index = scanner.nextInt();
                        for(int i = 0; i < routeService.getRoutes().size(); ++i){
                            if(routeService.getRoutes().get(i).getId() == index){
                                Route route = routeService.readRoute();
                                routeService.updateRoute(index, route);
                                break;
                            }
                        }
                    } else if(opt == 4){
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
                        for(int i = 0; i < stationsService.getStations().size(); ++i){
                            System.out.println(stationsService.getStations().get(i).toString());
                        }
                    } else if(opt == 1){
                        int index = scanner.nextInt();
                        for(int i = 0; i < stationsService.getStations().size(); ++i){
                            if(stationsService.getStations().get(i).getId() == index){
                                System.out.println(stationsService.getStations().get(i).toString());
                                break;
                            }
                        }
                    } else if(opt == 2){
                        Station station = stationsService.readStation();
                        stationsService.addStation(station);
                    } else if(opt == 3){
                        int index = scanner.nextInt();
                        for(int i = 0; i < stationsService.getStations().size(); ++i){
                            if(stationsService.getStations().get(i).getId() == index){
                                Station station = stationsService.readStation();
                                stationsService.updateStation(index, station);
                                break;
                            }
                        }
                    } else if(opt == 4){
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
                        for(int i = 0; i < userService.getUsers().size(); ++i){
                            System.out.println(userService.getUsers().get(i).toString());
                        }
                    } else if(opt == 1){
                        int index = scanner.nextInt();
                        for(int i = 0; i < userService.getUsers().size(); ++i){
                            if(userService.getUsers().get(i).getId() == index){
                                System.out.println(userService.getUsers().get(i).toString());
                                break;
                            }
                        }
                    } else if(opt == 2){
                        User user = userService.readUser();
                        userService.addUser(user);
                    } else if(opt == 3){
                        int index = scanner.nextInt();
                        for(int i = 0; i < userService.getUsers().size(); ++i){
                            if(userService.getUsers().get(i).getId() == index){
                                User user = userService.readUser();
                                userService.updateUser(index, user);
                                break;
                            }
                        }
                    } else if(opt == 4){
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
