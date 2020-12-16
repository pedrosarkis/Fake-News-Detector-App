package com.pedro.fakenewsdetector.api.dto;

public class DetectFakeNewsDTO {
    private String content;

    public DetectFakeNewsDTO() {
    }

    public DetectFakeNewsDTO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
