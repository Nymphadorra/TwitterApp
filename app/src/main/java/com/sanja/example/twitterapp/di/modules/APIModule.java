package com.sanja.example.twitterapp.di.modules;

import android.content.Context;

import com.sanja.example.twitterapp.app.api.APIAuthenticator;
import com.sanja.example.twitterapp.app.api.APIConstants;
import com.sanja.example.twitterapp.app.api.APIService;
import com.sanja.example.twitterapp.app.api.APIServiceAuth;
import com.sanja.example.twitterapp.app.api.AuthHeaderInterceptor;
import com.sanja.example.twitterapp.TokenPreferences;
import com.sanja.example.twitterapp.TokenManager;
import com.sanja.example.twitterapp.di.AppScope;
import com.sanja.example.twitterapp.di.qualifiers.Auth;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class APIModule {

    @AppScope
    @Provides
    public APIService provideAPIService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }

    @AppScope
    @Provides
    public APIServiceAuth provideAPIServiceAuth(@Auth Retrofit retrofit) {
        return retrofit.create(APIServiceAuth.class);
    }

    @AppScope
    @Provides
    public Retrofit provideRetrofit(HttpUrl baseUrl, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Auth
    @AppScope
    @Provides
    public Retrofit provideRetrofitAuth(@Auth HttpUrl baseUrl, @Auth OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @AppScope
    @Provides
    public HttpUrl provideBaseUrl() {
        return HttpUrl.parse(APIConstants.API_BASE_URL);
    }

    @Auth
    @AppScope
    @Provides
    public HttpUrl provideAuthBaseUrl() {
        return HttpUrl.parse(APIConstants.API_BASE_URL_AUTH);
    }

    @AppScope
    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
                                            AuthHeaderInterceptor authHeaderInterceptor,
                                            APIAuthenticator apiAuthenticator) {
        return new OkHttpClient.Builder()
                .addInterceptor(authHeaderInterceptor)
                .authenticator(apiAuthenticator)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Auth
    @AppScope
    @Provides
    public OkHttpClient provideOkHttpClientAuth(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @AppScope
    @Provides
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return logging;
    }

    @AppScope
    @Provides
    public AuthHeaderInterceptor provideAuthHeaderInterceptor(TokenManager tokenManager) {
        return new AuthHeaderInterceptor(tokenManager);
    }

    @AppScope
    @Provides
    public TokenManager provideTokenManager(APIServiceAuth apiServiceAuth, TokenPreferences tokenPreferences) {
        return new TokenManager(apiServiceAuth, tokenPreferences);
    }

    @Provides
    public APIAuthenticator provideApiAuthenticator(TokenManager tokenManager) {
        return new APIAuthenticator(tokenManager);
    }

    @AppScope
    @Provides
    public Picasso providePicasso(Context context) {
        return Picasso.with(context);
    }
}