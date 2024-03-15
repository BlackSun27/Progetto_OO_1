package DAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ImpiegatoDAO {

    void inserisciImpiegato(String cf, String nome, String cognome, Date dataNascita, Date dataAssunzione, String codiceCon,
                                   boolean merito, float salario, String categoria, int eta) throws SQLException;

    void rimuoviImpiegato(String cf) throws SQLException;

    void promuoviImpiegato(String cf, boolean merito) throws SQLException;

    void getAfferenzeImp(String cf, ArrayList<String> laboratorio);

    void getProgettiLab(String cf, ArrayList<String> progetto);

    void getPromozioniImp(String cf, ArrayList<String> l_Promozioni, ArrayList<Date> date);
}
