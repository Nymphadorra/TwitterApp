package com.sanja.example.twitterapp.home;

import com.sanja.example.twitterapp.app.BasePresenter;
import com.sanja.example.twitterapp.Tweet;

import java.util.List;

public class HomeMvp {

    public interface View{

        void showTweets(List<Tweet> tweets);

        void showListLayout();

        void showPagerLayout();
    }

    public interface Presenter extends BasePresenter<View> {

        void onAutoScrollClicked();

        void onListLayoutClicked();

        void onPagerLayoutClicked();

        void onSettingsClicked();
    }
}