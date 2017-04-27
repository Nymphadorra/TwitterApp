package com.sanja.example.twitterapp.di.components;

import com.sanja.example.twitterapp.app.api.APIService;
import com.sanja.example.twitterapp.app.api.APIServiceAuth;
import com.sanja.example.twitterapp.di.AppScope;
import com.sanja.example.twitterapp.di.modules.APIModule;
import com.sanja.example.twitterapp.di.modules.DataModule;
import com.sanja.example.twitterapp.di.modules.MainModule;
import com.sanja.example.twitterapp.home.MainActivity;
import com.sanja.example.twitterapp.home.TweetFragment;
import com.squareup.picasso.Picasso;

import dagger.Component;

@AppScope
@Component(modules = {MainModule.class, APIModule.class, DataModule.class})
public interface AppComponent {

    void inject(TweetFragment tweetFragment);

    // List of dependencies that will be accessible to subcomponents.

    APIService apiService();

    APIServiceAuth apiServiceAuth();

    Picasso picasso();
}