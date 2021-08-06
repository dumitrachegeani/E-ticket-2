package erezervareteatru;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Pay extends JFrame {
    JPanel jPanel;
    JButton jButtonPay;
    JTextField jTextFieldName;
    JTextField jTextFieldPhone;
    JTextField jTextFieldPrice;

    private double price;
    private Representation representation;
    private List<Integer> helperList;

    //helper list e folosit pentru a elimina mai usor integerurile din lista de locuri disponibile
    public Pay(Show show, Representation representation, List<Integer> helperList, double price, List<String> chairs) {
        //initializam campurile
        this.price = price;
        this.representation = representation;
        this.helperList = helperList;

        jTextFieldName = new JTextField("Numele dumnveavoastra");
        jTextFieldPhone = new JTextField("0720 xxx xxx");
        jTextFieldPrice = new JTextField("" + price);

        //adaugam action listener pe buton (plateste)
        jButtonPay = new JButton("Plateste");
        jButtonPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //verific daca fieldul de price este gol sau nu
                double enteredPrice = jTextFieldPrice.getText().isEmpty() ? 0 : Double.parseDouble(jTextFieldPrice.getText());
                if (enteredPrice >= price) {
                    JOptionPane.showMessageDialog(jPanel, "Rest: " + (enteredPrice - price));
                    //scoatem locurile cumparate din lista de locuri disponibile la eveniment
                    representation.getAvailableChairs().removeAll(helperList);
                    //adauga clientul la lista de clienti din reprezentatii
                    representation.addClient(jTextFieldName.getText(), jTextFieldPhone.getText(),
                                                new Ticket(show, representation, price, chairs));
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(jPanel, "Suma prea mica: " + (price - enteredPrice));
            }
        });
        //creem containerul si punem totul in el in ordine
        jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        //nume
        jPanel.add(new JLabel("Introduceti numele"), JPanel.LEFT_ALIGNMENT);
        jPanel.add(jTextFieldName, JPanel.RIGHT_ALIGNMENT);
        //telefon mobil
        jPanel.add(new JLabel("Introduceti numarul de telefon"), JPanel.LEFT_ALIGNMENT);
        jPanel.add(jTextFieldPhone, JPanel.RIGHT_ALIGNMENT);
        //suma
        jPanel.add(new JLabel("Introduceti suma (" + price + ") : "), JPanel.LEFT_ALIGNMENT);
        //suma completata automat la deschiderea ferestrei
        jPanel.add(jTextFieldPrice, JPanel.RIGHT_ALIGNMENT);
        jPanel.add(jButtonPay);

        add(jPanel);
        setTitle("Plata");
        setSize(275, 175);
        setVisible(true);
    }

}
