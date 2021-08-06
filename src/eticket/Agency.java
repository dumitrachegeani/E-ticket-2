package eticket;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Agency {
    private static List<Show> shows;

    public static void main(String[] args) throws Exception {
        shows = new ArrayList<>();

        //citim spectacolele din fisier
        final Scanner scanner = new Scanner(new File("test.txt"));
        int[] ints = new int[7];
        String name;
        while (scanner.hasNext()) {
            name = scanner.next() + scanner.nextLine();
            for (int i = 0; i < 7; i++)
                ints[i] = scanner.nextInt();


            Representation representation = new Representation(ints[0], ints[1], ints[2], ints[3], ints[4], ints[5], ints[6]);
            Show.addFromFile(shows, name, representation);
        }
        //deschidem prima fereastra "FindShow"
        new FindShow(shows);

    }
}