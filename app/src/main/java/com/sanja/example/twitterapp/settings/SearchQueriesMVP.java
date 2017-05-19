package com.sanja.example.twitterapp.settings;

import com.sanja.example.twitterapp.app.BasePresenter;

import java.util.List;

public interface SearchQueriesMVP {
    interface View {

        void showSearchQueries(List<SearchQuery> searchQueries);

        void addSearchQueryToAdapter(SearchQuery sq);

        void openOptionsDialogBox(SearchQuery searchQuery);

        void openSaveDialogBox();

        void searchNewQuery(SearchQuery sq);

        void openEditDialog(SearchQuery searchQuery);

        void removeItem(SearchQuery searchQuery);

        void refreshSearchQueries();
    }

    interface Presenter extends BasePresenter<View> {
        void onAddSearchQueryClicked();

        void onDeleteClicked(SearchQuery searchQuery);

        void onSearchQueryItemClicked(SearchQuery searchQuery);

        void onUseClicked(SearchQuery searchQuery);

        void onSearchQuerySaved(String searchName, String searchQuery);

        void onEditClicked(SearchQuery searchQuery);

        void onSearchQueryEdited(String name, String query, SearchQuery searchQuery);

        void onViewDestroyed(List<SearchQuery> searchQueries);
    }
}