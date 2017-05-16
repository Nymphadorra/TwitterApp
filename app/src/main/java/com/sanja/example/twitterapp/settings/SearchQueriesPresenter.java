package com.sanja.example.twitterapp.settings;

import com.sanja.example.twitterapp.SearchQueriesManager;
import com.sanja.example.twitterapp.app.AbstractPresenter;

public class SearchQueriesPresenter extends AbstractPresenter<SearchQueriesMVP.View> implements
        SearchQueriesMVP.Presenter {

    private final SearchQueriesManager sqManager;

    public SearchQueriesPresenter(SearchQueriesManager sqManager) {
        this.sqManager = sqManager;
    }

    @Override
    public void onViewVisible() {
        view().showSearchQueries(sqManager.getSearchQueries());
    }

    @Override
    public void onAddSearchQueryClicked() {

        SearchQuery newQuery = new SearchQuery("Android", "PikiliAndroid");
        sqManager.addSearchQuery(newQuery);
        view().addSearchQueryToAdapter(newQuery);
    }

    @Override
    public void onSearchQueryRemoved(int position) {
        sqManager.removeSearchQuery(position);
    }
}