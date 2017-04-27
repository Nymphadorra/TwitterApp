package com.sanja.example.twitterapp.app.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewAnimator;


public class ViewAnimatorById extends ViewAnimator{

    public ViewAnimatorById(Context context) {
        super(context);
    }

    public ViewAnimatorById(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setChildById(int childId) {
        for (int i = 0; i < getChildCount(); i++) {
            if(getChildAt(i).getId() == childId) {
                setDisplayedChild(i);
                return;
            }
        }
        throw new IllegalArgumentException("View with ID " + childId + " not found.");
    }
}