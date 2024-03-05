package View;

import Controller.*;

import javax.swing.*;
import java.sql.*;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class AddImpiegatoGUI {
    public JFrame frame;
    private JPanel impMainPanel;
    private JTextField cfTextField;
    private JTextField nomeTextField;
    private JTextField cognomeTextField;
    private JTextField dataNascitaField;
    private JTextField codiceConField;
    private JLabel cfLabel;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel dataNascitaLabel;
    private JLabel codiceConLabel;
    private JButton annullaBtn;
    private JButton okBtn;

    public AddImpiegatoGUI(Controller controller){
        frame = new JFrame("Inserimento Impiegati");
        frame.setContentPane(impMainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(580, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        annullaBtn.addActionListener(e->frame.dispose());

        okBtn.addActionListener(e->{
            int risposta1 = JOptionPane.showConfirmDialog(null, "Vuoi inserire il seguente impiegato? ",
                    "Conferma", JOptionPane.YES_NO_OPTION);
            if(risposta1 == JOptionPane.YES_OPTION) {
                String cf = cfTextField.getText();
                String nome = nomeTextField.getText();
                String cognome = cognomeTextField.getText();
                //String cf, String nome, String cognome, Date dataNascita, Date dataAssunzione,
                //String codiceCon, boolean merito, float salario, String categoria, int eta
                String dataNascita_S = dataNascitaField.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dataNascita_LD = LocalDate.parse(dataNascita_S, formatter);
                Date dataNascita = Date.valueOf(dataNascita_LD);
                LocalDate dataAttuale_LD = LocalDate.now();
                Date dataAttuale = Date.valueOf(dataAttuale_LD);
                String codiceCon = codiceConField.getText();
                int risposta2 = JOptionPane.showConfirmDialog(null, "L'impiegato inserito ha merito? ",
                        "Conferma", JOptionPane.YES_NO_OPTION);
                boolean merito = risposta2 == JOptionPane.YES_OPTION;
                float salario = 1500.00f;
                String categoria = "Junior";
                if(merito) {
                    salario = 3000.00f;
                    categoria = "Dirigente";
                }
                Period periodo = Period.between(dataNascita_LD, dataAttuale_LD);
                int eta = periodo.getYears();

                try{
                    controller.aggiungiImp(cf, nome, cognome, dataNascita, dataAttuale, codiceCon,
                            merito, salario, categoria, eta);
                    JOptionPane.showMessageDialog(null, "Inserimento avvenuto con successo! ", "Successo",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });

    }
}
