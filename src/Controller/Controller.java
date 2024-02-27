package Controller;

import DAO.*;
import ImplementazionePGresDAO.*;
import Model.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller {
    //l_ prima di ogni variabile fa riferimento al fatto che sia una lista,
    // esempio: l_Impiegati = lista impiegati
    private final List<Impiegato> l_Impiegati = new ArrayList<>();
    private final List<Promozione> l_Promozioni = new ArrayList<>();
    private final List<Laboratorio> l_Laboratori = new ArrayList<>();
    private final List<Progetto> l_Progetti = new ArrayList<>();

    public Controller(){
        getImpiegatiDB();
        getLaboratoriDB();
        getPromozioniDB();
        getProgettiDB();
    }

    public void getImpiegatiDB(){
        GestionaleDAO gestionaleDAO = new ImplementazioneGestionaleDAO();

        ArrayList<String> l_CF = new ArrayList<>();
        ArrayList<String> l_Nomi = new ArrayList<>();
        ArrayList<String> l_Cognomi = new ArrayList<>();
        ArrayList<Date> l_DateNascita = new ArrayList<>();
        ArrayList<Date> l_DateAssunzioni = new ArrayList<>();
        ArrayList<String> l_Contratti = new ArrayList<>();
        ArrayList<Boolean> l_Merito = new ArrayList<>();
        ArrayList<Float> l_Salari = new ArrayList<>();
        ArrayList<String> l_Categorie = new ArrayList<>();
        ArrayList<Integer> l_Eta = new ArrayList<>();

        gestionaleDAO.getImpiegati(l_CF, l_Nomi, l_Cognomi, l_DateNascita, l_DateAssunzioni,
                l_Contratti, l_Merito, l_Salari, l_Categorie, l_Eta);

        int i=0;
        for(; i<l_CF.size(); i++){
            l_Impiegati.add(new Impiegato(l_CF.get(i), l_Nomi.get(i), l_Cognomi.get(i),
                    l_DateNascita.get(i), l_DateAssunzioni.get(i), l_Contratti.get(i), l_Merito.get(i),
                    l_Salari.get(i), l_Categorie.get(i), l_Eta.get(i)));
        }
    }

    public void getLaboratoriDB(){
        GestionaleDAO gestionaleDAO = new ImplementazioneGestionaleDAO();

        ArrayList<String> l_Nomi = new ArrayList<>();
        ArrayList<String> l_RespSci = new ArrayList<>();
        ArrayList<String> l_Topic = new ArrayList<>();
        ArrayList<Integer> l_NumeroAfferenti = new ArrayList<>();

        gestionaleDAO.getLaboratori(l_Nomi, l_RespSci, l_Topic, l_NumeroAfferenti);

        int i=0;
        for(; i<l_Nomi.size(); i++){
            l_Laboratori.add(new Laboratorio(l_Nomi.get(i), l_RespSci.get(i), l_Topic.get(i),
                    l_NumeroAfferenti.get(i)));
        }

    }

    public void getPromozioniDB(){
        GestionaleDAO gestionaleDAO = new ImplementazioneGestionaleDAO();

        ArrayList<String> l_CF = new ArrayList<>();
        ArrayList<Date> l_DatePassaggio = new ArrayList<>();
        ArrayList<String> l_Contratti = new ArrayList<>();
        ArrayList<String> l_VecchieCategorie = new ArrayList<>();
        ArrayList<String> l_NuoveCategorie = new ArrayList<>();

        gestionaleDAO.getPromozioni(l_CF, l_DatePassaggio, l_Contratti, l_VecchieCategorie, l_NuoveCategorie);

        int i=0;
        for(; i<l_CF.size(); i++)
            l_Promozioni.add(new Promozione(l_CF.get(i), l_DatePassaggio.get(i), l_Contratti.get(i),
                    l_VecchieCategorie.get(i), l_NuoveCategorie.get(i)));


    }

    public void getProgettiDB(){
        GestionaleDAO gestionaleDAO = new ImplementazioneGestionaleDAO();

        ArrayList<String> l_CUP = new ArrayList<>();
        ArrayList<String> l_RefSci = new ArrayList<>();
        ArrayList<String> l_Resp = new ArrayList<>();
        ArrayList<String> l_Nomi = new ArrayList<>();
        ArrayList<Float> l_Budget = new ArrayList<>();

        gestionaleDAO.getProgetti(l_CUP, l_RefSci, l_Resp, l_Nomi, l_Budget);

        int i =0;

        for(; i<l_CUP.size(); i++)
            l_Progetti.add(new Progetto(l_CUP.get(i), l_RefSci.get(i), l_Resp.get(i),
                    l_Nomi.get(i), l_Budget.get(i)));
    }

    //METODI PER SVILUPPARE LA GUI DI IMPIEGATO
    public ArrayList<String> getListaCF(){
        ArrayList<String> l_CF = new ArrayList<>();

        for(Impiegato i : l_Impiegati)
            l_CF.add(i.getCf());

        return l_CF;
    }

    public ArrayList<String> getListaNomiImp(){
        ArrayList<String> l_Nomi = new ArrayList<>();

        for(Impiegato i : l_Impiegati)
            l_Nomi.add(i.getNome());

        return l_Nomi;
    }

    public ArrayList<String> getListaCognomi(){
        ArrayList<String> l_Cognomi = new ArrayList<>();

        for(Impiegato i : l_Impiegati)
            l_Cognomi.add(i.getCognome());

        return l_Cognomi;
    }

    public Impiegato getListaPromozioni(String cf){
        ArrayList<Promozione> l_PromozioniImp = new ArrayList<>();

        for(Promozione p : l_PromozioniImp){
            if(p.getCf().equals(cf))
                l_PromozioniImp.add(p);
        }

        for(Impiegato i : l_Impiegati){
            if(Objects.equals(cf, i.getCf())){
                i.setPromozioni(l_PromozioniImp);
                return i;
            }
        }
        return null;
    }

    public ArrayList<String> getAfferenzeImp(String cf){
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        Impiegato impiegato = null;

        for(Impiegato i : l_Impiegati){
            if(i.getCf().equals(cf))
                impiegato = i;
            break;
        }

        ArrayList<String> l_LaboratoriAff = new ArrayList<>();
        impiegatoDAO.getAfferenzeImp(cf, l_LaboratoriAff);

        if(impiegato != null) {
            impiegato.setLaboratori(l_LaboratoriAff);
            return l_LaboratoriAff;
        }

        return null;
    }

    public ArrayList<String> getProgettiImp(String cf){
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        Impiegato impiegato = null;

        for(Impiegato i : l_Impiegati){
            if(i.getCf().equals(cf))
                impiegato = i;
            break;
        }

        ArrayList<String> l_ProgettiAff = new ArrayList<>();
        impiegatoDAO.getAfferenzeImp(cf, l_ProgettiAff);

        if(impiegato != null) {
            impiegato.setProgetti(l_ProgettiAff);
            return l_ProgettiAff;
        }

        return null;
    }

    public void aggiungiImp(String cf, String nome, String cognome, Date dataNascita, Date dataAssunzione,
                            String codiceCon, boolean merito, float salario, String categoria, int eta) throws SQLException {

        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        if(merito) {
            categoria = "Dirigente";
            impiegatoDAO.inserisciImpiegato(cf, nome, cognome, dataNascita,
                    dataAssunzione, codiceCon, merito, salario, categoria, eta);
        }else
            impiegatoDAO.inserisciImpiegato(cf, nome, cognome, dataNascita,
                    dataAssunzione, codiceCon, merito, salario, categoria, eta);

        l_Impiegati.clear();
        getImpiegatiDB();
    }

    public void rimuoviImp(String cf) throws SQLException {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        impiegatoDAO.rimuoviImpiegato(cf);

        l_Impiegati.removeIf(i -> i.getCf().equals(cf));
    }

    public void promuoviImp(String cf, String codicecon, boolean merito) throws SQLException {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        impiegatoDAO.promuoviImpiegato(cf,codicecon,merito);

        l_Impiegati.clear();
        getImpiegatiDB();
    }

    //METODI PER SVILUPPARE LA GUI DI LABORATORIO
    public ArrayList<String> getListaNomiLab(){
        ArrayList<String> l_Nomi = new ArrayList<>();

        for(Laboratorio l : l_Laboratori)
            l_Nomi.add(l.getNome());

        return l_Nomi;
    }

    public ArrayList<String> getListaRespSci(){
        ArrayList<String> l_RespSci = new ArrayList<>();

        for(Laboratorio l : l_Laboratori)
            l_RespSci.add(l.getResp_sci());

        return l_RespSci;
    }

    public ArrayList<String> getListaTopicLab(){
        ArrayList<String> l_Topic = new ArrayList<>();

        for(Laboratorio l : l_Laboratori)
            l_Topic.add(l.getTopic());

        return l_Topic;
    }

    public ArrayList<Integer> getListaN_Afferenti(){
        ArrayList<Integer> l_Afferenti = new ArrayList<>();

        for(Laboratorio l : l_Laboratori)
            l_Afferenti.add(l.getN_afferenti());

        return l_Afferenti;
    }

    public ArrayList<String> getRespSciLab(String nome) {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        Laboratorio laboratorio = null;

        for (Laboratorio l : l_Laboratori) {
            if (l.getNome().equals(nome))
                laboratorio = l;
            break;
        }

        ArrayList<String> l_ImpiegatiAff = new ArrayList<>();
        laboratorioDAO.getRespSci(nome, l_ImpiegatiAff);

        if (laboratorio != null) {
            laboratorio.setListAfferenti(l_ImpiegatiAff);
            return l_ImpiegatiAff;
        }

        return null;
    }

    public ArrayList<String> getAfferenzeLab(String nome) {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        Laboratorio laboratorio = null;

        for (Laboratorio l : l_Laboratori) {
            if (l.getNome().equals(nome))
                laboratorio = l;
            break;
        }

        ArrayList<String> l_Afferenti = new ArrayList<>();
        laboratorioDAO.afferenzeLab(nome, l_Afferenti);

        if (laboratorio != null) {
            laboratorio.setListAfferenti(l_Afferenti);
            return l_Afferenti;
        }

        return null;
    }

    public ArrayList<String> getCUPfromLab(String nome) {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        Laboratorio laboratorio = null;

        for (Laboratorio l : l_Laboratori) {
            if (l.getNome().equals(nome))
                laboratorio = l;
            break;
        }

        ArrayList<String> l_ProgettiCup = new ArrayList<>();
        laboratorioDAO.getProgLavora(nome, l_ProgettiCup);

        if (laboratorio != null) {
            laboratorio.setListaCup(l_ProgettiCup);
            return l_ProgettiCup;
        }

        return null;
    }

    public void aggiungiLab(String nomeLab, String respSci, String topic, int n_Afferenti) throws SQLException {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        laboratorioDAO.inserisciLaboratorio(nomeLab, respSci, topic, n_Afferenti);

        l_Laboratori.clear();
        getLaboratoriDB();
    }

    public void rimuoviLab(String nome) throws SQLException {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        laboratorioDAO.rimuoviLaboratorio(nome);

        l_Laboratori.removeIf(l -> l.getNome().equals(nome));
    }

    public void aggiungiAfferenteLab(String nome, String cf) throws SQLException {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        laboratorioDAO.aggiungiAfferente(nome, cf);

        l_Laboratori.clear();
        getLaboratoriDB();
    }

    //METODI PER SVILUPPARE LA GUI DI PROGETTO
    public ArrayList<String> getListaCUP(){
        ArrayList<String> l_CUP = new ArrayList<>();

        for(Progetto p : l_Progetti)
            l_CUP.add(p.getCup());

        return l_CUP;
    }

    public ArrayList<String> getListaRefSci(){
        ArrayList<String> l_RefSci = new ArrayList<>();

        for(Progetto p : l_Progetti)
            l_RefSci.add(p.getRef_sci());

        return l_RefSci;
    }

    public ArrayList<String> getListaResp(){
        ArrayList<String> l_Resp = new ArrayList<>();

        for(Progetto p : l_Progetti)
            l_Resp.add(p.getResp());

        return l_Resp;
    }

    public ArrayList<String> getListaNomiProg(){
        ArrayList<String> l_Nomi = new ArrayList<>();

        for(Progetto p : l_Progetti)
            l_Nomi.add(p.getNome());

        return l_Nomi;
    }

    public ArrayList<String> getLabFromCUP(String cup) {
        ProgettoDAO progettoDAO = new ImplementazioneProgettoDAO();

        Progetto progetto = null;

        for (Progetto p : l_Progetti) {
            if (p.getCup().equals(cup))
                progetto = p;
            break;
        }

        ArrayList<String> l_Lab1 = new ArrayList<>();
        ArrayList<String> l_Lab2 = new ArrayList<>();
        ArrayList<String> l_Lab3 = new ArrayList<>();
        progettoDAO.getLabProg(cup, l_Lab1, l_Lab2, l_Lab3);

        ArrayList<String> listaLab = new ArrayList<>();
        listaLab.addAll(l_Lab1);
        listaLab.addAll(l_Lab2);
        listaLab.addAll(l_Lab3);

        if (progetto != null) {
            progetto.setListaLab(listaLab);
            return listaLab;
        }

        return null;
    }

    public ArrayList<String> getRespFromCUP(String cup) {
        ProgettoDAO progettoDAO = new ImplementazioneProgettoDAO();

        Progetto progetto = null;

        for (Progetto p : l_Progetti) {
            if (p.getCup().equals(cup))
                progetto = p;
            break;
        }

        ArrayList<String> l_Resp = new ArrayList<>();
        progettoDAO.getProgResp(cup, l_Resp);

        if (progetto != null) {
            progetto.setListaResp(l_Resp);
            return l_Resp;
        }

        return null;
    }

    public ArrayList<String> getRefFromCUP(String cup) {
        ProgettoDAO progettoDAO = new ImplementazioneProgettoDAO();

        Progetto progetto = null;

        for (Progetto p : l_Progetti) {
            if (p.getCup().equals(cup))
                progetto = p;
            break;
        }

        ArrayList<String> l_RefSci = new ArrayList<>();
        progettoDAO.getLabProg(cup, l_RefSci);

        if (progetto != null) {
            progetto.setListaRefSci(l_RefSci);
            return l_RefSci;
        }

        return null;
    }

    public void aggiungiProgetto(String cup, String refSci, String resp, String nome, float budget)
        throws SQLException{
        ProgettoDAO progettoDAO = new ImplementazioneProgettoDAO();

        progettoDAO.inserisciProgetto(cup, refSci, resp, nome, budget);

        l_Progetti.clear();
        getProgettiDB();
    }

    public void rimuoviProgetto(String cup) throws SQLException{
        ProgettoDAO progettoDAO = new ImplementazioneProgettoDAO();

        progettoDAO.rimuoviProgetto(cup);

        l_Progetti.removeIf(p -> p.getCup().equals(cup));
    }

    public void aggiungiLaboratorio(String cup, String nomelab) throws SQLException{
        ProgettoDAO progettoDAO = new ImplementazioneProgettoDAO();

        progettoDAO.aggiungiLaboratorio(cup, nomelab);

        l_Progetti.clear();
        getProgettiDB();
    }
}
