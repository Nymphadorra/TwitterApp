package com.sanja.example.twitterapp.settings;


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

    public SearchQuery getSearchQuery(int itemPosition) {
        return searchQueries.get(itemPosition);
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