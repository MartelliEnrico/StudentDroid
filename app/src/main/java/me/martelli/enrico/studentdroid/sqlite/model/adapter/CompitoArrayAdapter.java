package me.martelli.enrico.studentdroid.sqlite.model.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import me.martelli.enrico.studentdroid.R;
import me.martelli.enrico.studentdroid.sqlite.model.Compito;
import me.martelli.enrico.studentdroid.sqlite.model.Lezione;
import me.martelli.enrico.studentdroid.sqlite.model.Materia;

/**
 * Created by Enrico on 05/02/14.
 */
public class CompitoArrayAdapter extends ArrayAdapter<Compito> {
    private final Context context;
    private final List<Compito> compiti;

    public CompitoArrayAdapter(Context context, List<Compito> objects) {
        super(context, R.layout.homework_item, objects);
        this.context = context;
        this.compiti = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.homework_item, parent, false);

        Compito compito = compiti.get(position);
        Lezione lezione = Lezione.find(compito.getIdLezione());
        Materia materia = Materia.find(lezione.getIdMateria());

        TextView materiaNome = (TextView) rowView.findViewById(R.id.materia_nome);
        materiaNome.setText(materia.getNome());

        TextView giornoLezione = (TextView) rowView.findViewById(R.id.lesson_day);
        giornoLezione.setText(computeDate(compito.getGiorno()).toUpperCase());

        TextView descrizione = (TextView) rowView.findViewById(R.id.homework_description);
        descrizione.setText(compito.getDescrizione());

        return rowView;
    }

    private String computeDate(Date date) {
        if(DateUtils.isToday(date.getTime())) {
            return "Today";
        }

        Date oggi = new Date();
        long oggiTime = oggi.getTime() - (oggi.getTime() % (1000 * 60 * 60 * 24));
        long domaniTime = oggiTime + (1000 * 60 * 60 * 24);
        long dopoDomani = domaniTime + (1000 * 60 * 60 * 24);

        if(date.getTime() >= domaniTime && date.getTime() < dopoDomani) {
            return "Tomorrow";
        }

        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");

        return format.format(date);
    }
}
