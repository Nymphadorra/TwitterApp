package com.sanja.example.twitterapp;

import android.app.Application;

import com.sanja.example.twitterapp.di.components.AppComponentContainer;

import timber.log.Timber;

public class App extends Application {

    // Called when app is created.
    // Here are all app wide initializations.
    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        AppComponentContainer.init(this);
    }
}