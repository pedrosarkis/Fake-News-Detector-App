package com.pedro.fakenewsdetector.api.dto;

public class ParseUrlContentDTO {
    private String content;

    public ParseUrlContentDTO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
