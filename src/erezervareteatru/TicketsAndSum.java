package erezervareteatru;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicketsAndSum extends JFrame {
    JPanel jPanel;
    JLabel jLabelPrice;
    JButton jButtonCalculate;
    JButton jButtonNext;
    JList<String> jListAvailable;
    DefaultListModel defaultListModel;
    JScrollPane jScrollPane;
    Show show;
    Representation representation;
    private List myChairs;
    private double price;
    private List<Integer> helperList;

    public TicketsAndSum(Show show, Representation representation) {
        this.show = show;
        this.representation = representation;
        this.helperList = new ArrayList<>();

        //initializez componentele

        //setez selectare multipla si dimensiunea celulelor listei + listmodelul
        defaultListModel = new DefaultListModel<>();
        jListAvailable = new JList(defaultListModel);
        jListAvailable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jListAvailable.setFixedCellHeight(50);
        jListAvailable.setFixedCellWidth(200);
        for (Integer loc : representation.getAvailableChairs()) {
            defaultListModel.addElement("Locul " + loc);
        }
        jLabelPrice = new JLabel("Calculati in timp real costul:");
        jListAvailable.add(new Scrollbar());
        jScrollPane = new JScrollPane(jListAvailable);
        jButtonCalculate = new JButton("Calculeaza");

        //adaug action listener pe buton
        jButtonCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myChairs = jListAvailable.getSelectedValuesList();
                //aici fac o lista helper ca sa pot scoate direct intregii din lista in Pay
                // (in loc sa am "locul x" voi avea "x")
                for (int i : jListAvailable.getSelectedIndices())
                    helperList.add(i+1);

                price = myChairs.size() * representation.getPrice();
                jLabelPrice.setText("Totul va costa " + price);
            }
        });
        jButtonNext = new JButton("Inainte");
        //adaug action listener pe buton
        jButtonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonCalculate.doClick();
                //inchide fereastra curenta
                dispose();
                //se duce in urmatoarea fereastra
                new Pay(show, representation, helperList, price, myChairs);
            }
        });

        //creem containerul si punem totul in el in ordine
        jPanel = new JPanel(new FlowLayout (FlowLayout.CENTER));
        jPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jPanel.add(jLabelPrice);
        jPanel.add(jScrollPane);
        jPanel.add(jButtonCalculate);
        jPanel.add(jButtonNext);
        jPanel.setSize(500, 400);
        add(jPanel);
        setTitle("Alegeti-va locurile");
        pack();
        setVisible(true);
    }

}
