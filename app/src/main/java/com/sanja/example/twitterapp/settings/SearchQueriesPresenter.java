package com.sanja.example.twitterapp.settings;

import android.widget.EditText;

import com.sanja.example.twitterapp.app.AbstractPresenter;

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
    public void onDeleteClicked(int position) {
        sqManager.removeSearchQuery(position);
        view().showSearchQueries(sqManager.getSearchQueries());
    }

    @Override
    public void onSearchQueryItemClicked(int itemPosition) {
        view().openOptionsDialogBox(itemPosition);
    }

    @Override
    public void onUseClicked(int itemPosition) {
        view().searchNewQuery(sqManager.getSearchQuery(itemPosition));
    }

    @Override
    public void onSearchQuerySaved(String searchName, String searchQuery) {
        SearchQuery newQuery = new SearchQuery(searchName, searchQuery);
        sqManager.addSearchQuery(newQuery);
        view().addSearchQueryToAdapter(newQuery);
    }

    @Override
    public void onEditClicked(int itemPosition) {
        String searchName = sqManager.getSearchQuery(itemPosition).getSearchName();
        String searchQuery = sqManager.getSearchQuery(itemPosition).getSearchQuery();
        view().openEditDialog(searchName, searchQuery, itemPosition);
    }

    @Override
    public void onSearchQueryEdited(String searchName, String searchQuery, int itemPosition) {
        SearchQuery sq = sqManager.getSearchQuery(itemPosition);
        sq.setSearchName(searchName);
        sq.setSearchQuery(searchQuery);
    }
}