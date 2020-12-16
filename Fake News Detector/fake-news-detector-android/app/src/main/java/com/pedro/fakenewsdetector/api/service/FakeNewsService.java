package com.pedro.fakenewsdetector.api.service;

import com.pedro.fakenewsdetector.api.FakeNewsApi;
import com.pedro.fakenewsdetector.api.dto.DetectFakeNewsDTO;
import com.pedro.fakenewsdetector.api.dto.DetectFakeNewsScoreDTO;
import com.pedro.fakenewsdetector.api.util.DefaultCallback;

import retrofit2.Call;

public class FakeNewsService extends BaseService<FakeNewsApi> {
    public FakeNewsService() {
        super(FakeNewsApi.class);
    }

    @Override
    public String getBaseUrl() {
        return "http://3.238.247.57";
    }

    public void checkPortuguese(String content, DefaultCallback.OnSuccessCallback<DetectFakeNewsScoreDTO> onSuccess, DefaultCallback.OnErrorCallback onError) {
        Call<DetectFakeNewsScoreDTO> caller = getService().predictPortuguese(new DetectFakeNewsDTO(content));
        caller.enqueue(new DefaultCallback<>(onSuccess, onError));
    }

    public void checkEnglish(String content, DefaultCallback.OnSuccessCallback<DetectFakeNewsScoreDTO> onSuccess, DefaultCallback.OnErrorCallback onError) {
        Call<DetectFakeNewsScoreDTO> caller = getService().predictEnglish(new DetectFakeNewsDTO(content));
        caller.enqueue(new DefaultCallback<>(onSuccess, onError));
    }
}
