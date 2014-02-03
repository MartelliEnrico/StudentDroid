package me.martelli.enrico.studentdroid.sqlite.model;

import me.martelli.enrico.studentdroid.sqlite.helper.DbModel;

/**
 * Created by Enrico on 31/01/14.
 */
public class Verifica extends DbModel {

    private int id;
    private int giorno;
    private String tipologia;
    private int idLezione;

    public Verifica() {}

    public Verifica(int giorno, String tipologia, int idLezione) {
        this.giorno = giorno;
        this.tipologia = tipologia;
        this.idLezione = idLezione;
    }

    public Verifica(int id, int giorno, String tipologia, int idLezione) {
        this.id = id;
        this.giorno = giorno;
        this.tipologia = tipologia;
        this.idLezione = idLezione;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public void setIdLezione(int idLezione) {
        this.idLezione = idLezione;
    }

    public int getId() {
        return id;
    }

    public int getGiorno() {
        return giorno;
    }

    public String getTipologia() {
        return tipologia;
    }

    public int getIdLezione() {
        return idLezione;
    }
}
