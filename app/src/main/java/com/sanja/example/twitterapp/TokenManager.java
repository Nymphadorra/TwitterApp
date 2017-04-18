package com.sanja.example.twitterapp;

import java.io.IOException;

import retrofit2.Response;

public class TokenManager {

    private final APIServiceAuth apiServiceAuth;
    private Preferences preferences = Preferences.getInstance(); // TODO: DI through constructor?

    public TokenManager(APIServiceAuth apiServiceAuth) {
        this.apiServiceAuth = apiServiceAuth;
        this.token = preferences.getToken();
    }

    private String token;

    public String getToken() {
        return token;
    }

    public boolean refreshToken() {
        try {
            Response<Token> response = apiServiceAuth.createToken("client_credentials").execute();
            String accessToken = response.body().getAccessToken();
            this.token = accessToken;
            preferences.saveToken(accessToken);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}