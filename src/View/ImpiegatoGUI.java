package View;

import Controller.Controller;
import Model.Impiegato;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectStreamException;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class ImpiegatoGUI {
    private JPanel impiegatoMainPanel;
    private JButton backBtn;
    JFrame frame;
    private JButton addBtn;
    private JButton removeBtn;
    private JButton promuoviBtn;
    private JPanel btnPanel;
    private JScrollPane jsp;
    private JTable impTable;
    private JTable profileTable;
    private JPanel profilePanel;

    public ImpiegatoGUI(Controller controller, JFrame prevFrame) {
        impiegatoMainPanel = new JPanel();
        profilePanel = new JPanel();
        frame = new JFrame("Impiegati");
        frame.setSize(1280, 720);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(impiegatoMainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        String[] colonne = {"CF", "Nome", "Cognome", "DataNascita", "DataAssunzione", "CodiceCon", "Categoria",
                "Salario", "Eta"};

        List<Impiegato> impiegati = controller.getImpiegatiDB();
        Set<String> cfSet = new HashSet<>();

        int i = 0;
        for (Impiegato imp : impiegati) {
            String cf = imp.getCf();
            if (!cfSet.contains(cf)) {
                cfSet.add(cf);
            }
        }

        Object[][] righe = new Object[cfSet.size()][colonne.length];

        for (Impiegato imp : impiegati) {
            if (i < cfSet.size()) {
                righe[i] = new Object[]{imp.getCf(), imp.getNome(), imp.getCognome(), imp.getDataNascita(),
                        imp.getDataAssunzione(), imp.getCodiceCon(), imp.getCategoria(), imp.getSalario(), imp.getEta()};
                i++;
            } else
                break;
        }

        DefaultTableModel dtm = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Object[] riga : righe) {
            dtm.addRow(riga);
        }

        impTable = new JTable();
        impTable.setModel(dtm);
        impTable.setRowHeight(30);
        impTable.getTableHeader().setReorderingAllowed(false);
        impTable.getTableHeader().setResizingAllowed(false);
        impTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        impTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rigaSelezionata = impTable.getSelectedRow();
                String selected_cf = "";
                if (rigaSelezionata != -1)
                    selected_cf = impTable.getValueAt(rigaSelezionata, 0).toString();
                profileTable = new JTable();

                ArrayList<String> info = controller.getListaPromozioni(selected_cf);;
                String lab = new String();
                String prog = new String();
                int dim = info.size();
                try {
                    lab = controller.getAfferenzeImp(selected_cf).get(0);
                    prog = controller.getProgettiImp(selected_cf).get(0);
                    dim++;
                } catch (NullPointerException ex) {
                    lab = "";
                    prog = "";
                }


                Object[][] righe_pop;
                int dim1 = 0;
                if(lab!=null && prog!=null) {
                    righe_pop = new Object[dim/2 + 1][2];
                    righe_pop[0][0] = lab;
                    righe_pop[0][1] = prog;
                    dim1++;
                    for (int i = 0; i < dim/2 + 1; i++) {
                        if (i + 1 < dim/2 + 1) {
                            righe_pop[i+1][0] = info.get(2*i);
                            righe_pop[i+1][1] = info.get(2*i + 1);
                            dim1++;
                        }
                    }
                }else{
                    righe_pop = new Object[dim/2 + 1][2];
                    for (int i = 0; i < dim/2 + 1; i++) {
                        if (i + 1 < dim) {
                            righe_pop[i][0] = info.get(2*i);
                            righe_pop[i][1] = info.get(2*i + 1);
                            dim1++;
                        }
                    }
                }

                DefaultTableModel profileTableModel = new DefaultTableModel(righe_pop, new String[]{"Info imp1", "Info imp2"}) {
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
        jsp = new JScrollPane(impTable);
        jsp.setPreferredSize(new Dimension(800, 500));
        addBtn = new JButton("Aggiungi");
        removeBtn = new JButton("Rimuovi");
        promuoviBtn = new JButton("Promuovi");
        backBtn = new JButton("Torna alla Home");

        btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(addBtn);
        btnPanel.add(removeBtn);
        btnPanel.add(promuoviBtn);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        impiegatoMainPanel.add(backBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        impiegatoMainPanel.add(jsp, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        impiegatoMainPanel.add(profilePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        impiegatoMainPanel.add(btnPanel, gbc);

        Border compoundBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        btnPanel.setBorder(compoundBorder);

        backBtn.addActionListener(e -> {
            prevFrame.setVisible(true);
            frame.dispose();
        });

        addBtn.addActionListener(e -> {
            AddImpiegatoGUI addImpiegatoGUI = new AddImpiegatoGUI(controller);
            addImpiegatoGUI.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    ricaricaTabella(controller, colonne);
                }
            });
        });

        removeBtn.addActionListener(e -> {
            String selected_cf = impTable.getValueAt(impTable.getSelectedRow(), 0).toString();

            int selection = JOptionPane.showConfirmDialog(null, "Sicuro di voler licenziare " +
                    "l'impiegato?", "Conferma", JOptionPane.YES_NO_OPTION);
            if (selection == JOptionPane.YES_OPTION) {
                try {
                    controller.rimuoviImp(selected_cf);
                    JOptionPane.showMessageDialog(null, "Eliminazione eseguita correttamente!",
                            "Successo", JOptionPane.INFORMATION_MESSAGE);
                    ricaricaTabella(controller, colonne);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Impossibile rimuovere l'impiegato! Motivo: " +
                            ex.toString(), "Insuccesso", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        promuoviBtn.addActionListener(e -> {
            int selezione = JOptionPane.showConfirmDialog(null, "L'attuale impiegato ha merito per diventare " +
                    "dirigente?", "Conferma", JOptionPane.YES_NO_OPTION);

            if (selezione == JOptionPane.YES_OPTION) {
                String selected_cf = impTable.getValueAt(impTable.getSelectedRow(), 0).toString();
                boolean merito = selezione == JOptionPane.YES_OPTION;
                if (merito) {
                    try {
                        controller.promuoviImp(selected_cf, merito);
                        JOptionPane.showMessageDialog(null, "Promozione avvenuta con successo! ",
                                "Successo", JOptionPane.INFORMATION_MESSAGE);
                        ricaricaTabella(controller, colonne);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Impossibile promuovere l'impiegato! Motivo: " +
                                ex.toString(), "Insuccesso", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    try {
                        controller.promuoviImp(selected_cf, merito);
                        JOptionPane.showMessageDialog(null, "La promozione potrebbe non variare, in quanto" +
                                        " le promozioni variano in base alla permanenza in azienda. ", "Successo",
                                JOptionPane.INFORMATION_MESSAGE);
                        ricaricaTabella(controller, colonne);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Impossibile promuovere l'impiegato! Motivo: " +
                                ex.toString(), "Insuccesso", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
    }

    private void ricaricaTabella(Controller controller, String[] colonne) {
        if (controller != null && colonne.length != 0) {
            List<Impiegato> impiegati = controller.getImpiegatiDB();

            Set<String> cfSet = new HashSet<>();
            for (Impiegato imp : impiegati) {
                String cf = imp.getCf();
                if (!cfSet.contains(cf)) {
                    cfSet.add(cf);
                }
            }

            Object[][] righe = new Object[cfSet.size()][9];
            int i = 0;
            for (; i < cfSet.size(); i++) {
                Impiegato imp = impiegati.get(i);
                righe[i][0] = imp.getCf();
                righe[i][1] = imp.getNome();
                righe[i][2] = imp.getCognome();
                righe[i][3] = imp.getDataNascita();
                righe[i][4] = imp.getDataAssunzione();
                righe[i][5] = imp.getCodiceCon();
                righe[i][6] = imp.getCategoria();
                righe[i][7] = imp.getSalario();
                righe[i][8] = imp.getEta();
            }
        }
    }
}