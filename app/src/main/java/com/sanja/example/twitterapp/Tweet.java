package com.sanja.example.twitterapp;

import com.google.gson.annotations.SerializedName;

public class Tweet {

    @SerializedName("user") private User user;
    @SerializedName("created_at") private String date;
    @SerializedName("text") private String text;
    @SerializedName("favorite_count") private int favoriteCount;
    @SerializedName("retweet_count") private int retweetCount;

    public User getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public int getRetweetCount() {
        return retweetCount;
    }
}