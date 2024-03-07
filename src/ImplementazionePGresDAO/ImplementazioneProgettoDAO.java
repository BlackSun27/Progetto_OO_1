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
    public void getProgResp(String cup, String Cf_Resp){
        try{
            String query = "SELECT cf FROM progetto AS p JOIN impiegato ON " +
                    "p.cf = i.cf WHERE p.cup = ? AND i.categoria = ?";
            PreparedStatement stm = c.prepareStatement(query);
            stm.setString(1, cup);
            stm.setString(2, "dirigente");

            ResultSet rs = stm.executeQuery();

            while(rs.next())
                Cf_Resp= rs.getString(1);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getProgRef(String cup, String Cf_Ref){
        try{
            String query = "SELECT cf FROM progetto AS p JOIN impiegato ON " +
                    "p.cf = i.cf WHERE p.cup = ? AND i.categoria = ?";
            PreparedStatement stm = c.prepareStatement(query);
            stm.setString(1, cup);
            stm.setString(2, "senior");

            ResultSet rs = stm.executeQuery();

            while(rs.next())
                Cf_Ref=rs.getString(1);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getLabProg(String cup, String Lab1, String Lab2, String Lab3, ArrayList<String> l_Lab){
        try{
            String query = "SELECT lab1, lab2, lab3 FROM lavora WHERE cup = ?";
            PreparedStatement stm = c.prepareStatement(query);
            stm.setString(1, cup);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Lab1 = rs.getString(1);
                Lab2 = rs.getString(2);
                Lab3 = rs.getString(3);
            }

            l_Lab.add(Lab1);
            l_Lab.add(Lab2);
            l_Lab.add(Lab3);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}