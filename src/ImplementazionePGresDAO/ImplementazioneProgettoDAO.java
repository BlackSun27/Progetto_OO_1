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

    @Override
    public void getProgResp(String cup, ArrayList<String> Cf_Resp){
        try{
            String query = "SELECT cf FROM progetto AS p JOIN impiegato ON " +
                    "p.cf = i.cf WHERE p.cup = ? AND i.categoria = ?";
            PreparedStatement stm = c.prepareStatement(query);
            stm.setString(1, cup);
            stm.setString(2, "dirigente");

            ResultSet rs = stm.executeQuery();

            while(rs.next())
                Cf_Resp.add(rs.getString(1));

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getProgRef(String cup, ArrayList<String> Cf_Ref){
        try{
            String query = "SELECT cf FROM progetto AS p JOIN impiegato ON " +
                    "p.cf = i.cf WHERE p.cup = ? AND i.categoria = ?";
            PreparedStatement stm = c.prepareStatement(query);
            stm.setString(1, cup);
            stm.setString(2, "senior");

            ResultSet rs = stm.executeQuery();

            while(rs.next())
                Cf_Ref.add(rs.getString(1));

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getLabProg(String cup, ArrayList<String> l_Lab1, ArrayList<String> l_Lab2,
                           ArrayList<String> l_Lab3){
        try{
            String query = "SELECT lab1, lab2, lab3 FROM lavora WHERE cup = ?";
            PreparedStatement stm = c.prepareStatement(query);
            stm.setString(1, cup);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                l_Lab1.add(rs.getString(1));
                l_Lab2.add(rs.getString(2));
                l_Lab3.add(rs.getString(3));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}