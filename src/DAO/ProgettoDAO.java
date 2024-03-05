package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProgettoDAO {

    public void inserisciProgetto(String cup, String refSci, String resp, String nome, float budget)
            throws SQLException;

    public void rimuoviProgetto(String cup) throws SQLException;

    public void aggiungiLaboratorio(String cup, String nomeLab) throws SQLException;

    public void getProgResp(String cup, ArrayList<String> Cf_Resp);

    public void getProgRef(String cup, ArrayList<String> Cf_Ref);

    public void getLabProg(String cup, ArrayList<String> l_Lab1, ArrayList<String> l_Lab2,
                    ArrayList<String> l_Lab3);
}