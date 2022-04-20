package com.company.services;

import com.company.entities.*;

import java.text.ParseException;
import java.util.*;

public class TicketService implements TicketInterface{
    private ArrayList<Ticket> tickets = new ArrayList<>();

    private static TicketService instance;

    public static TicketService getInstance(){
        if(instance == null){
            instance = new TicketService();
        }
        return instance;
    }

    public ArrayList<Ticket> getTickets() {
        ArrayList<Ticket> ticketCopy = new ArrayList<>();
        ticketCopy.addAll(this.tickets);
        Collections.sort(ticketCopy, new TicketPriceComparator());
        return ticketCopy;
    }

    public Ticket getTicketById(int index){
        Ticket ticket = null;
        for(int i = 0; i < this.tickets.size(); ++i){
            if(this.tickets.get(i).getId() == index){
                if(this.tickets.get(i) instanceof FirstClass){
                    if(this.tickets.get(i) instanceof BunkBed)
                        ticket =  (BunkBed) this.tickets.get(i);
                    ticket = (FirstClass) this.tickets.get(i);
                }
                else if (this.tickets.get(i) instanceof SecondClass){
                    ticket = (SecondClass) this.tickets.get(i);
                }
            }
        }
        return ticket;
    }

    public void updateTicket(int index, Ticket ticket){
        for(int i = 0; i < this.tickets.size(); ++i){
            if(this.tickets.get(i).getId() == index){
                this.tickets.remove(i);
                this.tickets.add(index, ticket);
                break;
            }
        }
    }

    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
    }

    public void deteleTicket(int index){
        for(int i = 0; i < this.tickets.size(); ++i){
            if(this.tickets.get(i).getId() == index) {
                this.tickets.remove(i);
                break;
            }
        }
    }

    public Ticket readTicket() throws ParseException { // 0 = FirstClass; 1 = SecondClass; 2 = BunkBed
        Scanner scanner = new Scanner(System.in);
        RouteService routeService = RouteService.getInstance();
        System.out.println("0 - First Class");
        System.out.println("1 - Second Class");
        System.out.println("2 - Bunk Bed");
        int option;
        try {
            option = scanner.nextInt();
        } catch (Exception e){
            System.out.println("Provide int between 0 and 2");
            option = scanner.nextInt();
        }
        if(option == 0){
            FirstClass firstClass = new FirstClass();
            System.out.println("id = ");
            int id;
            try {
                id = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Provide int");
                id = scanner.nextInt();
            }
            firstClass.setId(id);

            System.out.println("price = ");
            double price;
            try {
                price = scanner.nextDouble();
            } catch (Exception e){
                System.out.println("Provide double");
                price = scanner.nextDouble();
            }
            firstClass.setPrice(price);

            System.out.println("seat = ");
            int seat;
            try {
                seat = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Provide int");
                seat = scanner.nextInt();
            }
            firstClass.setSeat(seat);

            System.out.println("Route = ");
            firstClass.setRoute(routeService.readRoute());

            System.out.println("number of meals included = ");
            int nr;
            try {
                nr = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Provide int");
                nr = scanner.nextInt();
            }

            System.out.println("meals included = ");
            ArrayList arr = new ArrayList<>();
            for(int i = 0; i < nr; ++i){
                arr.add(scanner.next());
            }
            TreeSet t = new TreeSet(arr);
            firstClass.setMealsIncluded(t);

            return firstClass;

        } else if (option == 1){
            SecondClass secondClass = new SecondClass();

            System.out.println("id = ");
            int id;
            try {
                id = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Provide int");
                id = scanner.nextInt();
            }
            secondClass.setId(id);

            System.out.println("price = ");
            double price;
            try {
                price = scanner.nextDouble();
            } catch (Exception e){
                System.out.println("Provide double");
                price = scanner.nextDouble();
            }
            secondClass.setPrice(price);

            System.out.println("seat = ");
            int seat;
            try {
                seat = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Provide int");
                seat = scanner.nextInt();
            }
            secondClass.setSeat(seat);

            System.out.println("route = ");
            secondClass.setRoute(routeService.readRoute());

            System.out.println("discount = ");
            double discount;
            try {
                discount = scanner.nextDouble();
            } catch (Exception e){
                System.out.println("Provide double");
                discount = scanner.nextDouble();
            }
            secondClass.setDiscount(discount);

            return secondClass;
        } else {
            BunkBed bunkBed = new BunkBed();

            System.out.println("id = ");
            int id;
            try {
                id = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Provide int");
                id = scanner.nextInt();
            }
            bunkBed.setId(id);

            System.out.println("price = ");
            double price;
            try {
                price = scanner.nextDouble();
            } catch (Exception e){
                System.out.println("Provide double");
                price = scanner.nextDouble();
            }
            bunkBed.setPrice(price);

            System.out.println("seat = ");
            System.out.println("seat = ");
            int seat;
            try {
                seat = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Provide int");
                seat = scanner.nextInt();
            }
            bunkBed.setSeat(seat);

            System.out.println("route = ");
            bunkBed.setRoute(routeService.readRoute());

            System.out.println("top bed = true & bottom bed = false ");
            System.out.println("seat = ");
            boolean bed;
            try {
                bed = scanner.nextBoolean();
            } catch (Exception e){
                System.out.println("Provide int");
                bed = scanner.nextBoolean();
            }
            bunkBed.setBedPosition(bed);

            return bunkBed;
        }
    }
}
