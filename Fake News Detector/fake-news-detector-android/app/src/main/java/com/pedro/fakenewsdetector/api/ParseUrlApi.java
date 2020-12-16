package com.pedro.fakenewsdetector.api;

import com.pedro.fakenewsdetector.api.dto.ParseUrlContentDTO;
import com.pedro.fakenewsdetector.api.dto.ParseUrlDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ParseUrlApi {
    @POST("/news/scrap/")
    Call<ParseUrlContentDTO> getContent(@Body ParseUrlDTO dto);
}
