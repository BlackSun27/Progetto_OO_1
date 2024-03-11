package Controller;

import DAO.*;
import ImplementazionePGresDAO.*;
import Model.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

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

    public List<Impiegato> getImpiegatiDB(){
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
            try {
                l_Impiegati.add(new Impiegato(l_CF.get(i), l_Nomi.get(i), l_Cognomi.get(i),
                        l_DateNascita.get(i), l_DateAssunzioni.get(i), l_Contratti.get(i), l_Merito.get(i),
                        l_Salari.get(i), l_Categorie.get(i), l_Eta.get(i)));
            }catch (NullPointerException e){
                System.err.println("Eccezione del puntatore! ");
                break;
            }catch (IndexOutOfBoundsException e){
                System.err.println("Eccezione dell'indice! ");
                break;
            }
        }
        return l_Impiegati;
    }

    public List<Laboratorio> getLaboratoriDB(){
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
        return l_Laboratori;
    }

    public List<Promozione> getPromozioniDB(){
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

        return l_Promozioni;
    }

    public List<Progetto> getProgettiDB(){
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

        return l_Progetti;
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

    public Map<List<String>, List<Date>> getListaPromozioni(String cf){
        ArrayList<String> promozioni = new ArrayList<>();
        ArrayList<Date> date = new ArrayList<>();

        ImpiegatoDAO impDao = new ImplementazioneImpiegatoDAO();
        impDao.getPromozioniImp(cf, promozioni, date);

        Map<List<String>, List<Date>> info_profilo = new HashMap<>();
        info_profilo.put(promozioni, date);

        return info_profilo;
    }

    public ArrayList<String> getAfferenzeImp(String cf){
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        Impiegato impiegato = null;

        for(int i=0; i<l_Impiegati.size(); i++){
            if(l_Impiegati.get(i).getCf().equals(cf)) {
                impiegato = l_Impiegati.get(i);
                break;
            }
        }

        ArrayList<String> nomelab = new ArrayList<>();
        impiegatoDAO.getAfferenzeImp(cf, nomelab);

        if(impiegato != null && !nomelab.isEmpty()) {
            impiegato.setLaboratorio(nomelab.get(0));
            return nomelab;
        }

        return null;
    }

    public ArrayList<String> getProgettiImp(String cf){
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        Impiegato impiegato = null;

        for(int i=0; i<l_Impiegati.size(); i++){
            if(l_Impiegati.get(i).getCf().equals(cf)){
                impiegato = l_Impiegati.get(i);
                break;
            }
        }

        ArrayList<String> progetto = new ArrayList<>();
        impiegatoDAO.getProgettiLab(cf, progetto);

        if(impiegato != null) {
            impiegato.setProgetto(progetto.get(0));
            return progetto;
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

    public void promuoviImp(String cf, boolean merito) throws SQLException {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        impiegatoDAO.promuoviImpiegato(cf,merito);

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

        ArrayList<String> cf_Resp = new ArrayList<>();
        laboratorioDAO.getRespSci(nome, cf_Resp);

        if (laboratorio != null) {
            laboratorio.setResp_sci(cf_Resp.get(0));
            return cf_Resp;
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

    public String getCUPfromLab(String nome) {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        Laboratorio laboratorio = null;

        for (Laboratorio l : l_Laboratori) {
            if (l.getNome().equals(nome))
                laboratorio = l;
            break;
        }

        ArrayList<String> ProgettoCup = new ArrayList<>();
        laboratorioDAO.getProgLavora(nome, ProgettoCup);

        if (laboratorio != null) {
            laboratorio.setCup(ProgettoCup.get(0));
            return ProgettoCup.get(0);
        }

        return null;
    }

    public void aggiungiLab(String nomeLab, String respSci, String topic) throws SQLException {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        laboratorioDAO.inserisciLaboratorio(nomeLab, respSci, topic);

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

        String lab1 = new String();
        String lab2 = new String();
        String lab3 = new String();
        ArrayList<String> l_Lab = new ArrayList<>();
        progettoDAO.getLabProg(cup, lab1, lab2, lab3, l_Lab);

        if (progetto != null) {
            progetto.setListaLab(l_Lab);
            return l_Lab;
        }

        return null;
    }

    public String getRespFromCUP(String cup) {
        ProgettoDAO progettoDAO = new ImplementazioneProgettoDAO();

        Progetto progetto = null;

        for (Progetto p : l_Progetti) {
            if (p.getCup().equals(cup))
                progetto = p;
            break;
        }

        String Resp = new String();
        progettoDAO.getProgResp(cup, Resp);

        if (progetto != null) {
            progetto.setResp(Resp);
            return Resp;
        }

        return null;
    }

    public String getRefFromCUP(String cup) {
        ProgettoDAO progettoDAO = new ImplementazioneProgettoDAO();

        Progetto progetto = null;

        for (Progetto p : l_Progetti) {
            if (p.getCup().equals(cup))
                progetto = p;
            break;
        }

        String refSci = new String();
        progettoDAO.getProgRef(cup, refSci);

        if (progetto != null) {
            progetto.setRef_sci(refSci);
            return refSci;
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
