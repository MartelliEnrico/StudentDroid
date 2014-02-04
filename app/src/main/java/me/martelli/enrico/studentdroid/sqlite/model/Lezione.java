package me.martelli.enrico.studentdroid.sqlite.model;

import java.util.Date;
import java.util.List;

import me.martelli.enrico.studentdroid.MyApplication;
import me.martelli.enrico.studentdroid.sqlite.helper.DatabaseOpenHelper;
import me.martelli.enrico.studentdroid.sqlite.helper.DbModel;

/**
 * Created by Enrico on 31/01/14.
 */
public class Lezione extends DbModel {

    private Date giorno;
    private Date inizio;
    private Date fine;
    private String classe;
    private String descrizione;
    private int idMateria;

    public Lezione() {}

    public Lezione(Date giorno, Date inizio, Date fine, String classe, String descrizione, int idMateria) {
        this.giorno = giorno;
        this.inizio = inizio;
        this.fine = fine;
        this.classe = classe;
        this.descrizione = descrizione;
        this.idMateria = idMateria;
    }

    public void setGiorno(Date giorno) {
        this.giorno = giorno;
    }

    public void setInizio(Date inizio) {
        this.inizio = inizio;
    }

    public void setFine(Date fine) {
        this.fine = fine;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public Date getGiorno() {
        return giorno;
    }

    public Date getInizio() {
        return inizio;
    }

    public Date getFine() {
        return fine;
    }

    public String getClasse() {
        return classe;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public static Lezione find(long id) {
        DatabaseOpenHelper db = new DatabaseOpenHelper(MyApplication.getAppContext());

        return db.getLezione(id);
    }

    public static List<Lezione> all() {
        DatabaseOpenHelper db = new DatabaseOpenHelper(MyApplication.getAppContext());

        return db.getAllLezioni();
    }
}
