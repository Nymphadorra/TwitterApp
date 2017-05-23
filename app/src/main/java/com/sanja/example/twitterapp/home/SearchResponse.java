package com.sanja.example.twitterapp.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

    @SerializedName("statuses") private List<Tweet> tweets;
    @SerializedName("search_metadata") private SearchMetaData searchMetaData;

    public List<Tweet> getTweets() {
        return tweets;
    }

    public SearchMetaData getSearchMetaData() {
        return searchMetaData;
    }
}