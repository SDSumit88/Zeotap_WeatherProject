package com.example.weather.service;

import java.util.List;

public class WeatherResponse {
    private Main main;
    private Wind wind; // Add wind field
    private List<Weather> weather;

    // Inner class for main weather data
    public static class Main {
        private double temp;
        private double feelsLike;
        private int humidity; // Add humidity field

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getFeelsLike() {
            return feelsLike;
        }

        public void setFeelsLike(double feelsLike) {
            this.feelsLike = feelsLike;
        }

        public int getHumidity() { // Getter for humidity
            return humidity;
        }

        public void setHumidity(int humidity) { // Setter for humidity
            this.humidity = humidity;
        }
    }

    // Inner class for wind data
    public static class Wind {
        private double speed; // Field for wind speed

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }
    }
    
    public static class Weather {
        private String main;

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() { // Getter for wind
        return wind;
    }

    public void setWind(Wind wind) { // Setter for wind
        this.wind = wind;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
