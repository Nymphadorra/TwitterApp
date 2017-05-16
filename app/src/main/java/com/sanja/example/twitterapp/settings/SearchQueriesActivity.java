package com.sanja.example.twitterapp.settings;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.sanja.example.twitterapp.ItemClickListener;
import com.sanja.example.twitterapp.OnStartDragListener;
import com.sanja.example.twitterapp.R;
import com.sanja.example.twitterapp.SimpleItemTouchHelperCallback;
import com.sanja.example.twitterapp.app.BaseActivity;
import com.sanja.example.twitterapp.di.components.AppComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchQueriesActivity extends BaseActivity implements
        SearchQueriesMVP.View,
        ItemClickListener,
        OnStartDragListener,
        SearchQueriesAdapter.ItemRemoveListener{

    private static final String LOG_TAG = "SearchQuery Activity";

    @Inject SearchQueriesMVP.Presenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_search_queries) RecyclerView rvSearchQueries;
    @BindString(R.string.error_network) String errorNetworkMessage;

    private SearchQueriesAdapter searchQueriesAdapter;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_queries);
        ButterKnife.bind(this);
        presenter.bind(this);
        setupToolbar(toolbar);

        rvSearchQueries.setLayoutManager(new LinearLayoutManager(this));
        searchQueriesAdapter = new SearchQueriesAdapter(this, this);
        rvSearchQueries.setAdapter(searchQueriesAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(searchQueriesAdapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rvSearchQueries);

        presenter.onViewVisible();
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
        // open a dialog box for use, edit, and delete item
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

    @OnClick(R.id.fab_add_search_query)
    public void addSearchQuery(){
        presenter.onAddSearchQueryClicked();
        Log.i(LOG_TAG, "Item Added.");
    }

    @Override
    public void onItemRemoved(int position) {
        presenter.onSearchQueryRemoved(position);
    }
}