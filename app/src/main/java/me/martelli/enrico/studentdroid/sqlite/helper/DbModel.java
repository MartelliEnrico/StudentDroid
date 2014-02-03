package me.martelli.enrico.studentdroid.sqlite.helper;

import me.martelli.enrico.studentdroid.MyApplication;

/**
 * Created by Enrico on 03/02/14.
 */
public class DbModel {

    private boolean pSaved = false;
    private int id;

    public int save() {
        DatabaseOpenHelper db = new DatabaseOpenHelper(MyApplication.getAppContext());
        int mId = this.id;

        if(!pSaved) {
            pSaved = true;
            // create a new db object
            mId = (int) db.create(this);
        } else {
            // update current object
            db.update(this);
        }

        return mId;
    }

    public void setSaved(boolean saved) {
        this.pSaved = saved;
    }

    public boolean getSaved() {
        return pSaved;
    }
}
