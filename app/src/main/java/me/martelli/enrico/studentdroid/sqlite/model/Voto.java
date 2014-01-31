package me.martelli.enrico.studentdroid.sqlite.model;

/**
 * Created by Enrico on 31/01/14.
 */
public class Voto {

    private int id;
    private float voto;
    private String tipologia;
    private String descrizione;
    private int idMateria;

    public Voto() {}

    public Voto(float voto, String tipologia, String descrizione, int idMateria) {
        this.voto = voto;
        this.tipologia = tipologia;
        this.descrizione = descrizione;
        this.idMateria = idMateria;
    }

    public Voto(int id, float voto, String tipologia, String descrizione, int idMateria) {
        this.id = id;
        this.voto = voto;
        this.tipologia = tipologia;
        this.descrizione = descrizione;
        this.idMateria = idMateria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVoto(float voto) {
        this.voto = voto;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
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

    public float getVoto() {
        return voto;
    }

    public String getTipologia() {
        return tipologia;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getIdMateria() {
        return idMateria;
    }
}
