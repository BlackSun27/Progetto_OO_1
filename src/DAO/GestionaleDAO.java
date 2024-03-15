package DAO;

import java.sql.Date;
import java.util.ArrayList;
//ogni l_ fa riferimento al fatto che è una variabile lista -> l_cf = lista codici fiscali
public interface GestionaleDAO {

    void getImpiegati(ArrayList<String> l_CF, ArrayList<String> l_Nomi, ArrayList<String> l_Cognomi,
                             ArrayList<Date> l_DateNascita, ArrayList<Date> l_DateAssunzioni,
                             ArrayList<String> l_Contratti, ArrayList<Boolean> l_Merito,
                             ArrayList<Float> l_Salari, ArrayList<String> l_Categorie, ArrayList<Integer> l_Eta);

    void getPromozioni(ArrayList<String> l_CF, ArrayList<Date> l_DatePassaggio,
                              ArrayList<String> l_Contratti, ArrayList<String> l_VecchieCategorie,
                              ArrayList<String> l_NuoveCategorie);

    void getLaboratori(ArrayList<String> l_Nomi, ArrayList<String> l_RespSci,
                              ArrayList<String> l_Topic, ArrayList<Integer> l_NumeroAfferenti);

    void getProgetti(ArrayList<String> l_CUP, ArrayList<String> l_RefSci, ArrayList<String> l_Resp,
                            ArrayList<String> l_Nomi, ArrayList<Float> l_Budget);

    void getAfferenti(String nomeLab, ArrayList<String> l_CF);
}
