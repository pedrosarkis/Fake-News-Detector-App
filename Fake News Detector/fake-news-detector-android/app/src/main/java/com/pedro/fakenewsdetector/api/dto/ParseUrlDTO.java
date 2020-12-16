package com.pedro.fakenewsdetector.api.dto;

public class ParseUrlDTO {
    private String url;

    public ParseUrlDTO() {
    }

    public ParseUrlDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
