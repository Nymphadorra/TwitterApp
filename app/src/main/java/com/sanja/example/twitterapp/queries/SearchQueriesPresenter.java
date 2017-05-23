package com.sanja.example.twitterapp.queries;

import com.sanja.example.twitterapp.app.AbstractPresenter;

import java.util.List;

public class SearchQueriesPresenter extends AbstractPresenter<SearchQueriesMVP.View> implements
        SearchQueriesMVP.Presenter {

    private final SearchQueriesManager sqManager;

    public SearchQueriesPresenter(SearchQueriesManager sqManager) {
        this.sqManager = sqManager;
    }

    @Override
    public void onBind() {
        view().showSearchQueries(sqManager.getSearchQueries());
    }

    @Override
    public void onAddSearchQueryClicked() {
        view().openSaveDialogBox();
    }

    @Override
    public void onSearchQueryItemClicked(SearchQuery searchQuery) {
        view().openOptionsDialogBox(searchQuery);
    }

    @Override
    public void onUseClicked(SearchQuery searchQuery) {
        view().searchNewQuery(searchQuery);
        searchQuery.markAsSelected();
    }

    @Override
    public void onEditClicked(SearchQuery sq) {
        view().openEditDialog(sq);
    }

    @Override
    public void onDeleteClicked(SearchQuery searchQuery) {
        view().removeItem(searchQuery);
    }

    @Override
    public void onSearchQuerySaved(String searchName, String searchQuery) {
        SearchQuery newQuery = new SearchQuery(searchName, searchQuery);
        view().addSearchQueryToAdapter(newQuery);
    }

    @Override
    public void onSearchQueryEdited(String name, String query, SearchQuery searchQuery) {
        searchQuery.setSearchName(name);
        searchQuery.setSearchQuery(query);
        view().refreshSearchQueries();
    }

    @Override
    public void onViewDestroyed(List<SearchQuery> searchQueries) {
        sqManager.setSearchQueries(searchQueries);
    }

    @Override
    public void onBackPressed() {
        view().close();
    }
}