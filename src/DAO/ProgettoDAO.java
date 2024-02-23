package DAO;

import java.sql.SQLException;

public interface ProgettoDAO {

    public void inserisciProgetto(String cup, String refSci, String resp, String nome, float budget)
            throws SQLException;

    public void rimuoviProgetto(String cup) throws SQLException;

    public void aggiungiLaboratorio(String cup, String nomeLab) throws SQLException;
}