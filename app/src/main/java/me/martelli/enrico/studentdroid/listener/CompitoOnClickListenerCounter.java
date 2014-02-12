package me.martelli.enrico.studentdroid.listener;

import android.content.Intent;
import android.view.View;

import me.martelli.enrico.studentdroid.MateriaActivity;
import me.martelli.enrico.studentdroid.MyApplication;
import me.martelli.enrico.studentdroid.sqlite.model.Compito;

/**
 * Created by Enrico on 05/02/14.
 */
public class CompitoOnClickListenerCounter implements View.OnClickListener {

    public final static String ID_LEZIONE = "me.martelli.enrico.studentdroid.ID_LEZIONE";

    private int counter;

    public CompitoOnClickListenerCounter(int counter) {
        super();
        this.counter = counter;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MyApplication.getAppContext(), MateriaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(ID_LEZIONE, Compito.rimanenti().get(counter).getIdLezione());
        MyApplication.getAppContext().startActivity(intent);
    }
}
