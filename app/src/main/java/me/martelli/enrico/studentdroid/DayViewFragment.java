package me.martelli.enrico.studentdroid;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.martelli.enrico.studentdroid.listener.CompitoOnClickListenerCounter;
import me.martelli.enrico.studentdroid.listener.MateriaOnClickListenerCounter;
import me.martelli.enrico.studentdroid.sqlite.model.Compito;
import me.martelli.enrico.studentdroid.sqlite.model.Lezione;
import me.martelli.enrico.studentdroid.sqlite.model.adapter.CompitoArrayAdapter;
import me.martelli.enrico.studentdroid.sqlite.model.adapter.LezioneArrayAdapter;

public class DayViewFragment extends Fragment {

    LinearLayout mLessonsLinearLayout;
    LinearLayout mHomeworkLinearLayout;

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

        String day = new SimpleDateFormat("EEEE").format(new Date());
        String full = new SimpleDateFormat("dd MMM yyyy").format(new Date());

        TextView todayDay = (TextView) rootView.findViewById(R.id.today_day);
        todayDay.setText(day);

        TextView fullDate = (TextView) rootView.findViewById(R.id.today_full_date);
        fullDate.setText(full);

        mLessonsLinearLayout = (LinearLayout) rootView.findViewById(R.id.lessons_list);

        LezioneArrayAdapter lezioneArrayAdapter = new LezioneArrayAdapter(
                getActivity().getBaseContext(), Lezione.oggi()
        );

        if(lezioneArrayAdapter.getCount() == 0) {
            TextView empty = (TextView) inflater.inflate(R.layout.empty_item, mLessonsLinearLayout, false);
            empty.setText("No school for today :)");
            mLessonsLinearLayout.addView(empty);
        }

        for(int i = 0; i < lezioneArrayAdapter.getCount(); i++) {
            View view = lezioneArrayAdapter.getView(i, null, mLessonsLinearLayout);
            view.setOnClickListener(new MateriaOnClickListenerCounter(i));
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

        mHomeworkLinearLayout = (LinearLayout) rootView.findViewById(R.id.homework_list);

        mHomeworkLinearLayout.addView(inflater.inflate(R.layout.homework_header, null));

        CompitoArrayAdapter compitoArrayAdapter = new CompitoArrayAdapter(
                getActivity().getBaseContext(), Compito.rimanenti()
        );

        if(compitoArrayAdapter.getCount() == 0) {
            TextView empty = (TextView) inflater.inflate(R.layout.empty_item, mLessonsLinearLayout, false);
            empty.setText("No homework left :)");
            mHomeworkLinearLayout.addView(empty);
        }

        for(int i = 0; i < compitoArrayAdapter.getCount(); i++) {
            View view = compitoArrayAdapter.getView(i, null, mHomeworkLinearLayout);
            view.setOnClickListener(new CompitoOnClickListenerCounter(i));
            mHomeworkLinearLayout.addView(view);
            if(i < compitoArrayAdapter.getCount() - 1) {
                mHomeworkLinearLayout.addView(inflater.inflate(R.layout.separator, mHomeworkLinearLayout, false));
            }
        }

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
                Intent intent = new Intent(getActivity(), AddHomeworkActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_add_exam:
                Toast.makeText(getActivity(), "Add exam", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
