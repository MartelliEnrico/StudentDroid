package me.martelli.enrico.studentdroid.sqlite.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

import me.martelli.enrico.studentdroid.MyApplication;
import me.martelli.enrico.studentdroid.sqlite.helper.DatabaseOpenHelper;
import me.martelli.enrico.studentdroid.sqlite.helper.DbModel;

/**
 * Created by Enrico on 31/01/14.
 * http://parcelabler.com/
 */
public class Lezione extends DbModel implements Parcelable {

    private int giorno;
    private Date inizio;
    private Date fine;
    private String classe;
    private String descrizione;
    private int idMateria;

    public Lezione() {}

    public Lezione(int giorno, Date inizio, Date fine, String classe, String descrizione, int idMateria) {
        this.giorno = giorno;
        this.inizio = inizio;
        this.fine = fine;
        this.classe = classe;
        this.descrizione = descrizione;
        this.idMateria = idMateria;
    }

    public void setGiorno(int giorno) {
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

    public int getGiorno() {
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
        return new DatabaseOpenHelper(MyApplication.getAppContext()).getLezione(id);
    }

    public static List<Lezione> all() {
        return new DatabaseOpenHelper(MyApplication.getAppContext()).getAllLezioni();
    }

    public static List<Lezione> oggi() {
        return new DatabaseOpenHelper(MyApplication.getAppContext()).getTodayLezioni();
    }

    protected Lezione(Parcel in) {
        id = in.readLong();
        giorno = in.readInt();
        long tmpInizio = in.readLong();
        inizio = tmpInizio != -1 ? new Date(tmpInizio) : null;
        long tmpFine = in.readLong();
        fine = tmpFine != -1 ? new Date(tmpFine) : null;
        classe = in.readString();
        descrizione = in.readString();
        idMateria = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(giorno);
        dest.writeLong(inizio != null ? inizio.getTime() : -1L);
        dest.writeLong(fine != null ? fine.getTime() : -1L);
        dest.writeString(classe);
        dest.writeString(descrizione);
        dest.writeInt(idMateria);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Lezione> CREATOR = new Parcelable.Creator<Lezione>() {
        @Override
        public Lezione createFromParcel(Parcel in) {
            return new Lezione(in);
        }

        @Override
        public Lezione[] newArray(int size) {
            return new Lezione[size];
        }
    };
}