package com.sanja.example.twitterapp.settings;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sanja.example.twitterapp.ItemClickListener;
import com.sanja.example.twitterapp.R;
import com.sanja.example.twitterapp.app.BaseActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchQueriesActivity extends BaseActivity implements
        SearchQueriesMVP.View,
        ItemClickListener{

    private static final String LOG_TAG = "SearchQuery Activity";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_search_queries) RecyclerView rvSearchQueries;
    @BindString(R.string.error_network) String errorNetworkMessage;

    private SearchQueriesAdapter searchQueriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_queries);
        ButterKnife.bind(this);
        setupToolbar(toolbar);

        rvSearchQueries.setLayoutManager(new LinearLayoutManager(this));
        searchQueriesAdapter = new SearchQueriesAdapter(this);
        rvSearchQueries.setAdapter(searchQueriesAdapter);

        searchQueriesAdapter.addNewSearchQuery("Elon Musk");
        searchQueriesAdapter.addNewSearchQuery("Donald Trump");
        searchQueriesAdapter.addNewSearchQuery("Tucker Carlson");
        searchQueriesAdapter.addNewSearchQuery("Android");
        searchQueriesAdapter.addNewSearchQuery("Uncle Bob");
        searchQueriesAdapter.addNewSearchQuery("Edward Snowden");
        searchQueriesAdapter.addNewSearchQuery("Jake Wharton");
        searchQueriesAdapter.addNewSearchQuery("SpaceX");
        searchQueriesAdapter.addNewSearchQuery("Bethany Hamilton");
        searchQueriesAdapter.addNewSearchQuery("Jason Momoa");
        searchQueriesAdapter.addNewSearchQuery("Benjamin Rojas");
        searchQueriesAdapter.addNewSearchQuery("Johnny Depp");
    }

    @Override
    public void onItemClicked(int itemPosition) {

    }
}