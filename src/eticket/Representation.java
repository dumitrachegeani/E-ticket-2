package eticket;

import java.util.ArrayList;
import java.util.List;

public class Representation {
    private String dateAndTime;
    private int tickets;
    private List<Integer> availableChairs;
    private double price;
    List<Client> clients;

    public Representation(int year, int month, int day, int hour, int minutes, int tickets, int price) {
        this.dateAndTime = "Data " + day + "/" + month + "/" + year + "    ora " + hour + ":" + minutes;
        this.tickets = tickets;
        this.price = price;
        this.clients = new ArrayList<>();
        this.availableChairs = new ArrayList<>();
        for (int i = 1; i < tickets + 1; i++)
            availableChairs.add(i);
    }

    public void addClient(String name, String phoneNo, Ticket ticket) {
        //daca exitsa doar ii adauga un bilet nou
        for (Client client : clients) {
            if (client.name.equals(name)) {
                client.attachNewTicket(ticket);
                return;
            }
        }
        //altfel adauga clientul cu tot cu bilet
        clients.add(new Client(name, phoneNo, ticket));
    }

    public double getPrice() {
        return price;
    }

    public List<Integer> getAvailableChairs() {
        return availableChairs;
    }

    @Override
    public String toString() {
        return dateAndTime;
    }
}
