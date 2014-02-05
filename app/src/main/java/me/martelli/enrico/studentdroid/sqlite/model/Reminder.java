package me.martelli.enrico.studentdroid.sqlite.model;

import java.util.List;

import me.martelli.enrico.studentdroid.MyApplication;
import me.martelli.enrico.studentdroid.sqlite.helper.DatabaseOpenHelper;
import me.martelli.enrico.studentdroid.sqlite.helper.DbModel;

/**
 * Created by Enrico on 31/01/14.
 */
public class Reminder extends DbModel {

    private int idVerifica;

    public Reminder() {}

    public Reminder(int idVerifica) {
        this.idVerifica = idVerifica;
    }

    public void setIdVerifica(int idVerifica) {
        this.idVerifica = idVerifica;
    }

    public int getIdVerifica() {
        return idVerifica;
    }

    public static Reminder find(long id) {
        return new DatabaseOpenHelper(MyApplication.getAppContext()).getReminder(id);
    }

    public static List<Reminder> all() {
        return new DatabaseOpenHelper(MyApplication.getAppContext()).getAllReminders();
    }
}
