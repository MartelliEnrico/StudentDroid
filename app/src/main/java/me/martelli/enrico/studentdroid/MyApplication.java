package me.martelli.enrico.studentdroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class MyApplication extends Activity {

    private static Context context;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
