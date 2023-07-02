package com.example.cryptoapp.data.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    private static final String BASE_URL = "https://min-api.cryptocompare.com/data/";
    public static final String BASE_IMAGE_URl = "https://cryptocompare.com";


    public final static Retrofit instance = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    public final static ApiService apiservice = instance.create(ApiService.class);

}
