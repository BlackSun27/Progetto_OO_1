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
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class ImpiegatoGUI {

    DefaultTableModel dfm;
    private JPanel impiegatoMainPanel;
    private JPanel impiegatoPanel;
    private JButton backBtn;
    JFrame frame;
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

    public ImpiegatoGUI(Controller controller, JFrame prevFrame) {
        impiegatoMainPanel = new JPanel();
        frame = new JFrame("Impiegati");
        frame.setSize(1280, 720);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(impiegatoMainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        String[] colonne = {"CF", "Nome", "Cognome", "DataNascita", "DataAssunzione", "CodiceCon", "Categoria",
                "Salario", "Eta"};

        List<Impiegato> impiegati = controller.getImpiegatiDB();

        Object[][] righe = new Object[impiegati.size()][colonne.length];
        int i = 0;
        for(Impiegato imp: impiegati){
            righe[i] = new Object[]{imp.getCf(),imp.getNome(),imp.getCognome(),
                    imp.getDataNascita(),imp.getDataAssunzione(),imp.getCodiceCon(),
                    imp.getSalario(),imp.getCategoria(),imp.getEta()};
            i++;
        }

        DefaultTableModel dtm = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        i=0;

        for(Object[] riga : righe){
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
                String selected_cf = impTable.getValueAt(impTable.getSelectedRow(), 0).toString();
                //a_ specifica che sia un array
                String a_Lab = controller.getAfferenzeImp(selected_cf);
                String a_Prog = controller.getProgettiImp(selected_cf);
                dtm.getDataVector().removeAllElements();

                Vector<String> riga = new Vector<>();
                riga.add(a_Lab);
                dtm.getDataVector().add(riga);

                riga.add(a_Prog);
                dtm.getDataVector().add(riga);

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
        jsp = new JScrollPane(impTable);
        jsp.setPreferredSize(new Dimension(800,500));
        addBtn = new JButton("Aggiungi");
        removeBtn = new JButton("Rimuovi");
        promuoviBtn = new JButton("Promuovi");
        backBtn = new JButton("Torna alla Home");

        btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(addBtn);
        btnPanel.add(removeBtn);
        btnPanel.add(promuoviBtn);

        GridBagConstraints gbc = new GridBagConstraints();

        // Aggiungi il pulsante "Torna alla Home" in alto a sinistra
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Posiziona nell'angolo in alto a sinistra
        impiegatoMainPanel.add(backBtn, gbc);

// Aggiungi la tabella
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3; // Usa tutta la larghezza disponibile per la tabella
        gbc.fill = GridBagConstraints.BOTH; // Per far espandere la tabella in entrambe le direzioni
        gbc.weightx = 1.0; // Per far espandere la tabella orizzontalmente
        gbc.weighty = 1.0; // Per far espandere la tabella verticalmente
        impiegatoMainPanel.add(jsp, gbc);

// Aggiungi il pannello dei pulsanti sotto la tabella
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER; // Posiziona i pulsanti al centro
        gbc.insets = new Insets(10, 10, 10, 10); // Spaziatura
        impiegatoMainPanel.add(btnPanel, gbc);


        /*gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.anchor = GridBagConstraints.PAGE_START;
        impiegatoMainPanel.add(btnPanel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 5, 0, 5);
        impiegatoMainPanel.add(addBtn, gbc);

        gbc.gridx = 1;
        impiegatoMainPanel.add(removeBtn, gbc);

        gbc.gridx = 2;
        impiegatoMainPanel.add(promuoviBtn, gbc);*/

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
                    ex.printStackTrace();
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
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        controller.promuoviImp(selected_cf, merito);
                        JOptionPane.showMessageDialog(null, "La promozione potrebbe non variare, in quanto" +
                                        " le promozioni variano in base alla permanenza in azienda. ", "Successo",
                                JOptionPane.INFORMATION_MESSAGE);
                        ricaricaTabella(controller, colonne);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

    }

    private void ricaricaTabella(Controller controller, String[] colonne) {
        if (controller != null && colonne.length != 0) {
            List<Impiegato> impiegati = controller.getImpiegatiDB();

            Object[][] righe = new Object[impiegati.size()][9];
            int i = 0;
            for (; i < impiegati.size(); i++) {
                Impiegato imp = impiegati.get(i);
                righe[i][0] = imp.getCf();
                righe[i][1] = imp.getNome();
                righe[i][2] = imp.getCognome();
                righe[i][3] = imp.getDataNascita();
                righe[i][4] = imp.getDataAssunzione();
                righe[i][5] = imp.getCodiceCon();
                righe[i][6] = imp.getSalario();
                righe[i][7] = imp.getCategoria();
                righe[i][8] = imp.getEta();
            }

            DefaultTableModel dtm = (DefaultTableModel) impTable.getModel();
            dtm.setDataVector(righe, colonne);
            TableColumnModel columnModel = impTable.getColumnModel();
            for (i=0; i < impTable.getColumnCount(); i++) {
                TableColumn column = columnModel.getColumn(i);
                int width = getColumnWidth(i);
                column.setPreferredWidth(width);
            }
        }
    }

    private int getColumnWidth(int columnIndex) {
        int width;
        TableColumn column = impTable.getColumnModel().getColumn(columnIndex);
        TableCellRenderer renderer = column.getHeaderRenderer();
        if (renderer == null) {
            renderer = impTable.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(
                impTable, column.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;

        for (int row = 0; row < impTable.getRowCount(); row++) {
            renderer = impTable.getCellRenderer(row, columnIndex);
            Component component = renderer.getTableCellRendererComponent(
                    impTable, impTable.getValueAt(row, columnIndex), false, false, row, columnIndex);
            width = Math.max(width, component.getPreferredSize().width);
        }

        return width + impTable.getIntercellSpacing().width;
    }
}