package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProgettoDAO {

    public void inserisciProgetto(String cup, String refSci, String resp, String nome, float budget)
            throws SQLException;

    public void rimuoviProgetto(String cup) throws SQLException;

    public void aggiungiLaboratorio(String cup, String nomeLab) throws SQLException;

    void getProgResp(String cup, ArrayList<String> Cf_Resp);

    void getProgRef(String cup, ArrayList<String> Cf_Ref);

    void getLabProg(String cup, ArrayList<String> l_Laboratori);
}