package com.sanja.example.twitterapp.settings;

public class SearchQuery {

    private String searchName;
    private String searchQuery;
    private boolean isSelected = false;

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

    public void markAsSelected(){
        this.isSelected = true;
    }

    public void unmarkAsSelected(){
        this.isSelected = false;
    }

    public boolean isSelected(){
        return this.isSelected;
    }
}
