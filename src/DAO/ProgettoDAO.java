package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProgettoDAO {

    public void inserisciProgetto(String cup, String refSci, String resp, String nome, float budget)
            throws SQLException;

    public void rimuoviProgetto(String cup) throws SQLException;

    public void aggiungiLaboratorio(String cup, String nomeLab) throws SQLException;

    public void getProgResp(String cup, String Cf_Resp);

    public void getProgRef(String cup, String Cf_Ref);

    public void getLabProg(String cup, String Lab1, String Lab2, String Lab3, ArrayList<String> l_Lab);
}