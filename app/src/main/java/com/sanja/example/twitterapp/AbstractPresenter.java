package com.sanja.example.twitterapp;

import android.support.annotation.CallSuper;

public abstract class AbstractPresenter<V> implements BasePresenter<V> {

    private volatile V view;

    @CallSuper
    @Override
    public void bind(V view) {
        if (this.view == null) {
            this.view = view;
            onBind();
        } else {
            throw new IllegalStateException("Cannot bind new view without unbinding the previous view first!");
        }
    }

    @CallSuper
    @Override
    public void unbind(V view) {
        if (this.view.equals(view)) {
            this.view = null;
            onUnbind();
        } else {
            throw new IllegalStateException("Cannot unbind view which is not bound.");
        }
    }

    @Override
    public V view() {
        return view;
    }


    protected void onBind() {
    }

    protected void onUnbind() {
    }
}