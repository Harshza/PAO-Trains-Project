package com.company.services;

import com.company.entities.Ticket;
import com.company.entities.Train;
import com.company.entities.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService implements UserInterface{
    private ArrayList<User> users = new ArrayList<>();

    private UserService(){};
    private static UserService instance;

    public static UserService getInstance(){
        if(instance == null){
            instance = new UserService();
        }
        return instance;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> usersCopy = new ArrayList<>();
        usersCopy.addAll(this.users);
        return usersCopy;
    }

    public User getUserById(int index){
        User user = new User();
        for(int i = 0; i < this.users.size(); ++i){
            if(this.users.get(i).getId() == index){
                user = this.users.get(i);
            }
        }
        return user;
    }

    public void updateUser(int index, User user){
        for(int i = 0; i < this.users.size(); ++i){
            if(this.users.get(i).getId() == index){
                this.users.remove(i);
                this.users.add(index, user);
                break;
            }
        }
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public void deteleUser(int index){
        for(int i = 0; i < this.users.size(); ++i){
            if(this.users.get(i).getId() == index) {
                this.users.remove(i);
                break;
            }
        }
    }

    public User readUser() throws ParseException {
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.println("id = ");
        user.setId(scanner.nextInt());

        System.out.println("name = ");
        user.setName(scanner.next());

        System.out.println("phone number = ");
        user.setPhoneNumber(scanner.next());

        System.out.println("email = ");
        user.setEmail(scanner.next());

        System.out.println("address = ");
        user.setAddress(scanner.next());

        List<Ticket> tickets = new ArrayList<>();
        TicketService ticketService = TicketService.getInstance();
        System.out.println("number of tickets = ");
        int nr = scanner.nextInt();
        System.out.println("tickets = ");
        for(int i = 0; i < nr; ++i){
            tickets.add(ticketService.readTicket());
        }
        user.setTickets(tickets);
        return user;
    }
}
