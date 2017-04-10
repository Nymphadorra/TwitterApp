package com.sanja.example.twitterapp;

import com.google.gson.annotations.SerializedName;

class Token {

    @SerializedName("access_token") private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
