package me.martelli.enrico.studentdroid.sqlite.model;

import java.util.Date;
import java.util.List;

import me.martelli.enrico.studentdroid.MyApplication;
import me.martelli.enrico.studentdroid.sqlite.helper.DatabaseOpenHelper;
import me.martelli.enrico.studentdroid.sqlite.helper.DbModel;

/**
 * Created by Enrico on 31/01/14.
 */
public class Compito extends DbModel {

    private Date giorno;
    private String descrizione;
    private int idLezione;

    public Compito() {}

    public Compito(Date giorno, String descrizione, int idLezione) {
        this.giorno = giorno;
        this.descrizione = descrizione;
        this.idLezione = idLezione;
    }

    public void setGiorno(Date giorno) {
        this.giorno = giorno;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setIdLezione(int idLezione) {
        this.idLezione = idLezione;
    }

    public Date getGiorno() {
        return giorno;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getIdLezione() {
        return idLezione;
    }

    public static Compito find(long id) {
        return new DatabaseOpenHelper(MyApplication.getAppContext()).getCompito(id);
    }

    public static List<Compito> all() {
        return new DatabaseOpenHelper(MyApplication.getAppContext()).getAllCompiti();
    }
}
