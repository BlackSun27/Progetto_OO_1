package Model;

import java.sql.Date;

public class Promozione {
    private String cf;
    private Date dataPassaggio;
    private String codiceCon;
    private String vecchiaCategoria;
    private String nuovaCategoria;

    public Promozione(String cf, Date dataPassaggio, String codiceCon, String vecchiaCategoria,
                      String nuovaCategoria){
        this.cf = cf;
        this.dataPassaggio = dataPassaggio;
        this.codiceCon = codiceCon;
        this.vecchiaCategoria = vecchiaCategoria;
        this.nuovaCategoria = nuovaCategoria;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getCf() {
        return cf;
    }

    public void setDataPassaggio(Date dataPassaggio) {
        this.dataPassaggio = dataPassaggio;
    }

    public Date getDataPassaggio() {
        return dataPassaggio;
    }

    public void setCodiceCon(String codiceCon) {
        this.codiceCon = codiceCon;
    }

    public String getCodiceCon() {
        return codiceCon;
    }

    public void setVecchiaCategoria(String vecchiaCategoria) {
        this.vecchiaCategoria = vecchiaCategoria;
    }

    public String getVecchiaCategoria() {
        return vecchiaCategoria;
    }

    public void setNuovaCategoria(String nuovaCategoria) {
        this.nuovaCategoria = nuovaCategoria;
    }

    public String getNuovaCategoria() {
        return nuovaCategoria;
    }
}
