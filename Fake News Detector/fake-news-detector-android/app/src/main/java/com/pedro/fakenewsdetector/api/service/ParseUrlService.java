package com.pedro.fakenewsdetector.api.service;

import com.pedro.fakenewsdetector.api.util.DefaultCallback;
import com.pedro.fakenewsdetector.api.ParseUrlApi;
import com.pedro.fakenewsdetector.api.dto.ParseUrlContentDTO;
import com.pedro.fakenewsdetector.api.dto.ParseUrlDTO;

import retrofit2.Call;

public class ParseUrlService extends BaseService<ParseUrlApi> {
    public ParseUrlService() {
        super(ParseUrlApi.class);
    }

    @Override
    public String getBaseUrl() {
        return "https://tcspedroverani.herokuapp.com";
    }

    public void parse(String url, DefaultCallback.OnSuccessCallback<ParseUrlContentDTO> onSuccess, DefaultCallback.OnErrorCallback onError) {
        Call<ParseUrlContentDTO> caller = getService().getContent(new ParseUrlDTO(url));
        caller.enqueue(new DefaultCallback<>(onSuccess, onError));
    }
}
