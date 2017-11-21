package com.five.fashion.utils;


import com.five.fashion.mine.utils.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fan on 2017/11/8.
 */

public class RetroFactory {

    private RetroFactory() {
    }
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor()).connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
    private static SortApiServer retrofitService = new Retrofit.Builder()

            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(API.SORTIP)
            .client(httpClient)
            .build()
            .create(SortApiServer.class);

    //单列模式
    public static SortApiServer getInstance() {
        return retrofitService;
    }


}
