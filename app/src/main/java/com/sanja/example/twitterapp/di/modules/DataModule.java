package com.sanja.example.twitterapp.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.sanja.example.twitterapp.SearchQueriesPreferences;
import com.sanja.example.twitterapp.TokenPreferences;
import com.sanja.example.twitterapp.SearchQueriesManager;
import com.sanja.example.twitterapp.di.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @AppScope
    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @AppScope
    @Provides
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @AppScope
    @Provides
    public TokenPreferences providePreferences(SharedPreferences sharedPreferences) {
        return new TokenPreferences(sharedPreferences);
    }

    @AppScope
    @Provides
    public SearchQueriesPreferences provideSearchQueriesPreferences(SharedPreferences sharedPreferences, Gson gson) {
        return new SearchQueriesPreferences(sharedPreferences, gson);
    }

    @AppScope
    @Provides
    public SearchQueriesManager provideSearchQueriesManager(SearchQueriesPreferences sqPreferences) {
        return new SearchQueriesManager(sqPreferences);
    }
}