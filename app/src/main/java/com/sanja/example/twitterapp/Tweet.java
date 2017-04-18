package com.sanja.example.twitterapp;

import com.google.gson.annotations.SerializedName;

public class Tweet {

    @SerializedName("created_at") private String date;
    @SerializedName("text") private String text;

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}