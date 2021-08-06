package erezervareteatru;

import java.util.ArrayList;
import java.util.List;


public class Client {
    String name;
    String phoneNo;
    List<Ticket> tickets;

    //constructor
    public Client(String name, String phoneNo, Ticket ticket) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.tickets = new ArrayList<>();
        this.tickets.add(ticket);
    }

    public void attachNewTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
