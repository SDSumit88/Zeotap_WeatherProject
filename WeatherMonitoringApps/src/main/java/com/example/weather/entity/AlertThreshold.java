package com.example.weather.entity;

public class AlertThreshold {
    private double maxTemperature;
    private int consecutiveUpdates;

    // Constructor, getters, and setters
    public AlertThreshold(double maxTemperature, int consecutiveUpdates) {
        this.maxTemperature = maxTemperature;
        this.consecutiveUpdates = consecutiveUpdates;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public int getConsecutiveUpdates() {
        return consecutiveUpdates;
    }

    public void setConsecutiveUpdates(int consecutiveUpdates) {
        this.consecutiveUpdates = consecutiveUpdates;
    }
}
