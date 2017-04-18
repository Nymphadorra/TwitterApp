package com.sanja.example.twitterapp;

public interface BasePresenter<V> {

    V view();

    void bind(V view);

    void unbind(V view);
}