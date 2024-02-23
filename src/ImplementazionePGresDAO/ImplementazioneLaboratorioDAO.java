package ImplementazionePGresDAO;

import ConnectDB.DbConn;
import DAO.LaboratorioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImplementazioneLaboratorioDAO implements LaboratorioDAO {
    private DbConn db;
    private Connection c;
    public ImplementazioneLaboratorioDAO() {
        db = new DbConn();
        try {
            c = db.conn_db("postgres", "postgres", "Blacks_27");
        } catch (SQLException | ClassNotFoundException e) {
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
}