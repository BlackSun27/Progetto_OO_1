package Model;

import java.util.ArrayList;

public class Progetto {
    private String cup;
    private String ref_sci;
    private String resp;
    private String nome;
    private float budget;

    private ArrayList<String> listaLab = new ArrayList<>();
    private ArrayList<String> listaInfoImp = new ArrayList<>();

    public Progetto(String cup, String ref_sci, String resp, String nome, float budget){
        this.cup = cup;
        this.ref_sci = ref_sci;
        this.resp = resp;
        this.nome = nome;
        this.budget = budget;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public String getCup() {
        return cup;
    }

    public void setRef_sci(String ref_sci) {
        this.ref_sci = ref_sci;
    }

    public String getRef_sci() {
        return ref_sci;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getResp() {
        return resp;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public float getBudget() {
        return budget;
    }

    public void aggiungiLab(String lab) {
        listaLab.add(lab);
    }

    public void rimuoviLab(Laboratorio lab) {
        listaLab.remove(lab);
    }

    public void setListaLab(ArrayList<String> listaLab) {
        this.listaLab = listaLab;
    }

    public ArrayList<String> getListaLab() {
        return listaLab;
    }

    public void setListaInfoImp(ArrayList<String> listaInfoImp) {
        this.listaInfoImp = listaInfoImp;
    }

    public ArrayList<String> getListaInfoImp() {
        return listaInfoImp;
    }
}