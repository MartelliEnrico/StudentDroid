package me.martelli.enrico.studentdroid.sqlite.model;

/**
 * Created by Enrico on 31/01/14.
 */
public class Lezione {

    private int id;
    private int giorno;
    private int inizio;
    private int fine;
    private String classe;
    private String descrizione;
    private int idMateria;

    public Lezione() {}

    public Lezione(int giorno, int inizio, int fine, String classe, String descrizione, int idMateria) {
        this.giorno = giorno;
        this.inizio = inizio;
        this.fine = fine;
        this.classe = classe;
        this.descrizione = descrizione;
        this.idMateria = idMateria;
    }

    public Lezione(int id, int giorno, int inizio, int fine, String classe, String descrizione, int idMateria) {
        this.id = id;
        this.giorno = giorno;
        this.inizio = inizio;
        this.fine = fine;
        this.classe = classe;
        this.descrizione = descrizione;
        this.idMateria = idMateria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public void setInizio(int inizio) {
        this.inizio = inizio;
    }

    public void setFine(int fine) {
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

    public int getId() {
        return id;
    }

    public int getGiorno() {
        return giorno;
    }

    public int getInizio() {
        return inizio;
    }

    public int getFine() {
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
}
