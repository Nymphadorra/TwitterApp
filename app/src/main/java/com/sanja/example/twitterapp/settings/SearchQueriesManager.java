package com.sanja.example.twitterapp.settings;


import java.util.List;

public class SearchQueriesManager {

    private List<SearchQuery> searchQueries;
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

    public void setSearchQueries(List<SearchQuery> searchQueries) {
        this.searchQueries = searchQueries;
        saveToPreferences();
    }

    private void saveToPreferences() {
        sqPreferences.save(searchQueries);
    }
}