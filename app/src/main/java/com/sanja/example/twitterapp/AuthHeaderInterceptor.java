package com.sanja.example.twitterapp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthHeaderInterceptor implements Interceptor {

    private static final String AUTH_HEADER_KEY = "Authorization";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request request = originalRequest.newBuilder()
                .header(AUTH_HEADER_KEY, "Bearer AAAAAAAAAAAAAAAAAAAAAG0m0AAAAAAA6P%2BjHzX5q1Fs%2BrPZrazHZqkFYMI%3D6Om8rVeRYRtZBtvDaKmChzpYcJiP8CPeJgcBr8VZTrpQHDtqEs") // TODO
                .build();
        return chain.proceed(request);
    }
}
