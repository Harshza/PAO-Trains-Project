package com.company.services;

import com.company.entities.BunkBed;
import com.company.entities.FirstClass;
import com.company.entities.SecondClass;
import com.company.entities.Ticket;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

public interface TicketInterface {
    public ArrayList<Ticket> getTickets();

    public Ticket getTicketById(int index);

    public void updateTicket(int index, Ticket ticket);

    public void addTicket(Ticket ticket);

    public void deteleTicket(int index);

    public Ticket readTicket() throws ParseException;
}
