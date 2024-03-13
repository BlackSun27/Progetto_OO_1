package DAO;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProgettoDAO {

    public void inserisciProgetto(String cup, String refSci, String resp, String nome, float budget)
            throws SQLException;

    public void rimuoviProgetto(String cup) throws SQLException;

    public void aggiungiLaboratorio(String cup, String nomeLab) throws SQLException;

    public ArrayList<String> getProgImp(String cup);

    public void getLabProg(String cup, ArrayList<String> l_Lab);
}