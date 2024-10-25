package com.example.weather.controller;

import com.example.weather.entity.WeatherSummary;
import com.example.weather.entity.WeatherData;
import com.example.weather.service.AlertService;
import com.example.weather.service.WeatherService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    
    @Autowired
    private AlertService alertService; // Inject AlertService

    // Get daily weather summary for a specific city
    @GetMapping("/summary")
    public WeatherSummary getDailySummary(@RequestParam String city) {
    	WeatherSummary summary = weatherService.calculateDailySummary(city);
        
        // Check for alerts with the latest weather data
        WeatherData latestData = summary.getLatestData(); // Ensure your WeatherSummary has this method
        if (latestData != null) {
            alertService.checkAlertThresholds(latestData); // Use the AlertService to check alerts
        }
       
        return summary;
    }

    // Get historical weather data for a city within a date range
    @GetMapping("/historical")
    public List<WeatherData> getHistoricalWeather(
            @RequestParam String city, 
            @RequestParam String startDate, 
            @RequestParam String endDate) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);

        // Validate date range
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before the end date.");
        }

        return weatherService.getHistoricalWeatherData(city, start, end);
    }
    
    // Endpoint to set alert thresholds for a city
    @PostMapping("/setAlertThreshold")
    public String setAlertThreshold(@RequestParam String city, 
                                     @RequestParam double maxTemp, 
                                     @RequestParam int updates) {
        weatherService.setAlertThreshold(city, maxTemp, updates);
        return "Alert threshold set for " + city + " (Max Temp: " + maxTemp + 
               ", Consecutive Updates: " + updates + ")";
    }
    
    // Fetch real-time weather for a city and save it to the database
    @GetMapping("/fetch")
    public WeatherData fetchWeather(@RequestParam String city) {
        return weatherService.fetchWeatherData(city);
    }

//    // Optionally allow fetching and saving weather data for any city dynamically
//    @PostMapping("/fetchAndSave")
//    public String fetchAndSaveCityData(@RequestParam String city) {
//        weatherService.fetchAndSaveForCity(city);
//        return "Weather data for " + city + " fetched and saved.";
//    }
    
    

}
