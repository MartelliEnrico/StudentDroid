package me.martelli.enrico.studentdroid.sqlite.model.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import me.martelli.enrico.studentdroid.R;
import me.martelli.enrico.studentdroid.sqlite.model.Lezione;
import me.martelli.enrico.studentdroid.sqlite.model.Materia;

/**
 * Created by Enrico on 05/02/14.
 */
public class LezioneArrayAdapter extends ArrayAdapter<Lezione> {
    private final Context context;
    private final List<Lezione> lezioni;

    public LezioneArrayAdapter(Context context, List<Lezione> objects) {
        super(context, R.layout.day_view_lessons_item, objects);
        this.context = context;
        this.lezioni = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.day_view_lessons_item, parent, false);

        int idMateria = lezioni.get(position).getIdMateria();

        Materia materia = Materia.find(idMateria);

        rowView.setBackgroundColor(materia.getColore());

        TextView mInizioLezione = (TextView) rowView.findViewById(R.id.lesson_start);
        SimpleDateFormat formatOrarioInizio = new SimpleDateFormat("HH:mm");
        formatOrarioInizio.setTimeZone(TimeZone.getTimeZone("GMT"));
        mInizioLezione.setText(formatOrarioInizio.format(lezioni.get(position).getInizio()));

        TextView mClasse = (TextView) rowView.findViewById(R.id.lesson_classroom);
        mClasse.setText(lezioni.get(position).getClasse());

        TextView mNomeMateria = (TextView) rowView.findViewById(R.id.lesson_name);
        mNomeMateria.setText(materia.getNome().toUpperCase());

        return rowView;
    }
}
