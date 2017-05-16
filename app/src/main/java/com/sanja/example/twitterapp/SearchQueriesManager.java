package com.sanja.example.twitterapp;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sanja.example.twitterapp.settings.SearchQuery;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchQueriesManager {

    private final List<SearchQuery> searchQueries;
    private final SearchQueriesPreferences sqPreferences;

    public SearchQueriesManager(SearchQueriesPreferences sqPreferences) {
        this.sqPreferences = sqPreferences;
        searchQueries = sqPreferences.get();
        if (searchQueries.isEmpty()) {
            searchQueries.add(new SearchQuery("Android", "Android"));
            saveToPreferences();
        }
    }

    public List<SearchQuery> getSearchQueries() {
        return searchQueries;
    }

    public void addSearchQuery(SearchQuery sq) {
        searchQueries.add(sq);
        saveToPreferences();
    }

    public void removeSearchQuery(int position) {
        searchQueries.remove(position);
        saveToPreferences();
    }

    private void saveToPreferences() {
        sqPreferences.save(searchQueries);
    }
}