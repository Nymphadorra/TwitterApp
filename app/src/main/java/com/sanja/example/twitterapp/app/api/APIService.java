package com.sanja.example.twitterapp.app.api;

import com.sanja.example.twitterapp.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIService {

    @GET("search/tweets.json")
    Call<SearchResponse> searchTweets(
            @Query("q") String query,
            @Query("count") int searchCount);

    @GET
    Call<SearchResponse> searchMoreTweets (
            @Url String url);
}