package com.sanja.example.twitterapp.home;

import com.sanja.example.twitterapp.BasePresenter;
import com.sanja.example.twitterapp.SearchResponse;
import com.sanja.example.twitterapp.Tweet;

import java.util.List;

import retrofit2.Response;

public class HomeMvp {

    public interface View{

        void showTweets(List<Tweet> tweets);
    }

    public interface Presenter extends BasePresenter<View> {

    }
}