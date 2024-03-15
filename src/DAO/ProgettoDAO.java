package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProgettoDAO {

    void inserisciProgetto(String cup, String refSci, String resp, String nome, float budget)
            throws SQLException;

    void rimuoviProgetto(String cup) throws SQLException;

    void aggiungiLaboratorio(String cup, String nomeLab) throws SQLException;

    ArrayList<String> getProgImp(String cup);

    void getLabProg(String cup, ArrayList<String> l_Lab);
}