package me.martelli.enrico.studentdroid.sqlite.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import me.martelli.enrico.studentdroid.MyApplication;
import me.martelli.enrico.studentdroid.sqlite.helper.DatabaseOpenHelper;
import me.martelli.enrico.studentdroid.sqlite.helper.DbModel;

/**
 * Created by Enrico on 31/01/14.
 * http://parcelabler.com/
 */
public class Materia extends DbModel implements Parcelable {

    private String nome;
    private String nome_professore;
    private String descrizione;
    private int colore;

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
        return new DatabaseOpenHelper(MyApplication.getAppContext()).getMateria(id);
    }

    public static List<Materia> all() {
        return new DatabaseOpenHelper(MyApplication.getAppContext()).getAllMaterie();
    }

    protected Materia(Parcel in) {
        id = in.readLong();
        nome = in.readString();
        nome_professore = in.readString();
        descrizione = in.readString();
        colore = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nome);
        dest.writeString(nome_professore);
        dest.writeString(descrizione);
        dest.writeInt(colore);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Materia> CREATOR = new Parcelable.Creator<Materia>() {
        @Override
        public Materia createFromParcel(Parcel in) {
            return new Materia(in);
        }

        @Override
        public Materia[] newArray(int size) {
            return new Materia[size];
        }
    };
}
