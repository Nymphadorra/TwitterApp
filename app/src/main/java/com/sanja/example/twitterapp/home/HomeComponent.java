package com.sanja.example.twitterapp.home;

import com.sanja.example.twitterapp.di.ActivityScope;
import com.sanja.example.twitterapp.di.components.AppComponent;
import com.sanja.example.twitterapp.di.modules.HomeModule;

import dagger.Component;

@ActivityScope
@Component(
        modules = HomeModule.class,
        dependencies = AppComponent.class)
public interface HomeComponent {

    /**
     * Activities, services, fragments or other objects which use member injection should be declared in
     * this class with individual inject() methods. That way dagger2 will generate {@link dagger.MembersInjector} class
     * to satisfy @Inject annotated members.
     */
    void inject(MainActivity mainActivity);
}