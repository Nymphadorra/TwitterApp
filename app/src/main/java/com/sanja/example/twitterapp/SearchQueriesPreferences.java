package com.sanja.example.twitterapp;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sanja.example.twitterapp.settings.SearchQuery;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchQueriesPreferences {

    private static final String PREF_KEY_SEARCH_QUERIES_LIST = "pref_key_search_queries_list";

    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    public SearchQueriesPreferences(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public void save(List<SearchQuery> searchQueries) {
        String searchQueriesJsonString = gson.toJson(searchQueries);
        sharedPreferences.edit().putString(PREF_KEY_SEARCH_QUERIES_LIST, searchQueriesJsonString).apply();
    }

    public List<SearchQuery> get() {
        String searchQueriesJson = sharedPreferences.getString(PREF_KEY_SEARCH_QUERIES_LIST, "[]");
        Type listType = new TypeToken<ArrayList<SearchQuery>>(){}.getType();
        return gson.fromJson(searchQueriesJson, listType);
    }
}