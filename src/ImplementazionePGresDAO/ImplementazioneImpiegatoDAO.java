package ImplementazionePGresDAO;

import ConnectDB.DbConn;
import DAO.ImpiegatoDAO;

import java.sql.*;

public class ImplementazioneImpiegatoDAO implements ImpiegatoDAO {
    private DbConn db;
    private Connection c;
    public ImplementazioneImpiegatoDAO(){
        db = new DbConn();
        try{
            c = db.conn_db("postgres", "postgres", "Blacks_27");
        } catch (SQLException | ClassNotFoundException e){
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
        stm.setString(4, String.valueOf(dataNascita));
        stm.setBoolean(5, merito);
        stm.setString(6, codiceCon);
        stm.setString(7, String.valueOf(dataAssunzione));
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
    public void promuoviImpiegato(String cf, String codiceCon, boolean merito) throws SQLException{
        if(merito) {
            String upMerito = "UPDATE Impiegato SET merito = ? WHERE cf = ? AND codicecon = ?";
            try (PreparedStatement stm = c.prepareStatement(upMerito)) {
                stm.setString(1, String.valueOf(merito));
                stm.setString(2, cf);
                stm.setString(3, codiceCon);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String query = "CALL inseriscipromozione(?)";
        CallableStatement stm = c.prepareCall(query);
        stm.setString(1, cf);
    }
}