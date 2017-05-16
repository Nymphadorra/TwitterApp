package com.sanja.example.twitterapp.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewAnimator;

import com.paginate.Paginate;
import com.sanja.example.twitterapp.LinearLayoutManagerWithSmoothScroller;
import com.sanja.example.twitterapp.app.BaseActivity;
import com.sanja.example.twitterapp.ItemClickListener;
import com.sanja.example.twitterapp.R;
import com.sanja.example.twitterapp.Tweet;
import com.sanja.example.twitterapp.app.ui.ViewAnimatorById;
import com.sanja.example.twitterapp.app.utils.PageSelectedListener;
import com.sanja.example.twitterapp.di.components.AppComponent;
import com.sanja.example.twitterapp.di.components.AppComponentContainer;
import com.sanja.example.twitterapp.settings.SearchQueriesActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements
        HomeMvp.View,
        ItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private static final String LOG_TAG = "Main Activity";

    @Inject HomeMvp.Presenter presenter;
    @Inject Picasso picasso;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_tweets) RecyclerView rvTweets;
    @BindView(R.id.view_animator) ViewAnimatorById viewAnimator;
    @BindView(R.id.vp_tweets) ViewPager viewPager;
    @BindView(R.id.swipeContainer) SwipeRefreshLayout swipeRefreshLayout;

    @BindString(R.string.error_network) String errorNetworkMessage;

    private boolean listActive = true; // TODO Rename. Unclear reference!
    private boolean isPaginationAlreadySet = false;
    private boolean isListAutoScrollOn = false;
    private boolean isPagerAutoScrollOn = false;
    private TweetsRecyclerAdapter tweetsRecyclerAdapter;
    private TweetsPagerAdapter tweetsPagerAdapter;
    private MenuItem listAutoScroll;
    private MenuItem pagerAutoScroll;
    private Handler handler;
    private int currentTweetPosition = 0;
    private int pagerAutoScrollPosition = currentTweetPosition + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter.bind(this);
        setupToolbar(toolbar);

        setViewAnimatorAnimations(this, viewAnimator);

        rvTweets.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(this));
        tweetsRecyclerAdapter = new TweetsRecyclerAdapter(this, this, picasso);
        rvTweets.setAdapter(tweetsRecyclerAdapter);

        tweetsPagerAdapter = new TweetsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tweetsPagerAdapter);
        viewPager.setPageMargin(20);
        viewPager.addOnPageChangeListener(new PageSelectedListener() {
            @Override
            public void onPageSelected(int position) {
                currentTweetPosition = position;
                pagerAutoScrollPosition = position + 1;
                if (shouldLoadMoreTweets(position)) {
                    presenter.onLoadMoreTweets();
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onItemClicked(int itemPosition) {

    }

    @Override
    public void onRefresh() {
        presenter.onPullToRefresh();
        onItemsLoadComplete();
    }

    @Override
    public void showTweets(List<Tweet> tweets) {
        tweetsRecyclerAdapter.refreshTweets(tweets);
        tweetsPagerAdapter.refreshTweets(tweets);
        setupTweetsPagination(isPaginationAlreadySet);
        handler = new Handler(); // Todo: put this maybe in onCreate?
    }

    @Override
    public void showMoreTweets(List<Tweet> tweets) {
        tweetsRecyclerAdapter.addMoreTweets(tweets);
        tweetsPagerAdapter.addMoreTweets(tweets);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        listAutoScroll = menu.findItem(R.id.menu_item_list_auto_scroll);
        pagerAutoScroll = menu.findItem(R.id.menu_item_pager_auto_scroll);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_item_list_layout).setVisible(!listActive);
        menu.findItem(R.id.menu_item_pager_layout).setVisible(listActive);
        menu.findItem(R.id.menu_item_list_auto_scroll).setVisible(listActive);
        menu.findItem(R.id.menu_item_pager_auto_scroll).setVisible(!listActive);
        refreshAutoScroll();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.menu_item_list_auto_scroll):
                invalidateOptionsMenu();
                if (!isListAutoScrollOn) {
                    isListAutoScrollOn = true;
                } else {
                    stopListAutoScroll();
                }
                presenter.onListAutoScrollClicked();
                return true;
            case (R.id.menu_item_pager_auto_scroll):
                invalidateOptionsMenu();
                if (!isPagerAutoScrollOn) {
                    isPagerAutoScrollOn = true;
                } else {
                    stopPagerAutoScroll();
                }
                presenter.onPagerAutoScrollClicked();
                return true;
            case (R.id.menu_item_list_layout):
                listActive = true;
                invalidateOptionsMenu();
                isPagerAutoScrollOn = false;
                presenter.onListLayoutClicked();
                return true;
            case (R.id.menu_item_pager_layout):
                listActive = false;
                invalidateOptionsMenu();
                isListAutoScrollOn = false;
                presenter.onPagerLayoutClicked();
                return true;
            case (R.id.menu_item_settings):
                presenter.onSettingsClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showListLayout() {
        scrollToTopInListLayout(rvTweets, currentTweetPosition);
        viewAnimator.setChildById(R.id.swipeContainer);
    }

    @Override
    public void setCurrentTweetPosition(int position) {
        this.currentTweetPosition = position;
    }

    @Override
    public void showPagerLayout() {
        setFirstVisibleItemInPagerLayout(viewPager, rvTweets);
        viewAnimator.setChildById(R.id.vp_tweets);
    }

    @Override
    public void showNetworkError() {
        viewAnimator.setChildById(R.id.ll_network_error);
        showToast(errorNetworkMessage);
    }

    @Override
    public void showEmptyResponse() {
        viewAnimator.setChildById(R.id.empty_response);
    }

    @OnClick(R.id.btn_try_to_reconnect)
    public void reconnect() {
        presenter.onReconnectClicked();
    }

    @Override
    public void startListAutoScroll(final int listDelayInMilliseconds) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (isListAutoScrollOn) {
                    LinearLayoutManagerWithSmoothScroller llm = (LinearLayoutManagerWithSmoothScroller) rvTweets.getLayoutManager();
                    rvTweets.smoothScrollToPosition(llm.findLastVisibleItemPosition());
                    handler.postDelayed(this, listDelayInMilliseconds);
                } else {
                    handler.removeCallbacks(this);
                    Log.i(LOG_TAG, "List Runnable terminated!");
                }
            }
        };
        handler.postDelayed(runnable, listDelayInMilliseconds);
    }

    @Override
    public void startPagerAutoScroll(final int pagerDelayInMilliseconds) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (isPagerAutoScrollOn) {
                    viewPager.setCurrentItem(pagerAutoScrollPosition, true);
                    handler.postDelayed(this, pagerDelayInMilliseconds);
                    Log.i(LOG_TAG, "Current position: " + currentTweetPosition + "\t PagerItem position: " + pagerAutoScrollPosition);
                } else {
                    handler.removeCallbacks(this);
                    Log.i(LOG_TAG, "Pager Runnable terminated!");
                }
            }
        };
        handler.postDelayed(runnable, pagerDelayInMilliseconds);
    }

    @Override
    public void stopListAutoScroll() {
        isListAutoScrollOn = false;
    }

    @Override
    public void stopPagerAutoScroll() {
        isPagerAutoScrollOn = false;
    }

    @Override
    public void startSettingsActivity() {
        startActivity(new Intent(this, SearchQueriesActivity.class));
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        HomeComponent homeComponent = DaggerHomeComponent.builder()
                .appComponent(appComponent)
                .build();
        homeComponent.inject(this);
    }

    private void setupTweetsPagination(boolean isPaginationAlreadySet) {
        if (!isPaginationAlreadySet) {
            this.isPaginationAlreadySet = true;
            Paginate.with(rvTweets, callbacks)
                    .setLoadingTriggerThreshold(2)
                    .addLoadingListItem(true)
                    .build();
        }
    }

    Paginate.Callbacks callbacks = new Paginate.Callbacks() {
        @Override
        public void onLoadMore() {
            presenter.onLoadMoreTweets();
        }

        @Override
        public boolean isLoading() {
            // Indicate whether new page loading is in progress or not
            return presenter.isLoadingInProgress();
        }

        @Override
        public boolean hasLoadedAllItems() {
            // Indicate whether all data (pages) are loaded or not
            return presenter.hasLoadedAllItems();
        }
    };

    private void setViewAnimatorAnimations(Context context, ViewAnimator va) {
        Animation in = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
        va.setInAnimation(in);
        va.setOutAnimation(out);
    }

    private void scrollToTopInListLayout(RecyclerView rv, int currentItemPosition) {
        LinearLayoutManagerWithSmoothScroller llm = (LinearLayoutManagerWithSmoothScroller) rv.getLayoutManager();
        llm.scrollToPositionWithOffset(currentItemPosition, 0);
    }

    private void setFirstVisibleItemInPagerLayout(ViewPager vp, RecyclerView rv) {
        LinearLayoutManagerWithSmoothScroller llm = (LinearLayoutManagerWithSmoothScroller) rv.getLayoutManager();
        vp.setCurrentItem(llm.findFirstVisibleItemPosition(), true);
    }

    private void onItemsLoadComplete() {
        swipeRefreshLayout.setRefreshing(false);
    }

    private boolean shouldLoadMoreTweets(int position) {
        return position == viewPager.getAdapter().getCount() - 3;
    }

    private void refreshAutoScroll() {
        if (isListAutoScrollOn) {
            listAutoScroll.setIcon(R.drawable.ic_auto_scroll_on);
        } else {
            listAutoScroll.setIcon(R.drawable.ic_auto_scroll_off);
        }

        if (isPagerAutoScrollOn) {
            pagerAutoScroll.setIcon(R.drawable.ic_auto_scroll_on);
        } else {
            pagerAutoScroll.setIcon(R.drawable.ic_auto_scroll_off);
        }
    }
}