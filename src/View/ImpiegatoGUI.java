package View;

import Controller.Controller;
import Model.Impiegato;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

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
    private JTable impTable;
    private JTable profileTable;
    private JPanel profilePanel;

    public ImpiegatoGUI(Controller controller, JFrame prevFrame){
        frame = new JFrame("Impiegati");
        frame.setSize(1280, 720);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(impiegatoMainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        String[] colonne = {"CF", "Nome", "Cognome", "DataNascita", "DataAssunzione", "CodiceCon",
        "Salario", "Eta"};

        List<Impiegato> impiegati = controller.getImpiegatiDB();

        Object[][] righe = new Object[impiegati.size()][8];
        int i=0;
        for(; i<impiegati.size(); i++){
            Impiegato imp = impiegati.get(i);
            righe[i][0] = imp.getCf();
            righe[i][1] = imp.getNome();
            righe[i][2] = imp.getCognome();
            righe[i][3] = imp.getDataNascita();
            righe[i][4] = imp.getDataAssunzione();
            righe[i][5] = imp.getCodiceCon();
            righe[i][6] = imp.getSalario();
            righe[i][7] = imp.getEta();
        }

        DefaultTableModel dtm = new DefaultTableModel(righe, colonne){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        impTable.setModel(dtm);
        impTable.setRowHeight(30);
        impTable.getTableHeader().setReorderingAllowed(false);
        impTable.getTableHeader().setResizingAllowed(false);

        impTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selected_cf = impTable.getValueAt(impTable.getSelectedRow(), 0).toString();
                //a_ specifica che sia un array
                String[] a_Lab = controller.getAfferenzeImp(selected_cf).toArray(new String[0]);
                String[] a_Prog = controller.getProgettiImp(selected_cf).toArray(new String[0]);
                dtm.getDataVector().removeAllElements();

                for(String s : a_Lab) {
                    Vector<String> row = new Vector<>();
                    row.add(s);
                    dtm.getDataVector().add(row);
                }
                for(String s : a_Prog){
                    Vector<String> row = new Vector<>();
                    row.add(s);
                    dtm.getDataVector().add(row);
                }
                dtm.fireTableDataChanged();
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

        backBtn.addActionListener(e->{
            prevFrame.setVisible(true);
            frame.dispose();
        });

        addBtn.addActionListener(e->{

        });

        removeBtn.addActionListener(e->{
            String selected_cf = impTable.getValueAt(impTable.getSelectedRow(), 0).toString();

            int selection = JOptionPane.showConfirmDialog(null, "Sicuro di voler licenziare " +
                    "l'impiegato?", "Conferma", JOptionPane.YES_NO_OPTION);
            if(selection == JOptionPane.YES_OPTION){
                try{
                    controller.rimuoviImp(selected_cf);
                    JOptionPane.showMessageDialog(null, "Eliminazione eseguita correttamente!",
                            "Successo", JOptionPane.INFORMATION_MESSAGE);
                    ricaricaTabella(controller, colonne);
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });

        promuoviBtn.addActionListener(e->{
            int selezione = JOptionPane.showConfirmDialog(null, "L'attuale impiegato ha merito per diventare " +
                    "dirigente?", "Conferma", JOptionPane.YES_NO_OPTION);

            if(selezione == JOptionPane.YES_OPTION){
                String selected_cf = impTable.getValueAt(impTable.getSelectedRow(), 0).toString();
                boolean merito = selezione == JOptionPane.YES_OPTION;
                if(merito){
                    try{
                        controller.promuoviImp(selected_cf, merito);
                        JOptionPane.showMessageDialog(null, "Promozione avvenuta con successo! ",
                                "Successo", JOptionPane.INFORMATION_MESSAGE);
                        ricaricaTabella(controller, colonne);
                    } catch (SQLException ex){
                        ex.printStackTrace();
                    }
                }else{
                    try{
                        controller.promuoviImp(selected_cf, merito);
                        JOptionPane.showMessageDialog(null, "La promozione potrebbe non variare, in quanto" +
                                " le promozioni variano in base alla permanenza in azienda. ", "Successo",
                                JOptionPane.INFORMATION_MESSAGE);
                        ricaricaTabella(controller, colonne);
                    } catch (SQLException ex){
                        ex.printStackTrace();
                    }
                }

            }
        });



        /*impiegatoButton = new JButton("Impiegato");
        promozioneButton = new JButton("Promozione");
        laboratorioButton = new JButton("Laboratorio");
        utilizzaButton = new JButton("Utilizza");
        progettoButton = new JButton("Progetto");
        lavoraButton = new JButton("Lavora");

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(impiegatoButton);
        buttonsPanel.add(promozioneButton);
        buttonsPanel.add(laboratorioButton);
        buttonsPanel.add(utilizzaButton);
        buttonsPanel.add(progettoButton);
        buttonsPanel.add(lavoraButton);

        JPanel pannelloInf = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.anchor = GridBagConstraints.PAGE_START;
        pannelloInf.add(labelScelta, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 5, 0, 5);
        pannelloInf.add(impiegatoButton, gbc);

        gbc.gridx = 1;
        pannelloInf.add(promozioneButton, gbc);

        gbc.gridx = 2;
        pannelloInf.add(laboratorioButton, gbc);

        gbc.gridx = 3;
        pannelloInf.add(utilizzaButton, gbc);

        gbc.gridx = 4;
        pannelloInf.add(progettoButton, gbc);

        gbc.gridx = 5;
        pannelloInf.add(lavoraButton, gbc);

        Border compoundBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        buttonsPanel.setBorder(compoundBorder);*/
    }

    private void ricaricaTabella(Controller controller, String[] colonne){
        if(controller!=null && colonne.length != 0){
            List<Impiegato> impiegati = controller.getImpiegatiDB();

            Object[][] righe = new Object[impiegati.size()][8];
            int i=0;
            for(; i<impiegati.size(); i++){
                Impiegato imp = impiegati.get(i);
                righe[i][0] = imp.getCf();
                righe[i][1] = imp.getNome();
                righe[i][2] = imp.getCognome();
                righe[i][3] = imp.getDataNascita();
                righe[i][4] = imp.getDataAssunzione();
                righe[i][5] = imp.getCodiceCon();
                righe[i][6] = imp.getSalario();
                righe[i][7] = imp.getEta();
            }

            DefaultTableModel dtm = (DefaultTableModel) impTable.getModel();
            dtm.setDataVector(righe, colonne);
        }
    }

}
