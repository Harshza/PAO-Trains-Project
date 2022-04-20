package com.company.services;



import com.company.entities.Route;
import com.company.entities.Station;
import com.company.entities.Ticket;
import com.company.entities.Train;
import com.company.entities.User;

import java.text.ParseException;
import java.util.*;

public class Service {
    private static Service instance;
    private static TicketService ticketService = TicketService.getInstance();
    private static TrainService trainService = TrainService.getInstance();
    private static UserService userService = UserService.getInstance();
    private static RouteService routeService = RouteService.getInstance();
    private static StationService stationsService = StationService.getInstance();

    private Scanner scanner = new Scanner(System.in);

    public static Service getInstance(){
        if(instance == null){
            instance = new Service();
        }
        return instance;
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
            int option;
            try {
                option = scanner.nextInt();
            }
            catch(Exception e){
                System.out.println("Provide an int between 0 and 5");
                option = scanner.nextInt();
            }
            if(option == 0){
                while(true){
                    System.out.println(" 0 - Get All");
                    System.out.println(" 1 - Get By Id");
                    System.out.println(" 2 - Add");
                    System.out.println(" 3 - Update");
                    System.out.println(" 4 - Delete");
                    System.out.println(" 5 - Exit");
                    int opt;
                    try {
                        opt = scanner.nextInt();
                    }
                    catch(Exception e){
                        System.out.println("Provide an int between 0 and 5");
                        opt = scanner.nextInt();
                    }
                    if(opt == 0){
                        if(ticketService.getTickets().size() == 0){
                            System.out.println("No tickets");
                        }
                        for(int i = 0; i < ticketService.getTickets().size(); ++i){
                            ticketService.getTickets().get(i).ticketInfo();
                        }
                    } else if(opt == 1){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(ticketService.getTickets().size() == 0){
                            System.out.println("No tickets");
                        }
                        boolean ok = false;
                        for(int i = 0; i < ticketService.getTickets().size(); ++i){
                            if(ticketService.getTickets().get(i).getId() == index){
                                ok = true;
                                ticketService.getTickets().get(i).ticketInfo();
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This ticket doesnt exist");
                        }
                    } else if(opt == 2){
                        Ticket ticket = ticketService.readTicket();
                        ticketService.addTicket(ticket);
                    } else if(opt == 3){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(ticketService.getTickets().size() == 0){
                            System.out.println("No tickets");
                        }
                        boolean ok = false;
                        for(int i = 0; i < ticketService.getTickets().size(); ++i){
                            if(ticketService.getTickets().get(i).getId() == index){
                                ok = true;
                                Ticket ticket = ticketService.readTicket();
                                ticketService.updateTicket(index, ticket);
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This ticket doesnt exist");
                        }
                    } else if(opt == 4){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(ticketService.getTickets().size() == 0){
                            System.out.println("No tickets");
                        }
                        boolean ok = false;
                        for(int i = 0; i < ticketService.getTickets().size(); ++i){
                            if(ticketService.getTickets().get(i).getId() == index){
                                ok = true;
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This ticket doesnt exist");
                        }
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
                    int opt;
                    try {
                        opt = scanner.nextInt();
                    }
                    catch(Exception e){
                        System.out.println("Provide an int between 0 and 5");
                        opt = scanner.nextInt();
                    }
                    if(opt == 0){
                        if(trainService.getTrains().size() == 0){
                            System.out.println("No trains");
                        }
                        for(int i = 0; i < trainService.getTrains().size(); ++i){
                            trainService.getTrains().get(i).trainInfo();
                        }
                    } else if(opt == 1){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(trainService.getTrains().size() == 0){
                            System.out.println("No trains");
                        }
                        boolean ok = false;
                        for(int i = 0; i < trainService.getTrains().size(); ++i){
                            if(trainService.getTrains().get(i).getId() == index){
                                trainService.getTrains().get(i).trainInfo();
                                ok = true;
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This train doesnt exist");
                        }
                    } else if(opt == 2){
                        Train train = trainService.readTrain();
                        trainService.addTrain(train);
                    } else if(opt == 3){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(trainService.getTrains().size() == 0){
                            System.out.println("No trains");
                        }
                        boolean ok = false;
                        for(int i = 0; i < trainService.getTrains().size(); ++i){
                            if(trainService.getTrains().get(i).getId() == index){
                                Train train = trainService.readTrain();
                                trainService.updateTrain(index, train);
                                ok = true;
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This train doesnt exist");
                        }
                    } else if(opt == 4){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(trainService.getTrains().size() == 0){
                            System.out.println("No trains");
                        }
                        boolean ok = false;
                        for(int i = 0; i < trainService.getTrains().size(); ++i){
                            if(trainService.getTrains().get(i).getId() == index){
                                ok = true;
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This train doesnt exist");
                        }
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
                    int opt;
                    try {
                        opt = scanner.nextInt();
                    }
                    catch(Exception e){
                        System.out.println("Provide an int between 0 and 5");
                        opt = scanner.nextInt();
                    }
                    if(opt == 0){
                        if(routeService.getRoutes().size() == 0){
                            System.out.println("No routes");
                        }
                        for(int i = 0; i < routeService.getRoutes().size(); ++i){
                            routeService.getRoutes().get(i).routeInfo();
                        }
                    } else if(opt == 1){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(routeService.getRoutes().size() == 0){
                            System.out.println("No routes");
                        }
                        boolean ok = false;
                        for(int i = 0; i < routeService.getRoutes().size(); ++i){
                            if(routeService.getRoutes().get(i).getId() == index){
                                routeService.getRoutes().get(i).routeInfo();
                                ok = true;
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This route doesnt exist");
                        }
                    } else if(opt == 2){
                        Route route = routeService.readRoute();
                        routeService.addRoute(route);
                    } else if(opt == 3){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(routeService.getRoutes().size() == 0){
                            System.out.println("No routes");
                        }
                        boolean ok = false;
                        for(int i = 0; i < routeService.getRoutes().size(); ++i){
                            if(routeService.getRoutes().get(i).getId() == index){
                                Route route = routeService.readRoute();
                                ok = true;
                                routeService.updateRoute(index, route);
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This route doesnt exist");
                        }
                    } else if(opt == 4){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(routeService.getRoutes().size() == 0){
                            System.out.println("No routes");
                        }
                        boolean ok = false;
                        for(int i = 0; i < routeService.getRoutes().size(); ++i){
                            if(routeService.getRoutes().get(i).getId() == index){
                                ok = true;
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This route doesnt exist");
                        }
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
                    int opt;
                    try {
                        opt = scanner.nextInt();
                    }
                    catch(Exception e){
                        System.out.println("Provide an int between 0 and 5");
                        opt = scanner.nextInt();
                    }
                    if(opt == 0){
                        if(stationsService.getStations().size() == 0){
                            System.out.println("No stations");
                        }
                        for(int i = 0; i < stationsService.getStations().size(); ++i){
                            stationsService.getStations().get(i).stationInfo();
                        }
                    } else if(opt == 1){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(stationsService.getStations().size() == 0){
                            System.out.println("No stations");
                        }
                        boolean ok = false;
                        for(int i = 0; i < stationsService.getStations().size(); ++i){
                            if(stationsService.getStations().get(i).getId() == index){
                                ok = true;
                                stationsService.getStations().get(i).stationInfo();
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This station doesnt exist");
                        }
                    } else if(opt == 2){
                        Station station = stationsService.readStation();
                        stationsService.addStation(station);
                    } else if(opt == 3){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(stationsService.getStations().size() == 0){
                            System.out.println("No stations");
                        }
                        boolean ok = false;
                        for(int i = 0; i < stationsService.getStations().size(); ++i){
                            if(stationsService.getStations().get(i).getId() == index){
                                Station station = stationsService.readStation();
                                ok = true;
                                stationsService.updateStation(index, station);
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This station doesnt exist");
                        }
                    } else if(opt == 4){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(stationsService.getStations().size() == 0){
                            System.out.println("No stations");
                        }
                        boolean ok = false;
                        for(int i = 0; i < stationsService.getStations().size(); ++i){
                            if(stationsService.getStations().get(i).getId() == index){
                                ok = true;
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This station doesnt exist");
                        }
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
                    int opt;
                    try {
                        opt = scanner.nextInt();
                    }
                    catch(Exception e){
                        System.out.println("Provide an int between 0 and 5");
                        opt = scanner.nextInt();
                    }
                    if(opt == 0){
                        if(userService.getUsers().size() == 0){
                            System.out.println("No stations");
                        }
                        for(int i = 0; i < userService.getUsers().size(); ++i){
                            userService.getUsers().get(i).userInfo();
                        }
                    } else if(opt == 1){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(userService.getUsers().size() == 0){
                            System.out.println("No stations");
                        }
                        boolean ok = false;
                        for(int i = 0; i < userService.getUsers().size(); ++i){
                            if(userService.getUsers().get(i).getId() == index){
                                userService.getUsers().get(i).userInfo();
                                ok = true;
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This user doesnt exist");
                        }
                    } else if(opt == 2){
                        User user = userService.readUser();
                        userService.addUser(user);
                    } else if(opt == 3){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(userService.getUsers().size() == 0){
                            System.out.println("No stations");
                        }
                        boolean ok = false;
                        for(int i = 0; i < userService.getUsers().size(); ++i){
                            if(userService.getUsers().get(i).getId() == index){
                                User user = userService.readUser();
                                ok = true;
                                userService.updateUser(index, user);
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This user doesnt exist");
                        }
                    } else if(opt == 4){
                        int index;
                        try {
                            index = scanner.nextInt();
                        } catch (Exception e){
                            System.out.println("Provide valid id");
                            index = scanner.nextInt();
                        }
                        if(userService.getUsers().size() == 0){
                            System.out.println("No stations");
                        }
                        boolean ok = false;
                        for(int i = 0; i < userService.getUsers().size(); ++i){
                            if(userService.getUsers().get(i).getId() == index){
                                ok = true;
                                break;
                            }
                        }
                        if(!ok){
                            System.out.println("This user doesnt exist");
                        }
                        userService.deteleUser(index);
                    } else {
                        break;
                    }
                }
            } else if(option == 5){
                break;
            }
        }
    }

}
