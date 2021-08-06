package erezervareteatru;

import java.util.ArrayList;
import java.util.List;

public class Show {
    private String name;
    private List<Representation> representations;


    public Show(String name, Representation representation) {
        this.name = name;
        this.representations = new ArrayList<>();
        this.representations.add(representation);
    }

    //Single Responsability Principle
    public static void addFromFile(List<Show> shows, String showName, Representation representation) {
        for (Show s : shows){
            //daca avem deja showl in lista ii adaugam doar o noua reprezentatie
            if (s.name.equals(showName)) {
                s.addRepresentaion(representation);
                return;
            }
        }
        //altfel adaugam showl cu tot cu reprezentatie
        shows.add(new Show(showName, representation));
    }

    public void addRepresentaion(Representation dispobibilitate) {
        this.representations.add(dispobibilitate);
    }

    public String getName() {
        return name;
    }

    public List<Representation> getRepresentations() {
        return representations;
    }

}
