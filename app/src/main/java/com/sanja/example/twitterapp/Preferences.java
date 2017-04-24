package com.sanja.example.twitterapp;

import android.content.SharedPreferences;

public class Preferences {

    private static final String PREF_KEY_TOKEN = "pref_key_token";

    private final SharedPreferences sharedPreferences;

    public Preferences(SharedPreferences sharedPreferences) {
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