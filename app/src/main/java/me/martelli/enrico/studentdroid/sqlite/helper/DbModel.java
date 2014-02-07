package me.martelli.enrico.studentdroid.sqlite.helper;

import android.os.Parcel;
import android.os.Parcelable;

import me.martelli.enrico.studentdroid.MyApplication;

/**
 * Created by Enrico on 03/02/14.
 */
public abstract class DbModel {

    protected long id = 0L;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void save() {
        DatabaseOpenHelper db = new DatabaseOpenHelper(MyApplication.getAppContext());

        if(id == 0L) {
            this.id = db.create(this);
        } else {
            db.update(this);
        }
    }

    public void delete() {
        DatabaseOpenHelper db = new DatabaseOpenHelper(MyApplication.getAppContext());

        db.delete(this);
    }
}
