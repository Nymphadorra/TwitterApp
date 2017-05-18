package com.sanja.example.twitterapp.di.modules;

import com.sanja.example.twitterapp.settings.SearchQueriesManager;
import com.sanja.example.twitterapp.settings.SearchQueriesMVP;
import com.sanja.example.twitterapp.settings.SearchQueriesPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsModule {
    @Provides
    public SearchQueriesMVP.Presenter provideSearchQueriesPresenter(SearchQueriesManager sqManager){
        return new SearchQueriesPresenter(sqManager);
    }
}