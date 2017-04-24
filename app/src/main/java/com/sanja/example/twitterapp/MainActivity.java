package com.sanja.example.twitterapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.paginate.Paginate;
import com.sanja.example.twitterapp.di.components.AppComponentContainer;
import com.sanja.example.twitterapp.home.DaggerHomeComponent;
import com.sanja.example.twitterapp.home.HomeComponent;
import com.sanja.example.twitterapp.home.HomeMvp;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements
        HomeMvp.View {

    @Inject HomeMvp.Presenter presenter;
    @Inject Picasso picasso;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_tweets) RecyclerView rvTweets;
    @BindView(R.id.iv_auto_scroll) ImageView ivAutoScroll;
    @BindView(R.id.iv_list_layout) ImageView ivListLayout;
    @BindView(R.id.iv_pager_layout) ImageView ivPagerLayout;
    @BindView(R.id.iv_settings) ImageView ivSettings;

    private TweetsAdapter tweetsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        injectDependencies();
        setupToolbar(toolbar);
        presenter.bind(this);

        rvTweets.setLayoutManager(new LinearLayoutManager(this));
        tweetsAdapter = new TweetsAdapter(this, tweetListener, picasso);
        rvTweets.setAdapter(tweetsAdapter);
    }

    @Override
    public void showTweets(List<Tweet> tweets) {
        tweetsAdapter.refreshTweets(tweets);
        setupTweetsPagination();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.menu_item_auto_scroll):
                // presenter.onAutoScrollClicked();
                return true;
            case(R.id.menu_item_list_layout):
                // presenter.onListLayoutClicked();
                return true;
            case(R.id.menu_item_pager_layout):
                // presenter.onPagerLayoutClicked();
                return true;
            case(R.id.menu_item_settings):
                // presenter.onSettingsClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void injectDependencies() {
        HomeComponent homeComponent = DaggerHomeComponent.builder()
                .appComponent(AppComponentContainer.get())
                .build();
        homeComponent.inject(this);
    }

    private TweetsAdapter.ItemClickListener tweetListener = new TweetsAdapter.ItemClickListener() {
        @Override
        public void onTweetItemClicked(int tweetPosition) {
        }
    };

    private void setupTweetsPagination() {
        Paginate.with(rvTweets, callbacks)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .build();
    }

    Paginate.Callbacks callbacks = new Paginate.Callbacks() {
        @Override
        public void onLoadMore() {

        }

        @Override
        public boolean isLoading() {
            // Indicate whether new page loading is in progress or not
            return false;
        }

        @Override
        public boolean hasLoadedAllItems() {
            // Indicate whether all data (pages) are loaded or not
            return true;
        }
    };
}