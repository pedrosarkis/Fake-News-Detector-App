package com.pedro.fakenewsdetector.api.util;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultCallback<T> implements Callback<T> {
    public interface OnSuccessCallback<T> {
        void onSuccess(T data);
    }

    public interface OnErrorCallback {
        void onError(int code, ResponseBody body, String errorBody);
    }
    
    private final OnSuccessCallback<T> successCallback;
    private final OnErrorCallback errorCallback;
    
    public DefaultCallback(OnSuccessCallback<T> successCallback, OnErrorCallback errorCallback) {
        this.successCallback = successCallback;
        this.errorCallback = errorCallback;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()) {
            successCallback.onSuccess(response.body());
        } else {
            errorCallback.onError(response.raw().code(), response.raw().body(), response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        errorCallback.onError(0, null, "");
        t.printStackTrace();
    }
}
