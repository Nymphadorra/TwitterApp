package com.sanja.example.twitterapp.app.api;

import com.sanja.example.twitterapp.app.token.TokenManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthHeaderInterceptor implements Interceptor {

    private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String BEARER = "Bearer ";

    private final TokenManager tokenManager;

    public AuthHeaderInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request request = originalRequest.newBuilder()
                .header(AUTH_HEADER_KEY, BEARER + tokenManager.getToken())
                .build();
        return chain.proceed(request);
    }
}
