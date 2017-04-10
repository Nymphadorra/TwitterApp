package com.sanja.example.twitterapp;

interface BasePresenter<V> {

    V view();

    void bind(V view);

    void unbind(V view);
}