package com.sanja.example.twitterapp.di.modules;

import android.content.Context;

import com.sanja.example.twitterapp.di.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private Context context;

    public MainModule(Context context) {
        this.context = context;
    }

    @AppScope
    @Provides
    Context provideAppContext() {
        return context;
    }
}