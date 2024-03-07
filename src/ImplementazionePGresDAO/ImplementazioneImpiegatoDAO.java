package ImplementazionePGresDAO;

import ConnectDB.DbConn;
import DAO.ImpiegatoDAO;

import java.sql.*;
import java.util.ArrayList;

public class ImplementazioneImpiegatoDAO implements ImpiegatoDAO {
    private DbConn db;
    private Connection c;
    public ImplementazioneImpiegatoDAO(){
        db = new DbConn();
        try{
            c = db.conn_db("postgres", "postgres", "Blacks_27");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void inserisciImpiegato(String cf, String nome, String cognome, Date dataNascita, Date dataAssunzione,
                                   String codiceCon, boolean merito, float salario, String categoria, int eta)
            throws SQLException {
        String queryImp = "CALL inserisciimpiegato(?,?,?,?,?,?,?,?,?);";

        CallableStatement stm = c.prepareCall(queryImp);
        stm.setString(1, cf);
        stm.setString(2, nome);
        stm.setString(3, cognome);
        stm.setDate(4, dataNascita);
        stm.setBoolean(5, merito);
        stm.setString(6, codiceCon);
        stm.setDate(7, dataAssunzione);
        stm.setString(8, categoria);
        stm.setDouble(9, salario);

        stm.executeUpdate();
    }

    @Override
    public void rimuoviImpiegato(String cf) throws SQLException{
        String query = "CALL rimuovi_impiegato(?); ";
        PreparedStatement stm = c.prepareStatement(query);
        stm.setString(1, cf);
        stm.executeUpdate();
    }

    @Override
    public void promuoviImpiegato(String cf, boolean merito) throws SQLException{
        if(merito) {
            String upMerito = "UPDATE Impiegato SET merito = ? WHERE cf = ?";
            try (PreparedStatement stm = c.prepareStatement(upMerito)) {
                stm.setString(1, String.valueOf(merito));
                stm.setString(2, cf);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String query = "CALL inseriscipromozione(?)";
        CallableStatement stm = c.prepareCall(query);
        stm.setString(1, cf);
    }

    @Override
    public void getAfferenzeImp(String cf, String laboratorio){
        try {
            String query = "SELECT nomelab FROM utilizza WHERE cf = ?";
            PreparedStatement stm = c.prepareStatement(query);
            stm.setString(1, cf);
            ResultSet rs = stm.executeQuery();

            while (rs.next())
                laboratorio = rs.getString(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getProgettiLab(String cf,String progetto){
        try {
            String nomelab = "";
            try {
                String query = "SELECT nomelab FROM utilizza WHERE cf = ?";
                PreparedStatement stm = c.prepareStatement(query);
                stm.setString(1, cf);
                ResultSet rs = stm.executeQuery();

                while (rs.next())
                    nomelab = rs.getString(1);

            } catch (SQLException e) {
                nomelab = "";
                e.printStackTrace();
            }
            String query = "SELECT cup FROM lavora WHERE lab1 = ? OR lab2 = ? OR lab3 = ?";
            PreparedStatement stm = c.prepareStatement(query);
            stm.setString(1, nomelab);
            stm.setString(2, nomelab);
            stm.setString(3, nomelab);

            ResultSet rs = stm.executeQuery();

            while (rs.next())
                progetto = rs.getString(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}