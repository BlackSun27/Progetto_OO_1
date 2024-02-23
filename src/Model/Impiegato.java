package Model;

import java.sql.Date;
import java.util.ArrayList;

public class Impiegato {
    private String cf;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private Date dataAssunzione;
    private String codiceCon;
    private boolean merito;
    private float salario;
    private String categoria;
    private int eta;

    private ArrayList<Promozione> promozioni = new ArrayList<>();

    public Impiegato(String cf, String nome, String cognome, Date dataNascita, Date dataAssunzione,
                     String codiceCon, boolean merito, float salario, String categoria, int eta){
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.dataAssunzione = dataAssunzione;
        this.codiceCon = codiceCon;
        this.merito = merito;
        this.salario = salario;
        this.categoria = categoria;
        this.eta = eta;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getCf() {
        return cf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setDataAssunzione(Date dataAssunzione) {
        this.dataAssunzione = dataAssunzione;
    }

    public Date getDataAssunzione() {
        return dataAssunzione;
    }

    public void setDataNascita(Date dataNascita){this.dataNascita = dataNascita;}
    public Date getDataNascita(){return dataNascita;}

    public void setCodiceCon(String codiceCon) {
        this.codiceCon = codiceCon;
    }

    public String getCodiceCon() {
        return codiceCon;
    }

    public void setMerito(boolean merito) {
        this.merito = merito;
    }

    public boolean hasMerito() {
        return merito;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getSalario() {
        return salario;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public int getEta() {
        return eta;
    }

    public void setPromozioni(ArrayList<Promozione> promozioni) {
        this.promozioni = promozioni;
    }

    public ArrayList<Promozione> getPromozioni() {
        return promozioni;
    }

}