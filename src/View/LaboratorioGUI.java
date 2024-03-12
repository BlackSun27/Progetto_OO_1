package View;

import Controller.Controller;
import Model.Laboratorio;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectStreamException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class LaboratorioGUI {
    private JPanel laboratorioMainPanel;
    private JButton backBtn;
    JFrame frame;
    private JButton addBtn;
    private JButton removeBtn;
    private JButton newAffBtn;
    private JPanel btnPanel;
    private JScrollPane jsp;
    private JTable labTable;
    private JTable profileTable;
    private JPanel profilePanel;
    private JTextField afferenteField;
    private JLabel afferenteLabel;
    private JPanel afferenteAddPanel;
    public LaboratorioGUI(Controller controller, JFrame prevFrame){
        laboratorioMainPanel = new JPanel();
        profilePanel = new JPanel();
        frame = new JFrame("Laboratorio");
        frame.setSize(1280, 720);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(laboratorioMainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        String colonne[] = {"Nome", "Resp. Scien.", "Topic", "Num. Afferenti"};

        List<Laboratorio> laboratori = controller.getLaboratoriDB();
        Set<String> nomiSet = new HashSet<>();
        for(Laboratorio lab: laboratori){
            String nome = lab.getNome();
            if(!nomiSet.contains(nome))
                nomiSet.add(nome);
            else
                break;
        }
        int i=0;
        Object[][] righe = new Object[nomiSet.size()][colonne.length];
        for(Laboratorio lab : laboratori){
            if(i<nomiSet.size()) {
                righe[i] = new Object[]{lab.getNome(), lab.getResp_sci(), lab.getTopic(), lab.getN_afferenti()};
                i++;
            }else
                break;
        }

        DefaultTableModel dtm = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for(Object[] riga : righe){
            dtm.addRow(riga);
        }

        labTable = new JTable();
        labTable.setModel(dtm);
        labTable.setRowHeight(30);
        labTable.getTableHeader().setReorderingAllowed(false);
        labTable.getTableHeader().setResizingAllowed(false);
        labTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        labTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rigaSelezionata = labTable.getSelectedRow();
                String nome = "";
                if(rigaSelezionata != -1)
                    nome = labTable.getValueAt(rigaSelezionata, 0).toString();
                profileTable = new JTable();

                String prog = controller.getCUPfromLab(nome);
                ArrayList<String> info_resp = controller.getRespSciLab(nome);

                Object[][] colonne = new Object[info_resp.size() + 1][2];
                colonne[0][0] = "Prog";
                colonne[0][1] = "Resp Sci";
                for(int i=1; i<info_resp.size() + 1; i++){
                    colonne[i][0] = prog;
                    colonne[i][1] = info_resp.get(i-1);
                }

                DefaultTableModel profileTableModel = new DefaultTableModel(colonne, new String[]{"Prog", "Resp Sci"}) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                profileTable.setModel(profileTableModel);
                profilePanel.removeAll();
                profilePanel.add(profileTable);
                profilePanel.repaint();
                profilePanel.revalidate();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        jsp = new JScrollPane(labTable);
        jsp.setPreferredSize(new Dimension(700, 400));
        addBtn = new JButton("Aggiungi Lab");
        removeBtn = new JButton("Rimuovi");
        newAffBtn = new JButton("Nuovo Afferente");
        backBtn = new JButton("Torna alla Home");

        btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(addBtn);
        btnPanel.add(removeBtn);
        btnPanel.add(newAffBtn);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10,10,10,10);
        laboratorioMainPanel.add(backBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        laboratorioMainPanel.add(jsp, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        laboratorioMainPanel.add(profilePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        laboratorioMainPanel.add(btnPanel, gbc);

        Border compoundBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        btnPanel.setBorder(compoundBorder);

        backBtn.addActionListener(e -> {
            prevFrame.setVisible(true);
            frame.dispose();
        });

        removeBtn.addActionListener(e->{
            String nome = labTable.getValueAt(labTable.getSelectedRow(), 0).toString();
            int selection = JOptionPane.showConfirmDialog(null, "Sicuro di voler rimuovere il laboratorio?",
                    "Conferma", JOptionPane.YES_NO_OPTION);

            if(selection == JOptionPane.YES_OPTION){
                try {
                    controller.rimuoviLab(nome);
                    JOptionPane.showMessageDialog(null, "Rimozione avvenuta con successo! ", "Conferma",
                            JOptionPane.INFORMATION_MESSAGE);
                    ricaricaTabella(controller, colonne);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        newAffBtn.addActionListener(e->{
             int rigaSelezionata = labTable.getSelectedRow();
             String nome = "";
             if(rigaSelezionata != -1)
                nome = labTable.getValueAt(rigaSelezionata, 0).toString();
             int selezione = JOptionPane.showConfirmDialog(null, "Vuoi aggiungere un nuovo afferente?",
                     "Conferma", JOptionPane.YES_NO_OPTION);
             afferenteAddPanel = new JPanel();
             afferenteAddPanel.setLayout(new BoxLayout(afferenteAddPanel, BoxLayout.Y_AXIS));
             if(selezione == JOptionPane.YES_OPTION){
                 afferenteLabel = new JLabel("CF: ");
                 afferenteField = new JTextField();

                 JOptionPane.showMessageDialog(null, afferenteAddPanel, "Inserisci il CF",
                         JOptionPane.PLAIN_MESSAGE);

                 String cf = afferenteField.getText();
                 try {
                     controller.aggiungiAfferenteLab(nome, cf);
                     ricaricaTabella(controller, colonne);
                 } catch (SQLException ex) {
                     ex.printStackTrace();
                 }
             }
        });

        addBtn.addActionListener(e->{
            AddLaboratorioGUI laboratorioGUI = new AddLaboratorioGUI(controller);

            laboratorioGUI.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    ricaricaTabella(controller, colonne);
                }
            });
        });
    }

    private void ricaricaTabella(Controller controller, String[] colonne) {
        if (controller != null && colonne.length != 0) {
            List<Laboratorio> laboratori = controller.getLaboratoriDB();
            Set<String> nomiSet = new HashSet<>();
            for(Laboratorio lab: laboratori){
                String nome = lab.getNome();
                if(!nomiSet.contains(nome))
                    nomiSet.add(nome);
                else
                    break;
            }

            Object[][] righe = new Object[nomiSet.size()][colonne.length];
            for (int i = 0; i < nomiSet.size(); i++) {
                Laboratorio lab = laboratori.get(i);
                righe[i][0] = lab.getNome();
                righe[i][1] = lab.getResp_sci();
                righe[i][2] = lab.getTopic();
                righe[i][3] = lab.getN_afferenti();
            }

            DefaultTableModel dtm = new DefaultTableModel(righe, colonne) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            labTable.setModel(dtm);
            labTable.setRowHeight(30);
            labTable.getTableHeader().setReorderingAllowed(false);
            labTable.getTableHeader().setResizingAllowed(false);
            labTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        }
    }


}
