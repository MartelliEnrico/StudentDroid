package me.martelli.enrico.studentdroid.sqlite.model;

import java.util.List;

import me.martelli.enrico.studentdroid.MyApplication;
import me.martelli.enrico.studentdroid.sqlite.helper.DatabaseOpenHelper;
import me.martelli.enrico.studentdroid.sqlite.helper.DbModel;

/**
 * Created by Enrico on 31/01/14.
 */
public class Voto extends DbModel {

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

    public static Voto find(long id) {
        return new DatabaseOpenHelper(MyApplication.getAppContext()).getVoto(id);
    }

    public static List<Voto> all() {
        return new DatabaseOpenHelper(MyApplication.getAppContext()).getAllVoti();
    }
}
