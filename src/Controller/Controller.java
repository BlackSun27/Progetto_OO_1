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


}
