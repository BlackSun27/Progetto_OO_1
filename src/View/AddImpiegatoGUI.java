package View;

import Controller.*;
import Model.Impiegato;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class AddImpiegatoGUI {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private JFrame frame;
    private JPanel impMainPanel;
    private JTextField cfTextField;
    private JTextField nomeTextField;
    private JTextField cognomeTextField;
    private JTextField dataNascitaField;
    private JLabel cfLabel;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel dataNascitaLabel;

    public AddImpiegatoGUI(Controller controller){
        frame = new JFrame("Inserimento Impiegati");
        frame.setContentPane(impMainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(580, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}
