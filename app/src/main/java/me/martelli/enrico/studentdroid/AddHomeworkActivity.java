package me.martelli.enrico.studentdroid;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import me.martelli.enrico.studentdroid.sqlite.model.Lezione;
import me.martelli.enrico.studentdroid.sqlite.model.Materia;

public class AddHomeworkActivity extends DoneBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);

        List<Materia> materie = Materia.all();
        List<Lezione> lezioni = Lezione.oggi();

        List<Materia> oggi = new ArrayList<Materia>();
        for(Lezione lezione : lezioni) {
            Materia m = Materia.find(lezione.getIdMateria());
            if(! oggi.contains(m)) oggi.add(m);
            materie.remove(m);
        }

        oggi.addAll(materie);
    }

    @Override
    public void action(View rootView) {

    }
}
