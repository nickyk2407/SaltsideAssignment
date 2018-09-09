package com.example.nicky.saltsidecodingexercise.application;

import android.app.Application;

import com.example.nicky.saltsidecodingexercise.data.database.DatabaseHolder;


/**
 * Created by NICKY on 09-09-2018.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHolder.init(this);
    }
}
