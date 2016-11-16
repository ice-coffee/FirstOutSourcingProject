package com.github.out;

import android.app.Application;

/**
 * Created by mzp on 2016/11/15.
 */

public class FirstProjectApplication extends Application
{
    public static FirstProjectApplication myApplication;

    @Override
    public void onCreate()
    {
        super.onCreate();

        myApplication = this;
    }

    public static Application getContext()
    {
        return myApplication;
    }
}
