package ImplementazionePGresDAO;

import ConnectDB.DbConn;
import DAO.ProgettoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImplementazioneProgettoDAO implements ProgettoDAO {
    private DbConn db;
    private Connection c;
    public ImplementazioneProgettoDAO() {
        db = new DbConn();
        try {
            c = db.conn_db("postgres", "postgres", "Blacks_27");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inserisciProgetto(String cup, String refSci, String resp, String nome, float budget)
            throws SQLException {
        String query = "CALL inserisciprogetto(?,?,?,?,?); ";
        PreparedStatement stm = c.prepareStatement(query);
        stm.setString(1, cup);
        stm.setString(2, refSci);
        stm.setString(3, resp);
        stm.setString(4, nome);
        stm.setDouble(5, budget);

        stm.executeUpdate();
    }

    @Override
    public void rimuoviProgetto(String cup) throws SQLException {
        String query = "CALL rimuovi_progetto(?); ";
        PreparedStatement stm = c.prepareStatement(query);
        stm.setString(1, cup);

        stm.executeUpdate();
    }

    @Override
    public void aggiungiLaboratorio(String cup, String nomeLab) throws SQLException{
        String query = "CALL inseriscilavora(?)";
        PreparedStatement stm = c.prepareStatement(query);
        stm.setString(1, cup);

        stm.executeUpdate();
    }

    public void getProgLavora(String nomelab, ArrayList<String> l_CUP){
        try{
            String query = "SELECT cup FROM lavora WHERE lab1 = ? OR lab2 = ? OR lab3 = ?";
            PreparedStatement stm = c.prepareStatement(query);
            stm.setString(1,nomelab);
            stm.setString(2,nomelab);
            stm.setString(3,nomelab);

            ResultSet rs = stm.executeQuery();

            while(rs.next())
                l_CUP.add(rs.getString(1));

        }catch (SQLException e){
            System.out.println("Errore a ottenere informazioni sulle afferenze!");
            e.printStackTrace();
        }
    }

}