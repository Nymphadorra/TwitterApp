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

    private static final String DEFAULT_SEARCH_QUERY = "Elon Musk";
    private final APIService apiService;

    public HomePresenter(APIService apiService) {
        this.apiService = apiService;
    }

    @Override
    protected void onBind() {
        search(DEFAULT_SEARCH_QUERY);
    }

    private void search(String searchInput) {
        apiService.searchTweets(searchInput).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if(response.isSuccessful()) {
                    handleSearchSuccess(response.body().getTweets());
                } else {
                    // Show default error message or connection lost?
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // Show default error message or connection lost.
            }
        });
    }

    private void handleSearchSuccess(List<Tweet> tweets) {
        view().showTweets(tweets);
    }

    @Override
    public void onAutoScrollClicked() {

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

    }
}