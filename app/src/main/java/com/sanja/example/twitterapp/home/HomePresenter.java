package com.sanja.example.twitterapp.home;

import com.sanja.example.twitterapp.app.api.APIService;
import com.sanja.example.twitterapp.app.AbstractPresenter;
import com.sanja.example.twitterapp.SearchResponse;
import com.sanja.example.twitterapp.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter extends AbstractPresenter<HomeMvp.View> implements HomeMvp.Presenter {

    private static final int SEARCH_COUNT = 10;
    private static final int LIST_AUTO_SCROLL_DELAY = 10000;
    private static final int PAGER_AUTO_SCROLL_DELAY = 7000;
    private static final String DEFAULT_SEARCH_QUERY = "Elon Musk";

    private final APIService apiService;

    private boolean isLoadingInProgress = false;
    private boolean hasLoadedAllItems = false;
    private String nextResults;

    public HomePresenter(APIService apiService) {
        this.apiService = apiService;
    }

    @Override
    protected void onBind() {
        search(DEFAULT_SEARCH_QUERY);
    }

    @Override
    public void onListAutoScrollClicked() {
       view().startListAutoScroll(LIST_AUTO_SCROLL_DELAY);
    }

    @Override
    public void onPagerAutoScrollClicked() {
        view().startPagerAutoScroll(PAGER_AUTO_SCROLL_DELAY);
    }

    @Override
    public void onListLayoutClicked() {
        view().showListLayout();
    }

    @Override
    public void onPagerLayoutClicked() {
        view().showPagerLayout();
    }

    @Override
    public void onSettingsClicked() {
        view().startSettingsActivity();
    }

    @Override
    public void onPullToRefresh() {
        search(DEFAULT_SEARCH_QUERY);
        view().setCurrentTweetPosition(0);
    }

    @Override
    public void onReconnectClicked() {
        search(DEFAULT_SEARCH_QUERY);
    }

    @Override
    public void onLoadMoreTweets() {
        searchMoreTweets(nextResults);
    }

    @Override
    public boolean isLoadingInProgress() {
        return isLoadingInProgress;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return hasLoadedAllItems;
    }

    private void search(String searchInput) {
        apiService.searchTweets(searchInput, SEARCH_COUNT).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    setNextResults(response.body().getSearchMetaData().getNextResults());
                    handleSearchSuccess(response.body().getTweets());
                } else {
                    handleSearchFailure();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                handleSearchFailure();
            }
        });
    }

    private void searchMoreTweets(String nextResults) {
        isLoadingInProgress = true;
        apiService.searchMoreTweets(nextResults).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                isLoadingInProgress = false;
                if (response.body().getTweets() != null) {
                    setNextResults(response.body().getSearchMetaData().getNextResults());
                    view().showMoreTweets(response.body().getTweets());
                } else {
                    view().showEmptyResponse();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                handleSearchFailure();
                isLoadingInProgress = false;
            }
        });
    }

    private void handleSearchSuccess(List<Tweet> tweets) {
        view().showTweets(tweets);
        view().showListLayout();
    }

    private void handleSearchFailure() {
        isLoadingInProgress = false;
        view().showNetworkError();
    }

    private void setNextResults(String nextResults) {
        this.nextResults = "search/tweets.json" + nextResults;
    }
}