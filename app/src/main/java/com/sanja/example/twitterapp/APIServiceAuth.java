package com.sanja.example.twitterapp;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIServiceAuth {

    @Headers("Authorization: Basic QnNJMDNnSzhka2pEU0ZNZktqWmpINW52VTp1d1BMT280MTBzcGhoVDNSZVRCekF3eGdKUWZWOWIwVjl1a0R6SEpuOWxORzdpZXY0dA==")
    @POST("token")
    Call<Token> createToken(
            @Query("grant_type") String grantType
    );
}