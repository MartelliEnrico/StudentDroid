package me.martelli.enrico.studentdroid;

import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
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
import me.martelli.enrico.studentdroid.sqlite.helper.DebugDbSeeder;
import me.martelli.enrico.studentdroid.sqlite.model.Lezione;
import me.martelli.enrico.studentdroid.sqlite.model.Materia;
import me.martelli.enrico.studentdroid.sqlite.model.adapter.LezioneArrayAdapter;

public class DayViewFragment extends Fragment {

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

        mLessonsLinearLayout = (LinearLayout) rootView.findViewById(R.id.lessons_list);

        LezioneArrayAdapter lezioneArrayAdapter = new LezioneArrayAdapter(
                getActivity().getBaseContext(), Lezione.oggi()
        );

        for(int i = 0; i < lezioneArrayAdapter.getCount(); i++) {
            View view = lezioneArrayAdapter.getView(i, null, null);
            view.setOnClickListener(new OnClickListenerCounter(i));
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch(motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            view.setAlpha(0.9f);
                            break;
                        case MotionEvent.ACTION_UP:
                            view.setAlpha(1);
                            break;
                    }
                    return false;
                }
            });
            mLessonsLinearLayout.addView(view);
        }

        mAddSampleLessons = (Button) rootView.findViewById(R.id.add_sample_lessons);

        mAddSampleLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DebugDbSeeder.seed();

                mLessonsLinearLayout.removeAllViews();

                LezioneArrayAdapter lezioneArrayAdapter = new LezioneArrayAdapter(
                        getActivity().getBaseContext(), Lezione.oggi()
                );

                for(int i = 0; i < lezioneArrayAdapter.getCount(); i++) {
                    View v = lezioneArrayAdapter.getView(i, null, null);
                    v.setOnClickListener(new OnClickListenerCounter(i));
                    v.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            switch(motionEvent.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    view.setAlpha(0.9f);
                                    break;
                                case MotionEvent.ACTION_UP:
                                    view.setAlpha(1);
                                    break;
                            }
                            return false;
                        }
                    });
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
