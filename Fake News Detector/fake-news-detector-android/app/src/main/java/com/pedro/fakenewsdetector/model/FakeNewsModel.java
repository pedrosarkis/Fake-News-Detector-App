package com.pedro.fakenewsdetector.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable
public class FakeNewsModel {
    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private Integer id;

    @DatabaseField(canBeNull = false)
    private String url;

    @DatabaseField(canBeNull = false)
    private String title;

    @DatabaseField(canBeNull = false)
    private String content;

    @DatabaseField(canBeNull = false)
    private String language;

    @DatabaseField(canBeNull = false)
    private Date createdAt;

    @DatabaseField(canBeNull = false)
    private double score;

    public FakeNewsModel() {
    }

    public FakeNewsModel(String url, String title, String language, String content, Date createdAt, double score) {
        this.url = url;
        this.title = title;
        this.content = content;
        this.language = language;
        this.createdAt = createdAt;
        this.score = score;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "FakeNewsModel{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content.substring(0, 20) + '\'' +
                ", language=" + language +
                ", createdAt=" + createdAt +
                ", score=" + score +
                '}';
    }
}
