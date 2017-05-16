package com.sanja.example.twitterapp;

import android.content.SharedPreferences;

public class TokenPreferences {

    private static final String PREF_KEY_TOKEN = "pref_key_token";

    private final SharedPreferences sharedPreferences;

    public TokenPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveToken(String token) {
        sharedPreferences.edit()
                .putString(PREF_KEY_TOKEN, token)
                .apply();
    }

    public String getToken() {
        return sharedPreferences.getString(PREF_KEY_TOKEN, "");
    }
}