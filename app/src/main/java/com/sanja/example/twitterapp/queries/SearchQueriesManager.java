package com.sanja.example.twitterapp.queries;


import java.util.List;

public class SearchQueriesManager {

    private static String DEFAULT_SEARCH_QUERY_NAME = "Android";
    private static String DEFAULT_SEARCH_QUERY = "Android";

    private List<SearchQuery> searchQueries;
    private final SearchQueriesPreferences sqPreferences;

    public SearchQueriesManager(SearchQueriesPreferences sqPreferences) {
        this.sqPreferences = sqPreferences;
        searchQueries = sqPreferences.get();
        if (searchQueries.isEmpty()) {
            SearchQuery sq = new SearchQuery(DEFAULT_SEARCH_QUERY_NAME, DEFAULT_SEARCH_QUERY);
            searchQueries.add(sq);
            sq.markAsSelected();
            saveToPreferences();
        }
    }

    public List<SearchQuery> getSearchQueries() {
        return searchQueries;
    }

    public SearchQuery getFirstSelectedSQ(){
        SearchQuery selectedSQ = searchQueries.get(0);
        for (int i = 0; i <searchQueries.size(); i++) {
            if (searchQueries.get(i).isSelected()){
                selectedSQ = searchQueries.get(i);
                break;
            }
        }
        selectedSQ.markAsSelected();
        return selectedSQ;
    }

    public void setSearchQueries(List<SearchQuery> searchQueries) {
        this.searchQueries = searchQueries;
        saveToPreferences();
    }

    private void saveToPreferences() {
        sqPreferences.save(searchQueries);
    }
}