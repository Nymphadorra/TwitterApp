package com.sanja.example.twitterapp.queries;

import com.sanja.example.twitterapp.di.ActivityScope;
import com.sanja.example.twitterapp.di.components.AppComponent;
import com.sanja.example.twitterapp.di.modules.SettingsModule;

import dagger.Component;

@ActivityScope
@Component(
        modules = SettingsModule.class,
        dependencies = AppComponent.class)
public interface SettingsComponent {
    void inject (SearchQueriesActivity searchQueriesActivity);
}