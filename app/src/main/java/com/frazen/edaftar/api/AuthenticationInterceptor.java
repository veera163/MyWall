package com.frazen.edaftar.api;

import androidx.annotation.Nullable;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ramakanth on 20-12-2019.
 */

public class AuthenticationInterceptor implements Interceptor {

    private String authToken;

    public AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    @Nullable
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder().addHeader("Authorization", authToken);
        Request req = builder.build();

        return chain.proceed(req);
    }
}
