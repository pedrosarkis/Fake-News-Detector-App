package com.pedro.fakenewsdetector.api;

import com.pedro.fakenewsdetector.api.dto.DetectFakeNewsDTO;
import com.pedro.fakenewsdetector.api.dto.DetectFakeNewsScoreDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FakeNewsApi {
    @POST("/pt/predict/")
    Call<DetectFakeNewsScoreDTO> predictPortuguese(@Body DetectFakeNewsDTO dto);

    @POST("/en/predict/")
    Call<DetectFakeNewsScoreDTO> predictEnglish(@Body DetectFakeNewsDTO dto);
}
