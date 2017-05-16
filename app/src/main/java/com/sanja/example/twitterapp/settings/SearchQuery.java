package com.sanja.example.twitterapp.settings;

public class SearchQuery {

    private String searchName;
    private String searchQuery;

    public SearchQuery(String searchName, String searchQuery) {
        this.searchName = searchName;
        this.searchQuery = searchQuery;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
