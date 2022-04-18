package com.company.services;

import com.company.entities.Ticket;

import java.util.Comparator;

public class TicketPriceComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2) {
        if (t1.getPrice() - t2.getPrice() > 0){
            return 1;
        }
        return 0;
    }
}
