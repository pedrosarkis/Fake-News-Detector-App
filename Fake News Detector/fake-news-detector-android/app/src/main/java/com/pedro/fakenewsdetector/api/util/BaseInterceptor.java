package com.pedro.fakenewsdetector.api.util;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder request = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", Locale.getDefault().toString())
                .addHeader("Device-Agent", "Android-Client")
                .addHeader("Device-Id", "Android-Client")
                .addHeader("Device-Token", "Android-Client")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "D8A928B2043DB77E340B523547BF16CB4AA483F0645FE0A290ED1F20AAB76257");

        return chain.proceed(request.build());
    }
}
