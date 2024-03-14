package View;

import Controller.*;

import javax.swing.*;
import java.sql.*;

public class AddProgettoGUI {
    public JFrame frame;
    private JPanel progettoPanel;
    private JTextField resp_sciField;
    private JTextField nomeTextField;
    private JTextField budgetField;
    private JTextField cupTextField;
    private JTextField refSciField;
    private JLabel resp_sciLabel;
    private JLabel nomeLabel;
    private JLabel budgetLabel;
    private JLabel cupLabel;
    private JLabel refSciLabel;
    private JButton okBtn;

    public AddProgettoGUI(Controller controller){
        progettoPanel = new JPanel();
        progettoPanel.setLayout(new BoxLayout(progettoPanel, BoxLayout.Y_AXIS));
        frame = new JFrame("Inserimento progetto");
        frame.setContentPane(progettoPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(580, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cupLabel = new JLabel("CUP: ");
        cupTextField = new JTextField(20);
        resp_sciLabel = new JLabel("CF Responsabile: ");
        resp_sciField = new JTextField(20);
        refSciLabel = new JLabel("CF Referente Scientifico: ");
        refSciField = new JTextField(20);
        nomeLabel = new JLabel("Nome: ");
        nomeTextField = new JTextField(20);
        budgetLabel = new JLabel("Budget: ");
        budgetField = new JTextField(20);

        progettoPanel.add(cupLabel);
        progettoPanel.add(cupTextField);
        progettoPanel.add(resp_sciLabel);
        progettoPanel.add(resp_sciField);
        progettoPanel.add(refSciLabel);
        progettoPanel.add(refSciField);
        progettoPanel.add(nomeLabel);
        progettoPanel.add(nomeTextField);
        progettoPanel.add(budgetLabel);
        progettoPanel.add(budgetField);

        okBtn = new JButton("OK");
        progettoPanel.add(okBtn);

        okBtn.addActionListener(e -> {
            int risposta = JOptionPane.showConfirmDialog(null, "Vuoi inserire il seguente laboratorio? ",
                    "Conferma", JOptionPane.YES_NO_OPTION);
            if(risposta == JOptionPane.YES_OPTION){
                String cup = cupTextField.getText();
                String resp = resp_sciField.getText();
                String ref = refSciField.getText();
                String nome = nomeTextField.getText();
                float budget = Float.parseFloat(budgetField.getText());
                if(!nome.isEmpty() || !resp.isEmpty() || !cup.isEmpty() || !ref.isEmpty() || budget !=0) {
                    try {
                        controller.aggiungiProgetto(cup, ref, resp,nome, budget);
                        JOptionPane.showMessageDialog(null, "Inserimento avvenuto con successo!", "Conferma",
                                JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Non posso effettuare l'inserimento" +
                                " motivo: "+ ex.toString(), "Insuccesso", JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Ci sono campi vuoti! ", "Errore",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                }
            }
        });
    }
}
