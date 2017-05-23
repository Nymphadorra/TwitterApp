package com.sanja.example.twitterapp.di.modules;

import com.sanja.example.twitterapp.queries.SearchQueriesManager;
import com.sanja.example.twitterapp.queries.SearchQueriesMVP;
import com.sanja.example.twitterapp.queries.SearchQueriesPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsModule {
    @Provides
    public SearchQueriesMVP.Presenter provideSearchQueriesPresenter(SearchQueriesManager sqManager){
        return new SearchQueriesPresenter(sqManager);
    }
}