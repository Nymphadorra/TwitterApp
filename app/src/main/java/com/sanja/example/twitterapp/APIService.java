package com.sanja.example.twitterapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface APIService {

    @GET("search/tweets.json")
    Call<SearchResponse> searchTweets(
            @Query("q") String query,
            @Header("Authorization") String bearerToken
            );
}