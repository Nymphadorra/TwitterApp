package com.sanja.example.twitterapp.di.modules;

import com.sanja.example.twitterapp.app.api.APIService;
import com.sanja.example.twitterapp.home.HomeMvp;
import com.sanja.example.twitterapp.home.HomePresenter;
import com.sanja.example.twitterapp.queries.SearchQueriesManager;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    @Provides
    public HomeMvp.Presenter provideHomePresenter(APIService apiService, SearchQueriesManager sqManager) {
        return new HomePresenter(apiService, sqManager);
    }
}
