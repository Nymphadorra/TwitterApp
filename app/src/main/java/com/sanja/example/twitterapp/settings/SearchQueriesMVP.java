package com.sanja.example.twitterapp.settings;

import android.app.Dialog;

import com.sanja.example.twitterapp.app.BasePresenter;

import java.util.List;

public interface SearchQueriesMVP {
    interface View {
        void showSearchQueries(List<SearchQuery> searchQueries);

        void addSearchQueryToAdapter(SearchQuery sq);

        void openOptionsDialogBox(int itemPosition);

        void openSaveDialogBox();

        void searchNewQuery(SearchQuery sq);

        void openEditDialog(String searchName, String searchQuery, int itemPosition);
    }

    interface Presenter extends BasePresenter<View> {
        void onAddSearchQueryClicked();

        void onDeleteClicked(int position);

        void onSearchQueryItemClicked(int itemPosition);

        void onUseClicked(int itemPosition);

        void onSearchQuerySaved(String searchName, String searchQuery);

        void onEditClicked(int itemPosition);

        void onSearchQueryEdited(String searchName, String searchQuery, int itemPosition);
    }
}