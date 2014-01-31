package me.martelli.enrico.studentdroid.sqlite.model;

/**
 * Created by Enrico on 31/01/14.
 */
public class Reminder {

    private int id;
    private int idVerifica;

    public Reminder() {}

    public Reminder(int idVerifica) {
        this.idVerifica = idVerifica;
    }

    public Reminder(int id, int idVerifica) {
        this.id = id;
        this.idVerifica = idVerifica;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdVerifica(int idVerifica) {
        this.idVerifica = idVerifica;
    }

    public int getId() {
        return id;
    }

    public int getIdVerifica() {
        return idVerifica;
    }
}
