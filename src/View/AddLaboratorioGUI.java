package View;

import Controller.*;

import javax.swing.*;
import java.sql.*;

public class AddLaboratorioGUI {
    public JFrame frame;
    private JPanel laboratorioPanel;
    private JTextField resp_sciField;
    private JTextField nomeTextField;
    private JTextField topicField;
    private JLabel resp_sciLabel;
    private JLabel nomeLabel;
    private JLabel topicLabel;
    private JButton okBtn;

    public AddLaboratorioGUI(Controller controller){
        laboratorioPanel = new JPanel();
        laboratorioPanel.setLayout(new BoxLayout(laboratorioPanel, BoxLayout.Y_AXIS));
        frame = new JFrame("Inserimento laboratorio");
        frame.setContentPane(laboratorioPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(580, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        nomeLabel = new JLabel("Nome: ");
        nomeTextField = new JTextField(20);
        resp_sciLabel = new JLabel("CF Responsabile Scientifico: ");
        resp_sciField = new JTextField(20);
        topicLabel = new JLabel("Topic: ");
        topicField = new JTextField(60);

        laboratorioPanel.add(nomeLabel);
        laboratorioPanel.add(nomeTextField);
        laboratorioPanel.add(resp_sciLabel);
        laboratorioPanel.add(resp_sciField);
        laboratorioPanel.add(topicLabel);
        laboratorioPanel.add(topicField);

        okBtn = new JButton("OK");

        okBtn.addActionListener(e -> {
            int risposta = JOptionPane.showConfirmDialog(null, "Vuoi inserire il seguente laboratorio? ",
                    "Conferma", JOptionPane.YES_NO_OPTION);
            if(risposta == JOptionPane.YES_OPTION){
                String nome = nomeTextField.getText();
                String resp = resp_sciField.getText();
                String topic = topicField.getText();
                try {
                    controller.aggiungiLab(nome, resp, topic);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
