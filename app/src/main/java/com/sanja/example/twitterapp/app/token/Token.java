package com.sanja.example.twitterapp.app.token;

import com.google.gson.annotations.SerializedName;

public class Token {

    @SerializedName("access_token") private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
