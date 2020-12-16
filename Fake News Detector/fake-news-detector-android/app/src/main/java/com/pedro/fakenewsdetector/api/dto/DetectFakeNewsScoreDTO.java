package com.pedro.fakenewsdetector.api.dto;

public class DetectFakeNewsScoreDTO {
    private double prediction;

    public DetectFakeNewsScoreDTO(double prediction) {
        this.prediction = prediction;
    }

    public double getPrediction() {
        return prediction;
    }

    public void setPrediction(double prediction) {
        this.prediction = prediction;
    }
}
