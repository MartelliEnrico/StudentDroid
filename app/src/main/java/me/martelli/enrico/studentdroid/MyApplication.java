package me.martelli.enrico.studentdroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class MyApplication {

    public static Context context;

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
