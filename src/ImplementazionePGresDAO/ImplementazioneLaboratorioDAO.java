package ImplementazionePGresDAO;

import ConnectDB.DbConn;
import DAO.LaboratorioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImplementazioneLaboratorioDAO implements LaboratorioDAO {
    private DbConn db;
    private Connection c;
    public ImplementazioneLaboratorioDAO() {
        db = new DbConn();
        try {
            c = db.conn_db("postgres", "postgres", "Blacks_27");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inserisciLaboratorio(String nome, String respSci, String topic, int n_Afferenti)
            throws SQLException {
        String query = "CALL inseriscilaboratorio(?,?,?,?); ";
        PreparedStatement stm = c.prepareStatement(query);
        stm.setString(1, nome);
        stm.setString(2, respSci);
        stm.setString(3, topic);
        stm.setInt(4, n_Afferenti);

        stm.executeUpdate();
    }

    @Override
    public void rimuoviLaboratorio(String nome) throws SQLException{
        String query = "CALL rimuovi_laboratorio(?);";
        PreparedStatement stm = c.prepareStatement(query);
        stm.setString(1,nome);

        stm.executeUpdate();
    }

    @Override
    public void aggiungiAfferente(String nomeLab, String cf) throws SQLException{
        String queryUti = "CALL inserisciutilizza(?,?); ";
        PreparedStatement stm = c.prepareStatement(queryUti);
        stm.setString(1, cf);
        stm.setString(2, nomeLab);
        stm.executeUpdate();
    }

    public void afferenzeLab(String nomelab, ArrayList<String> l_CF){
        try{
            String query = "SELECT cf FROM utilizza WHERE nomelab = ?";
            PreparedStatement stm = c.prepareStatement(query);
            stm.setString(1,nomelab);

            ResultSet rs = stm.executeQuery();

            while(rs.next())
                l_CF.add(rs.getString(1));

        }catch (SQLException e){
            System.out.println("Errore a ottenere informazioni sulle afferenze!");
            e.printStackTrace();
        }
    }
}