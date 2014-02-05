package me.martelli.enrico.studentdroid;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import me.martelli.enrico.studentdroid.listener.OnClickListenerCounter;
import me.martelli.enrico.studentdroid.sqlite.helper.DatabaseOpenHelper;
import me.martelli.enrico.studentdroid.sqlite.model.Lezione;
import me.martelli.enrico.studentdroid.sqlite.model.Materia;
import me.martelli.enrico.studentdroid.sqlite.model.adapter.LezioneArrayAdapter;

public class DayViewFragment extends Fragment {

//    ListView mLessonsListView;
    LinearLayout mLessonsLinearLayout;
    Button mAddSampleLessons;
    Button mClearSampleLessons;

    public DayViewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getActionBar().setTitle(R.string.title_section1);

        View rootView = inflater.inflate(R.layout.fragment_day_view, container, false);

//        mLessonsListView = (ListView) rootView.findViewById(R.id.lessons_list);
//        mLessonsListView.setScrollContainer(false);

//        mLessonsListView.setAdapter(new LezioneArrayAdapter(
//                getActivity().getBaseContext(),
//                Lezione.oggi()
//        ));

        mLessonsLinearLayout = (LinearLayout) rootView.findViewById(R.id.lessons_list);

        LezioneArrayAdapter lezioneArrayAdapter = new LezioneArrayAdapter(
                getActivity().getBaseContext(), Lezione.oggi()
        );

        for(int i = 0; i < lezioneArrayAdapter.getCount(); i++) {
            View view = lezioneArrayAdapter.getView(i, null, null);
            view.setOnClickListener(new OnClickListenerCounter(i));
            mLessonsLinearLayout.addView(view);
        }

        mAddSampleLessons = (Button) rootView.findViewById(R.id.add_sample_lessons);

        mAddSampleLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Materia matematica = new Materia(
                        "Matematica",
                        "Ferretti",
                        null,
                        Color.rgb(255, 68, 68)
                );
                matematica.save();

                Materia inglese = new Materia(
                        "Inglese",
                        "Starace",
                        null,
                        Color.rgb(170, 102, 204)
                );
                inglese.save();

                Materia storia = new Materia(
                        "Storia",
                        "D'Alanno",
                        null,
                        Color.rgb(255, 187, 51)
                );
                storia.save();

                Lezione math = new Lezione(
                        4,
                        new Date(1000 * 60 * 60 * 8),
                        new Date(1000 * 60 * 60 * 9),
                        "208",
                        null,
                        (int) matematica.getId()
                );
                math.save();

                Lezione english = new Lezione(
                        4,
                        new Date(1000 * 60 * 60 * 9),
                        new Date(1000 * 60 * 60 * 10),
                        "15B",
                        null,
                        (int) inglese.getId()
                );
                english.save();

                Lezione history = new Lezione(
                        4,
                        new Date(1000 * 60 * 60 * 10),
                        new Date(1000 * 60 * 60 * 11),
                        "115",
                        null,
                        (int) storia.getId()
                );
                history.save();

//                ((LezioneArrayAdapter) mLessonsListView.getAdapter()).clear();

//                ((LezioneArrayAdapter) mLessonsListView.getAdapter()).addAll(Lezione.oggi());

                mLessonsLinearLayout.removeAllViews();

                LezioneArrayAdapter lezioneArrayAdapter = new LezioneArrayAdapter(
                        getActivity().getBaseContext(), Lezione.oggi()
                );

                for(int i = 0; i < lezioneArrayAdapter.getCount(); i++) {
                    View v = lezioneArrayAdapter.getView(i, null, null);
                    mLessonsLinearLayout.addView(v);
                }
            }
        });

        mClearSampleLessons = (Button) rootView.findViewById(R.id.remove_sample_lessons);

        mClearSampleLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = new DatabaseOpenHelper(getActivity().getBaseContext()).getWritableDatabase();

                db.execSQL("DELETE FROM lezioni");
                db.execSQL("DELETE FROM materie");

//                ((LezioneArrayAdapter) mLessonsListView.getAdapter()).clear();

                mLessonsLinearLayout.removeAllViews();
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.day_view, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
            case R.id.action_add_homework:
                Toast.makeText(getActivity(), "Add homework", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_add_exam:
                Toast.makeText(getActivity(), "Add exam", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
