package com.sanja.example.twitterapp;

import com.sanja.example.twitterapp.app.api.APIServiceAuth;

import java.io.IOException;

import retrofit2.Response;

public class TokenManager {

    private static final String CLIENT_CREDENTIALS = "client_credentials";

    private final APIServiceAuth apiServiceAuth;
    private final TokenPreferences tokenPreferences;

    public TokenManager(APIServiceAuth apiServiceAuth, TokenPreferences tokenPreferences) {
        this.apiServiceAuth = apiServiceAuth;
        this.tokenPreferences = tokenPreferences;
        this.token = tokenPreferences.getToken();
    }

    private String token;

    public String getToken() {
        return token;
    }

    public boolean refreshToken() {
        try {
            Response<Token> response = apiServiceAuth.createToken(CLIENT_CREDENTIALS).execute();
            String accessToken = response.body().getAccessToken();
            this.token = accessToken;
            tokenPreferences.saveToken(accessToken);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}