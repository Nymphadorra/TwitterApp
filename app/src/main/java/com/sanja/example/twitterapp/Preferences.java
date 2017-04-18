package com.sanja.example.twitterapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {

    private static final String PREF_KEY_TOKEN = "pref_key_token";

    private SharedPreferences sharedPreferences;

    public volatile static Preferences uniqueInstance;

    private Preferences(){}

    public static Preferences getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new Preferences();
        }
        return uniqueInstance;
    }

    public void initialize(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
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