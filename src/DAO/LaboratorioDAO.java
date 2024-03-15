package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LaboratorioDAO {

    void inserisciLaboratorio(String nomeLab, String respSci, String topic)
            throws SQLException;

    void rimuoviLaboratorio(String nomeLab) throws SQLException;

    void aggiungiAfferente(String nomeLab, String cf) throws SQLException;

    void afferenzeLab(String nomelab, ArrayList<String> l_CF);

    void getRespSci(String nomelab, ArrayList<String> resp);

    void getProgLavora(String nomelab, ArrayList<String> CUP);
}