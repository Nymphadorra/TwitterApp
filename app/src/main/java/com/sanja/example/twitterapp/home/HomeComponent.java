package com.sanja.example.twitterapp.home;

import com.sanja.example.twitterapp.MainActivity;
import com.sanja.example.twitterapp.di.HomeScope;
import com.sanja.example.twitterapp.di.components.AppComponent;
import com.sanja.example.twitterapp.di.modules.HomeModule;

import dagger.Component;

@HomeScope
@Component (
        modules = HomeModule.class,
        dependencies = AppComponent.class
)
public interface HomeComponent {

    void inject(MainActivity mainActivity);
}
