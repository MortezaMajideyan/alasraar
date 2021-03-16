package com.pattern.sarketab.ui.retro;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://alasraar.com/";

    public static Retrofit getApplication() {

        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(40, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static Method_Conection getApi() {
        return getRetrofit().create(Method_Conection.class);
    }
}