package eticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ticket extends JFrame {
    JPanel jPanel;
    JButton jButtonPrint;
    public Ticket(Show show, Representation representation, double price, List<String> chairs) {
        //verific daca e bilet sau abonament
        String entity = chairs.size() > 1 ? "Abonament" : "Bilet";
        //depinzand de cate locuri sunt pe bilet il facem mai mare
        int width = chairs.size()/3 * 200;
        width = width > 1 ? width : 250;
        //initializam componentele
        jPanel = new JPanel((new GridLayout(5 , 1)));

        jPanel.add(new JLabel(entity, JLabel.CENTER));
        jPanel.add(new JLabel("Numele spectacolului: " + show.getName(), JLabel.CENTER));
        jPanel.add(new JLabel(representation.toString(), JLabel.CENTER));
        jPanel.add(new JLabel(chairs.toString(), JLabel.CENTER));


        //adaugam action listener pe butonul de printare
        jButtonPrint = new JButton("Printeaza");
        jButtonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jPanel, "PRINTING...");
            }
        });
        jPanel.add(jButtonPrint);

        add(jPanel);
        setTitle("Bilet");
        setVisible(true);
        setSize(width, 400);
    }
}
