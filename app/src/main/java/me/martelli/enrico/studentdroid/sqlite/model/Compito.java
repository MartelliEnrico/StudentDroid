package me.martelli.enrico.studentdroid.sqlite.model;

import me.martelli.enrico.studentdroid.sqlite.helper.DbModel;

/**
 * Created by Enrico on 31/01/14.
 */
public class Compito extends DbModel {

    private int id;
    private int giorno;
    private String descrizione;
    private int idLezione;

    public Compito() {}

    public Compito(int giorno, String descrizione, int idLezione) {
        this.giorno = giorno;
        this.descrizione = descrizione;
        this.idLezione = idLezione;
    }

    public Compito(int id, int giorno, String descrizione, int idLezione) {
        this.id = id;
        this.giorno = giorno;
        this.descrizione = descrizione;
        this.idLezione = idLezione;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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

    public String getDescrizione() {
        return descrizione;
    }

    public int getIdLezione() {
        return idLezione;
    }
}
