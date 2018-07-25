package com.carousell.codingchallenge.api;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRepository {
    private static ApiRepository instance;

    private CodingChallengeApi codingChallengeApi;

    public static ApiRepository get() {
        if (instance == null) {
            instance = new ApiRepository();
        }
        return instance;
    }

    public ApiRepository() {
        final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder().connectionSpecs(
                Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);

        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        final GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        final Retrofit retrofit = new Retrofit.Builder().client(clientBuilder.build())
                .baseUrl(Api.API_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();

        codingChallengeApi = retrofit.create(CodingChallengeApi.class);
    }

    @NonNull
    public CodingChallengeApi getCodingChallengeApi() {
        return codingChallengeApi;
    }
}
