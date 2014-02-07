package me.martelli.enrico.studentdroid;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.martelli.enrico.studentdroid.drawable.ActionBarDrawable;
import me.martelli.enrico.studentdroid.listener.OnClickListenerCounter;
import me.martelli.enrico.studentdroid.sqlite.model.Lezione;
import me.martelli.enrico.studentdroid.sqlite.model.Materia;

public class MateriaActivity extends Activity {

    Materia materia;
    Lezione lezione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_recap);

        int position = getIntent().getExtras().getInt(OnClickListenerCounter.ID_LEZIONE);
        lezione = Lezione.oggi().get(position);
        materia = Materia.find(lezione.getIdMateria());

        getActionBar().setBackgroundDrawable(new ActionBarDrawable(materia.getColore()));

        setTitle(materia.getNome());

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MateriaFragment().newInstace(materia, lezione))
                    .commit();
        }
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
