package com.sanja.example.twitterapp.app;

public interface BasePresenter<V> {

    V view();

    void bind(V view);

    void unbind(V view);
}