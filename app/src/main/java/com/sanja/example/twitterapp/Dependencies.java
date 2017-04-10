package com.sanja.example.twitterapp;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dependencies {

    private static final String API_BASE_URL = "https://api.twitter.com/1.1/";
    private static final String API_BASE_URL_AUTH = "https://api.twitter.com/oauth2/";

    private static Interceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return logging;
    }

    private static Interceptor provideAuthHeaderInterceptor() {
        return new AuthHeaderInterceptor();
    }

    private static OkHttpClient provideOkHttpClientAuth() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(provideLoggingInterceptor())
                .build();
        return okHttpClient;
    }

    private static OkHttpClient provideOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(provideAuthHeaderInterceptor())
                .addInterceptor(provideLoggingInterceptor())
                .build();
        return okHttpClient;
    }

    private static Retrofit createRetrofit(String baseUrl, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static APIServiceAuth createAPIServiceAuth(){
        return createRetrofit(API_BASE_URL_AUTH, provideOkHttpClientAuth())
                .create(APIServiceAuth.class);
    }

    public static APIService createAPIService(){
        return createRetrofit(API_BASE_URL, provideOkHttpClient())
                .create(APIService.class);
    }
}
