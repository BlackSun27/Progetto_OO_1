package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LaboratorioDAO {

    public void inserisciLaboratorio(String nomeLab, String respSci, String topic, int n_Afferenti)
            throws SQLException;

    public void rimuoviLaboratorio(String nomeLab) throws SQLException;

    public void aggiungiAfferente(String nomeLab, String cf) throws SQLException;


    void afferenzeLab(String nomelab, ArrayList<String> l_CF);

    void getRespSci(String nomelab, String Cf_Resp);

    void getProgLavora(String nomelab, String CUP);
}