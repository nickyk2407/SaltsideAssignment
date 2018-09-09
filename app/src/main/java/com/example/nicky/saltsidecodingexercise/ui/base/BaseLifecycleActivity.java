package com.example.nicky.saltsidecodingexercise.ui.base;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by NICKY on 09-09-2018.
 */
@SuppressLint("Registered")
public class BaseLifecycleActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @MainThread
    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
