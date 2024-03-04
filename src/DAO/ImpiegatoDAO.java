package DAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ImpiegatoDAO {

    public void inserisciImpiegato(String cf, String nome, String cognome, Date dataNascita, Date dataAssunzione, String codiceCon,
                                   boolean merito, float salario, String categoria, int eta) throws SQLException;

    public void rimuoviImpiegato(String cf) throws SQLException;

    public void promuoviImpiegato(String cf, boolean merito) throws SQLException;

    public void getAfferenzeImp(String cf, ArrayList<String> l_Laboratori);

    public void getProgettiLab(String cf, ArrayList<String> l_Progetti);
}
