package com.pedro.fakenewsdetector.api.service;

import com.pedro.fakenewsdetector.api.util.BaseInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseService<T> {
    final int TIMEOUT = 60;
    private final Class<T> tClass;
    private T repo;

    public abstract String getBaseUrl();

    public BaseService(Class<T> tClass) {
        this.tClass = tClass;
    }

    public T getService() {
        if(repo == null) repo = buildService();
        return repo;
    }

    private T buildService() {
        Retrofit.Builder service = new Retrofit.Builder().baseUrl(getBaseUrl());

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        client.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        client.addInterceptor(new BaseInterceptor());
        service.client(client.build());
        service.addConverterFactory(GsonConverterFactory.create());
        return service.build().create(tClass);
    }
}
