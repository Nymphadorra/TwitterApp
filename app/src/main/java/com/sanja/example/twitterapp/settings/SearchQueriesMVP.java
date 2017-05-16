package com.sanja.example.twitterapp.settings;

import com.sanja.example.twitterapp.app.BasePresenter;

import java.util.List;

public interface SearchQueriesMVP {
    interface View {
        void showSearchQueries(List<SearchQuery> searchQueries);

        void addSearchQueryToAdapter(SearchQuery sq);
    }

    interface Presenter extends BasePresenter<View> {
        void onViewVisible();

        void onAddSearchQueryClicked();

        void onSearchQueryRemoved(int position);
    }
}