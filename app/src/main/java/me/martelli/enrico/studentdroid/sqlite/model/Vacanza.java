package me.martelli.enrico.studentdroid.sqlite.model;

/**
 * Created by Enrico on 31/01/14.
 */
public class Vacanza {

    private int id;
    private int inizio;
    private int fine;
    private String descrizione;

    public Vacanza() {}

    public Vacanza(int inizio, int fine, String descrizione) {
        this.inizio = inizio;
        this.fine = fine;
        this.descrizione = descrizione;
    }

    public Vacanza(int id, int inizio, int fine, String descrizione) {
        this.id = id;
        this.inizio = inizio;
        this.fine = fine;
        this.descrizione = descrizione;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInizio(int inizio) {
        this.inizio = inizio;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getId() {
        return id;
    }

    public int getInizio() {
        return inizio;
    }

    public int getFine() {
        return fine;
    }

    public String getDescrizione() {
        return descrizione;
    }
}
