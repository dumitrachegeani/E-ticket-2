package erezervareteatru;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FindShow extends JFrame {
    JPanel jPanel;
    JLabel jLabelShow;
    JTextField jTextFieldShow;
    JButton jButtonSearch;
    JList<String> jListRepresentaions;
    DefaultListModel<String> defaultListModel;
    JScrollPane jScrollPane;
    Show myShow;
    FindShow(List<Show> shows) {
        //initializez componentele
        defaultListModel = new DefaultListModel<>();
        jListRepresentaions = new JList<>(defaultListModel);
        jListRepresentaions.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int i = jListRepresentaions.getSelectedIndex();
                new TicketsAndSum(myShow, myShow.getRepresentations().get(i));
                defaultListModel.clear();
            }
        });
        jTextFieldShow = new JTextField("tastati aici numele spectacolului");
        jListRepresentaions.add(new Scrollbar());
        jScrollPane = new JScrollPane(jListRepresentaions);
        jButtonSearch = new JButton("Search");
        //adaug action listener pe buton
        jButtonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jTextFieldShow.getText().trim();
                defaultListModel.clear();
                //caut showl respectiv in lista mea de showuri
                for (Show show : shows) {
                    if (show.getName().equals(name)) {
                        //am gasit showul si ii afisez reprezentatiile (folosind toString)
                        myShow = show;
                        for (Representation representation: show.getRepresentations())
                            if (representation.getAvailableChairs().size() > 0)
                                defaultListModel.addElement(representation.toString());
                        return;
                    }
                }
                //nu am gasit si afisez mesaj de eroare
                JOptionPane.showMessageDialog(jPanel, "Nu am gasit spectacolul: " + name);
            }
        });

        jPanel = new JPanel(new FlowLayout (FlowLayout.CENTER));
        jPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jPanel.add(new JLabel("Alegeti spectacolul la care doriti sa mergeti"));
        jPanel.add(jTextFieldShow);
        jPanel.add(jScrollPane);
        jPanel.add(jButtonSearch);
        add(jPanel);
        setTitle("Cautati un spectacol");
        setSize(300,300);
        setVisible(true);
    }

}