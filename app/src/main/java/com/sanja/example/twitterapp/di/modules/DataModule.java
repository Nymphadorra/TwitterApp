package com.sanja.example.twitterapp.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sanja.example.twitterapp.Preferences;
import com.sanja.example.twitterapp.di.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @AppScope
    @Provides
    public Preferences providePreferences(SharedPreferences sharedPreferences) {
        return new Preferences(sharedPreferences);
    }

    @AppScope
    @Provides
    public SharedPreferences provideSharedPreferences(Context context) {
           return PreferenceManager.getDefaultSharedPreferences(context);
    }
}