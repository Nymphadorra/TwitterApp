package com.sanja.example.twitterapp.di.components;

import android.app.Application;

import com.sanja.example.twitterapp.di.components.DaggerAppComponent;

public class AppComponentContainer {

    private static AppComponent appComponent;

    public static AppComponent get() {
        if (appComponent == null) {
            throw new IllegalStateException("You must initialize AppComponent before using get()");
        }
        return appComponent;
    }

    public static void init(Application app) {
        appComponent = DaggerAppComponent.create();
    }
}
