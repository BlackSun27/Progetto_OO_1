package View;

import Controller.Controller;

import javax.swing.*;

public class Home {

    private JPanel mainPanel;
    private JButton impiegatobtn;
    private JButton laboratoriobtn;
    private JButton progettobtn;
    private JPanel btnPanel;

    public Home(){
        Controller controller = new Controller();

        JFrame frame = new JFrame("Sistema di gestione del personale in un'azienda");

        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        impiegatobtn.addActionListener(e->{
            frame.setVisible(true);
            new ImpiegatoGUI(controller,frame);
        });

        laboratoriobtn.addActionListener(e->{
            frame.setVisible(true);
            new LaboratorioGUI(controller,frame);
        });

        progettobtn.addActionListener(e->{
            frame.setVisible(true);
            new ProgettoGUI(controller,frame);
        });
    }

}
