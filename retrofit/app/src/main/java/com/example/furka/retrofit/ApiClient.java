package com.example.furka.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BaseUrl = "http://192.168.1.35/bitirme/";

    private static Retrofit retrofit;

    public static Retrofit getApiclient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BaseUrl).
                    addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
}
