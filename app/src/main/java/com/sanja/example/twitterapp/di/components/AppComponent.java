package com.sanja.example.twitterapp.di.components;

import com.sanja.example.twitterapp.APIService;
import com.sanja.example.twitterapp.APIServiceAuth;
import com.sanja.example.twitterapp.di.AppScope;
import com.sanja.example.twitterapp.di.modules.APIModule;

import dagger.Component;

@AppScope
@Component(modules = APIModule.class)
public interface AppComponent {

    // List of dependencies that will be accessible to subcomponents.

    APIService apiService();

    APIServiceAuth apiServiceAuth();
}