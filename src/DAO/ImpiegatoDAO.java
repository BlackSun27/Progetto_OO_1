package DAO;

import java.sql.Date;
import java.sql.SQLException;

public interface ImpiegatoDAO {

    public void inserisciImpiegato(String cf, String nome, String cognome, Date dataNascita, Date dataAssunzione, String codiceCon,
                                   boolean merito, float salario, String categoria, int eta) throws SQLException;

    public void rimuoviImpiegato(String cf) throws SQLException;

    public void promuoviImpiegato(String cf, String codiceCon, boolean merito) throws SQLException;


}
