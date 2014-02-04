package me.martelli.enrico.studentdroid.sqlite.model;

import java.util.Date;
import java.util.List;

import me.martelli.enrico.studentdroid.MyApplication;
import me.martelli.enrico.studentdroid.sqlite.helper.DatabaseOpenHelper;
import me.martelli.enrico.studentdroid.sqlite.helper.DbModel;

/**
 * Created by Enrico on 31/01/14.
 */
public class Verifica extends DbModel {

    private Date giorno;
    private String tipologia;
    private int idLezione;

    public Verifica() {}

    public Verifica(Date giorno, String tipologia, int idLezione) {
        this.giorno = giorno;
        this.tipologia = tipologia;
        this.idLezione = idLezione;
    }

    public void setGiorno(Date giorno) {
        this.giorno = giorno;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public void setIdLezione(int idLezione) {
        this.idLezione = idLezione;
    }

    public Date getGiorno() {
        return giorno;
    }

    public String getTipologia() {
        return tipologia;
    }

    public int getIdLezione() {
        return idLezione;
    }

    public static Verifica find(long id) {
        DatabaseOpenHelper db = new DatabaseOpenHelper(MyApplication.getAppContext());

        return db.getVerifica(id);
    }

    public static List<Verifica> all() {
        DatabaseOpenHelper db = new DatabaseOpenHelper(MyApplication.getAppContext());

        return db.getAllVerifiche();
    }
}
