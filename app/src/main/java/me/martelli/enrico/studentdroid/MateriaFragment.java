package me.martelli.enrico.studentdroid;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.martelli.enrico.studentdroid.sqlite.model.Lezione;
import me.martelli.enrico.studentdroid.sqlite.model.Materia;

/**
 * Created by Enrico on 07/02/14.
 */
public class MateriaFragment extends Fragment {

    Materia materia;
    Lezione lezione;

    private static final String KEY_MATERIA = "me.martelli.enrico.MateriaFragment.MATERIA";
    private static final String KEY_LEZIONE = "me.martelli.enrico.MateriaFragment.LEZIONE";

    public static final MateriaFragment newInstace(Materia materia, Lezione lezione) {
        MateriaFragment f = new MateriaFragment();

        Bundle bundle = new Bundle(2);
        bundle.putParcelable(KEY_MATERIA, materia);
        bundle.putParcelable(KEY_LEZIONE, lezione);

        f.setArguments(bundle);

        return f;
    }

    public MateriaFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        materia = getArguments().getParcelable(KEY_MATERIA);
        lezione = getArguments().getParcelable(KEY_LEZIONE);

        View rootView = inflater.inflate(R.layout.fragment_materia, container, false);

        TextView nome = (TextView) rootView.findViewById(R.id.materia_nome);
        nome.setText(materia.getNome());

        TextView nomeProfessore = (TextView) rootView.findViewById(R.id.materia_nome_professore);
        nomeProfessore.setText(materia.getNomeProfessore());

        return rootView;
    }
}