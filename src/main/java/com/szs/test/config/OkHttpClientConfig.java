package com.szs.test.config;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttpClientConfig {

    private final OkHttpClient client;

    public OkHttpClientConfig() {
        this.client = new OkHttpClient();
    }

    public OkHttpClient getClient() {
        return client;
    }

    public String get(String url, long connectTimeout, long readTimeout) throws IOException {
        OkHttpClient customizedClient = client.newBuilder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = customizedClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String post(String url, String jsonBody) throws IOException {
        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
