package me.martelli.enrico.studentdroid.listener;

import android.content.Intent;
import android.view.View;

import me.martelli.enrico.studentdroid.MyApplication;

/**
 * Created by Enrico on 05/02/14.
 */
public class OnClickListenerCounter implements View.OnClickListener {

    private int counter;

    public OnClickListenerCounter(int counter) {
        super();
        this.counter = counter;
    }

    @Override
    public void onClick(View view) {
        //
    }
}
