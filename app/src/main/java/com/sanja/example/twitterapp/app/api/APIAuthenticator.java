package com.sanja.example.twitterapp.app.api;

import com.sanja.example.twitterapp.app.token.TokenManager;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class APIAuthenticator implements Authenticator {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private final TokenManager tokenManager;

    public APIAuthenticator(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        boolean refreshTokenSuccess = tokenManager.refreshToken();

        if(refreshTokenSuccess) {
            return response.request().newBuilder()
                    .header(AUTHORIZATION, BEARER + tokenManager.getToken())
                    .build();
        } else {
            return null;
        }
    }
}