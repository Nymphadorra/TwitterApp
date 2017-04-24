package com.sanja.example.twitterapp.di.components;

import com.sanja.example.twitterapp.APIService;
import com.sanja.example.twitterapp.APIServiceAuth;
import com.sanja.example.twitterapp.di.AppScope;
import com.sanja.example.twitterapp.di.modules.APIModule;
import com.sanja.example.twitterapp.di.modules.DataModule;
import com.sanja.example.twitterapp.di.modules.MainModule;

import dagger.Component;

@AppScope
@Component(modules = {MainModule.class, APIModule.class, DataModule.class})
public interface AppComponent {

    // List of dependencies that will be accessible to subcomponents.

    APIService apiService();

    APIServiceAuth apiServiceAuth();
}