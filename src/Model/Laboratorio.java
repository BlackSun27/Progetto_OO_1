package Model;

import java.util.ArrayList;

public class Laboratorio {
    private String nome;
    private String resp_sci;
    private String topic;
    private int n_afferenti = 0;

    private ArrayList<String> listAfferenti = new ArrayList<>();
    private ArrayList<String> listaCup = new ArrayList<>();

    public Laboratorio(String nome, String resp_sci, String topic, int n_afferenti){
        this.nome = nome;
        this.resp_sci = resp_sci;
        this.topic = topic;
        this.n_afferenti = n_afferenti;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getResp_sci() {
        return resp_sci;
    }

    public void setResp_sci(String resp_sci) {
        this.resp_sci = resp_sci;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setN_afferenti(int n_afferenti) {
        this.n_afferenti = n_afferenti;
    }

    public int getN_afferenti() {
        return n_afferenti;
    }

    public void aggiungiAff(String cf){
        listAfferenti.add(cf);
        this.n_afferenti += 1;
    }

    public void rimuoviAff(String cf){
        listAfferenti.remove(cf);
        this.n_afferenti -= 1;
    }

    public void setListAfferenti(ArrayList<String> listAfferenti) {
        this.listAfferenti = listAfferenti;
    }

    public ArrayList<String> getListAfferenti() {
        return listAfferenti;
    }

    public void setListaCup(ArrayList<String> listaCup){this.listaCup = listaCup;}

    public ArrayList<String> getListaCup() {
        return listaCup;
    }
}