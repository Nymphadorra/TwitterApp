package com.sanja.example.twitterapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

    @SerializedName("statuses") private List<Tweet> tweets;

    public List<Tweet> getTweets() {
        return tweets;
    }
}