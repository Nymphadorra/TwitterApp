package com.sanja.example.twitterapp.settings;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sanja.example.twitterapp.ItemClickListener;
import com.sanja.example.twitterapp.R;
import com.sanja.example.twitterapp.app.BaseActivity;
import com.sanja.example.twitterapp.di.components.AppComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class SearchQueriesActivity extends BaseActivity implements
        SearchQueriesMVP.View,
        ItemClickListener,
        OnStartDragListener {

    private static final String EXTRA_SEARCH_QUERY = "search_query";

    @Inject SearchQueriesMVP.Presenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_search_queries) RecyclerView rvSearchQueries;
    @BindString(R.string.error_network) String errorNetworkMessage;
    @BindString(R.string.dialog_save_title) String saveDialogTitle;
    @BindString(R.string.dialog_edit_title) String editDialogTitle;

    private SearchQueriesAdapter searchQueriesAdapter;
    private ItemTouchHelper itemTouchHelper;
    private View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_queries);
        ButterKnife.bind(this);
        setupToolbar(toolbar);

        rvSearchQueries.setLayoutManager(new LinearLayoutManager(this));
        searchQueriesAdapter = new SearchQueriesAdapter(this, this);
        rvSearchQueries.setAdapter(searchQueriesAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(searchQueriesAdapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rvSearchQueries);

        presenter.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDestroyed(searchQueriesAdapter.getSearchQueries());
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        SettingsComponent settingsComponent = DaggerSettingsComponent.builder()
                .appComponent(appComponent)
                .build();
        settingsComponent.inject(this);
    }

    @Override
    public void onItemClicked(int itemPosition) {
        presenter.onSearchQueryItemClicked(searchQueriesAdapter.getItem(itemPosition));
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void showSearchQueries(List<SearchQuery> searchQueries) {
        searchQueriesAdapter.refreshSearchQueries(searchQueries);
    }

    @Override
    public void addSearchQueryToAdapter(SearchQuery sq) {
        searchQueriesAdapter.addItem(sq);
    }

    @Override
    public void openOptionsDialogBox(final SearchQuery searchQuery) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SearchQueriesActivity.this);
        builder.setTitle(R.string.dialog_options_title)
                .setItems(R.array.dialog_options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                presenter.onUseClicked(searchQuery);
                                break;
                            case 1:
                                presenter.onEditClicked(searchQuery);
                                break;
                            case 2:
                                presenter.onDeleteClicked(searchQuery);
                        }
                    }
                });
        builder.create().show();
    }

    @Override
    public void openSaveDialogBox() {
        AlertDialog.Builder builder = createDialogBuilder(this, saveDialogTitle);
        final EditText etSearchName = (EditText) dialogView.findViewById(R.id.et_search_name);
        final EditText etSearchQuery = (EditText) dialogView.findViewById(R.id.et_search_query);
        builder.setPositiveButton(R.string.save_item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String searchName = etSearchName.getText().toString().trim();
                String searchQuery = etSearchQuery.getText().toString().trim();
                presenter.onSearchQuerySaved(searchName, searchQuery);
                showToast("Saved");
                Timber.i("Saved new search query %s with name %s", searchQuery, searchName);
            }
        });
        builder.create().show();
    }

    @Override
    public void searchNewQuery(SearchQuery sq) {
        Intent i = new Intent();
        i.putExtra(EXTRA_SEARCH_QUERY, sq.getSearchQuery());
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    @Override
    public void openEditDialog(final SearchQuery sq) {
        AlertDialog.Builder builder = createDialogBuilder(this, editDialogTitle);
        final EditText etSearchName = (EditText) dialogView.findViewById(R.id.et_search_name);
        final EditText etSearchQuery = (EditText) dialogView.findViewById(R.id.et_search_query);
        etSearchName.setText(sq.getSearchName(), TextView.BufferType.EDITABLE);
        etSearchQuery.setText(sq.getSearchQuery(), TextView.BufferType.EDITABLE);
        builder.setPositiveButton(R.string.save_item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String searchName = etSearchName.getText().toString().trim();
                String searchQuery = etSearchQuery.getText().toString().trim();
                presenter.onSearchQueryEdited(searchName, searchQuery, sq);
                searchQueriesAdapter.notifyDataSetChanged();
                showToast("Saved");
                Timber.i("Saved new search query %s with name %s", searchQuery, searchName);
            }
        });
        builder.create().show();
    }

    @Override
    public void removeItem(SearchQuery searchQuery) {
        searchQueriesAdapter.removeItem(searchQuery);
    }

    @Override
    public void refreshSearchQueries() {
        searchQueriesAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.fab_add_search_query)
    public void addSearchQuery() {
        presenter.onAddSearchQueryClicked();
    }

    private AlertDialog.Builder createDialogBuilder(Context context, String dialogTitle){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_search_query, null);
        dialogView = view;
        builder.setTitle(dialogTitle)
                .setView(view)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        return builder;
    }
}