package DAO;

import java.sql.SQLException;

public interface LaboratorioDAO {

    public void inserisciLaboratorio(String nomeLab, String respSci, String topic, int n_Afferenti)
            throws SQLException;

    public void rimuoviLaboratorio(String nomeLab) throws SQLException;

    public void aggiungiAfferente(String nomeLab, String cf) throws SQLException;



}