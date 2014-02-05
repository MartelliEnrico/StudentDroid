package me.martelli.enrico.studentdroid.sqlite.model;

import java.util.Date;
import java.util.List;

import me.martelli.enrico.studentdroid.MyApplication;
import me.martelli.enrico.studentdroid.sqlite.helper.DatabaseOpenHelper;
import me.martelli.enrico.studentdroid.sqlite.helper.DbModel;

/**
 * Created by Enrico on 31/01/14.
 */
public class Vacanza extends DbModel {

    private Date inizio;
    private Date fine;
    private String descrizione;

    public Vacanza() {}

    public Vacanza(Date inizio, Date fine, String descrizione) {
        this.inizio = inizio;
        this.fine = fine;
        this.descrizione = descrizione;
    }

    public void setInizio(Date inizio) {
        this.inizio = inizio;
    }

    public void setFine(Date fine) {
        this.fine = fine;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Date getInizio() {
        return inizio;
    }

    public Date getFine() {
        return fine;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public static Vacanza find(long id) {
        return new DatabaseOpenHelper(MyApplication.getAppContext()).getVacanza(id);
    }

    public static List<Vacanza> all() {
        return new DatabaseOpenHelper(MyApplication.getAppContext()).getAllVacanze();
    }
}
