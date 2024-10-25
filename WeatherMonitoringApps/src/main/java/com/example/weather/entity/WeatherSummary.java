package com.example.weather.entity;

import java.util.List;

//Summary Object for Rollups

public class WeatherSummary {
	
	 private List<WeatherData> weatherDataList;


	 private double averageTemperature;
	    private double maxTemperature;
	    private double minTemperature;
	    private double averageHumidity; // Field for average humidity
	    private double averageWindSpeed; // Field for average wind speed
	    private String dominantCondition;

	    // Constructor
	    public WeatherSummary(double averageTemperature, double maxTemperature, double minTemperature,
	                          double averageHumidity, double averageWindSpeed, String dominantCondition) {
	        this.averageTemperature = averageTemperature;
	        this.maxTemperature = maxTemperature;
	        this.minTemperature = minTemperature;
	        this.averageHumidity = averageHumidity;
	        this.averageWindSpeed = averageWindSpeed;
	        this.dominantCondition = dominantCondition;
	    }

	    // Getters and Setters
	    public double getAverageTemperature() {
	        return averageTemperature;
	    }

	    public void setAverageTemperature(double averageTemperature) {
	        this.averageTemperature = averageTemperature;
	    }

	    public double getMaxTemperature() {
	        return maxTemperature;
	    }

	    public void setMaxTemperature(double maxTemperature) {
	        this.maxTemperature = maxTemperature;
	    }

	    public double getMinTemperature() {
	        return minTemperature;
	    }

	    public void setMinTemperature(double minTemperature) {
	        this.minTemperature = minTemperature;
	    }

	    public double getAverageHumidity() {
	        return averageHumidity;
	    }

	    public void setAverageHumidity(double averageHumidity) {
	        this.averageHumidity = averageHumidity;
	    }

	    public double getAverageWindSpeed() {
	        return averageWindSpeed;
	    }

	    public void setAverageWindSpeed(double averageWindSpeed) {
	        this.averageWindSpeed = averageWindSpeed;
	    }

	    public String getDominantCondition() {
	        return dominantCondition;
	    }

	    public void setDominantCondition(String dominantCondition) {
	        this.dominantCondition = dominantCondition;
	    }
	    // Optional: Override toString() to provide a summary when printed
	    @Override
	    public String toString() {
	        return "WeatherSummary{" +
	                "averageTemperature=" + averageTemperature +
	                ", maxTemperature=" + maxTemperature +
	                ", minTemperature=" + minTemperature +
	                ", averageHumidity=" + averageHumidity +
	                ", averageWindSpeed=" + averageWindSpeed +
	                '}';
	    }
    
    public WeatherData getLatestData() {
        if (weatherDataList != null && !weatherDataList.isEmpty()) {
            return weatherDataList.get(weatherDataList.size() - 1); // Return the latest data
        }
        return null;
    }
}
