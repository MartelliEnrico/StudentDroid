package me.martelli.enrico.studentdroid.sqlite.model;

import java.util.List;

import me.martelli.enrico.studentdroid.MyApplication;
import me.martelli.enrico.studentdroid.sqlite.helper.DatabaseOpenHelper;
import me.martelli.enrico.studentdroid.sqlite.helper.DbModel;

/**
 * Created by Enrico on 31/01/14.
 */
public class Materia extends DbModel {

    private String nome;
    private String nome_professore;
    private String descrizione;
    private int colore;

    private boolean saved = false;

    public Materia() {}

    public Materia(String nome, String nome_professore, String descrizione, int colore) {
        this.nome = nome;
        this.nome_professore = nome_professore;
        this.descrizione = descrizione;
        this.colore = colore;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomeProfessore(String nome_professore) {
        this.nome_professore = nome_professore;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setColore(int colore) {
        this.colore = colore;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeProfessore() {
        return nome_professore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getColore() {
        return colore;
    }

    public static Materia find(long id) {
        DatabaseOpenHelper db = new DatabaseOpenHelper(MyApplication.getAppContext());

        return db.getMateria(id);
    }

    public static List<Materia> all() {
        DatabaseOpenHelper db = new DatabaseOpenHelper(MyApplication.getAppContext());

        return db.getAllMaterie();
    }
}
