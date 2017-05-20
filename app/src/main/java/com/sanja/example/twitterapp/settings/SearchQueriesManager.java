package com.sanja.example.twitterapp.settings;


import java.util.List;

public class SearchQueriesManager {

    private List<SearchQuery> searchQueries;
    private final SearchQueriesPreferences sqPreferences;
    private SearchQuery selectedSQ;

    public SearchQueriesManager(SearchQueriesPreferences sqPreferences) {
        this.sqPreferences = sqPreferences;
        searchQueries = sqPreferences.get();
        if (searchQueries.isEmpty()) {
            SearchQuery sq = new SearchQuery("Android", "Android");
            searchQueries.add(sq);
            sq.markAsSelected();
            saveToPreferences();
        }
    }

    public List<SearchQuery> getSearchQueries() {
        return searchQueries;
    }

    public SearchQuery getFirstSearchQuery() {
        return searchQueries.get(0);
    }

    public void setSearchQueries(List<SearchQuery> searchQueries) {
        this.searchQueries = searchQueries;
        saveToPreferences();
    }

    private void saveToPreferences() {
        sqPreferences.save(searchQueries);
    }
}