package View;

import Controller.Controller;
import Model.Impiegato;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImpiegatoGUI {

    DefaultTableModel dfm;
    private JPanel impiegatoMainPanel;
    private JPanel impiegatoPanel;
    private JButton backBtn;
    private JFrame frame;
    private JButton addBtn;
    private JButton removeBtn;
    private JButton promuoviBtn;
    private JButton profileBtn;
    private JPanel btnPanel;
    private JScrollPane jsp;
    private JList<String> profileList;
    private JTable table;
    private JScrollPane jsp;

    public ImpiegatoGUI(Controller controller, JFrame prevFrame){
        frame = new JFrame("Impiegati");
        frame.setSize(1280, 720);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(impiegatoMainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
