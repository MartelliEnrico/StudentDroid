package me.martelli.enrico.studentdroid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import me.martelli.enrico.studentdroid.drawable.ActionBarDrawable;
import me.martelli.enrico.studentdroid.listener.MateriaOnClickListenerCounter;
import me.martelli.enrico.studentdroid.sqlite.model.Lezione;
import me.martelli.enrico.studentdroid.sqlite.model.Materia;

public class MateriaActivity extends FragmentActivity {

    Materia materia;
    Lezione lezione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_materia);

        int idLezione = getIntent().getExtras().getInt(MateriaOnClickListenerCounter.ID_LEZIONE);
        lezione = Lezione.find(idLezione);
        materia = Materia.find(lezione.getIdMateria());

        getActionBar().setBackgroundDrawable(new ActionBarDrawable(materia.getColore()));

        setTitle(materia.getNome());

        TextView nome = (TextView) findViewById(R.id.materia_nome);
        nome.setText(materia.getNome());

        TextView nomeProfessore = (TextView) findViewById(R.id.materia_nome_professore);
        nomeProfessore.setText(materia.getNomeProfessore());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(this, "Show settings", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
