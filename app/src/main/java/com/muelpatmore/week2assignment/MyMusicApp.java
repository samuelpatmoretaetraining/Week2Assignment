package com.muelpatmore.week2assignment;

import android.app.Application;
import android.content.Context;

/**
 * Created by Samuel on 24/11/2017.
 */

public class MyMusicApp extends Application {
    public static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
