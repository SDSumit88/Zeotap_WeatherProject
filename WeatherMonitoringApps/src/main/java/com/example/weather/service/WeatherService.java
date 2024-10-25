package com.example.weather.service;

import com.example.weather.entity.WeatherData;
import com.example.weather.entity.WeatherSummary;
import com.example.weather.repository.WeatherDataRepository;
import com.example.weather.service.AlertService;

import jakarta.transaction.Transactional;

import com.example.weather.entity.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeatherService {
    @Autowired
    private AlertService alertService;

    private final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final WeatherDataRepository weatherDataRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiKey = "d22fba8b465f393451d9d00638b9b3d0"; // Replace with your API key
    private final String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";

    // Store the weather data
    private List<WeatherData> weatherDataList = new ArrayList<>();
    
    // Store alert thresholds per city
    private Map<String, Double> maxTemperatureThresholds = new HashMap<>();
    private Map<String, Integer> consecutiveUpdatesThresholds = new HashMap<>();
    
    // Store the count of consecutive alerts per city
    private Map<String, Integer> consecutiveAlertCounter = new HashMap<>();

    public WeatherService(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    // Method to fetch weather data for any city dynamically
    @Transactional
    public WeatherData fetchWeatherData(String city) {
    	 logger.info("Entered fetchWeatherData method for city: {}", city);
        String url = apiUrl.replace("{city}", city).replace("{apiKey}", apiKey);
        logger.info("Fetching weather data for: {}", city);

        try {
            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
            logger.info("API Response: {}", response);
            if (response != null) {

                WeatherData data = new WeatherData();
                data.setCity(city);
                data.setTemperature(convertKelvinToCelsius(response.getMain().getTemp()));
                data.setFeelsLike(convertKelvinToCelsius(response.getMain().getFeelsLike()));
                data.setCondition(response.getWeather().get(0).getMain());
                data.setHumidity(response.getMain().getHumidity()); // Add humidity
                data.setWindSpeed(response.getWind().getSpeed()); // Add wind speed
                data.setTimestamp(LocalDateTime.now());
                logger.info("Saving weather data for city: {} at timestamp: {}", city, data.getTimestamp());

                // Save the weather data to the repository
                weatherDataRepository.save(data);
                logger.info("Weather data saved for: {}", city);

                // Check for alerts after saving the data
                updateWeatherData(data);
                return data;
            } else {
                logger.error("Failed to fetch data for city: {}. No response or invalid API key.", city);
            }
        } catch (Exception e) {
            logger.error("Error while fetching weather data for city: {}", city, e);
        }
        return null;
    }

    public double convertKelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public void setAlertThreshold(String city, double maxTemp, int updates) {
        maxTemperatureThresholds.put(city, maxTemp);
        consecutiveUpdatesThresholds.put(city, updates);
        logger.info("Alert threshold set for {}: Max Temp = {}, Consecutive Updates = {}", city, maxTemp, updates);
    }

    public void updateWeatherData(WeatherData newData) {
        weatherDataList.add(newData); // Add new weather data to the list
        
        String city = newData.getCity();
        double maxTempThreshold = maxTemperatureThresholds.getOrDefault(city, Double.MAX_VALUE);
        int consecutiveUpdatesThreshold = consecutiveUpdatesThresholds.getOrDefault(city, 1);

        // Check if the temperature exceeds the threshold
        if (newData.getTemperature() > maxTempThreshold) {
            // Get the current count of alerts for the city
            int counter = consecutiveAlertCounter.getOrDefault(city, 0) + 1;

            // Check if the count exceeds the threshold
            if (counter >= consecutiveUpdatesThreshold) {
                alertService.triggerAlert(newData); // Trigger alert
                consecutiveAlertCounter.put(city, 0); // Reset counter after alert
            } else {
                consecutiveAlertCounter.put(city, counter); // Update counter
            }
        } else {
            // Reset counter if the temperature is below the threshold
            consecutiveAlertCounter.put(city, 0);
        }
    }

    public WeatherSummary calculateDailySummary(String city) {
    	LocalDateTime today = LocalDateTime.now();
        LocalDateTime startOfDay = today.toLocalDate().atStartOfDay();
        logger.info("Calculating daily summary for city: {} - StartOfDay: {}, EndOfDay: {}", city, startOfDay, today);
        List<WeatherData> data = weatherDataRepository.findByCityAndTimestampBetween(city, startOfDay, today);
        System.out.println("data response"+data);

        if (data.isEmpty()) {
            logger.warn("No weather data found for city: {} for today", city);
            return new WeatherSummary(0, 0, 0, 0, 0, "No data"); // Adjusted to include humidity and wind speed
        }

        // Calculate average, max, and min temperatures
        double averageTemp = data.stream().mapToDouble(WeatherData::getTemperature).average().orElse(0);
        double maxTemp = data.stream().mapToDouble(WeatherData::getTemperature).max().orElse(0);
        double minTemp = data.stream().mapToDouble(WeatherData::getTemperature).min().orElse(0);

        // Calculate average humidity
        double averageHumidity = data.stream().mapToDouble(WeatherData::getHumidity).average().orElse(0);

        // Calculate average wind speed
        double averageWindSpeed = data.stream().mapToDouble(WeatherData::getWindSpeed).average().orElse(0);

        // Determine dominant weather condition
        String dominantCondition = data.stream()
            .collect(Collectors.groupingBy(WeatherData::getCondition, Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .get()
            .getKey();

        return new WeatherSummary(averageTemp, maxTemp, minTemp, averageHumidity, averageWindSpeed, dominantCondition);
    }
    
    @Scheduled(fixedRate = 300000) // Runs every 5 min
    public void fetchWeatherDataForPredefinedCities() {
        List<String> cities = Arrays.asList("Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"); // Add or modify cities as needed

        for (String city : cities) {
            logger.info("Scheduled fetching of weather data for city: {}", city);
            fetchWeatherData(city); // Fetch and store weather data for each city
        }
    }

    public List<WeatherData> getHistoricalWeatherData(String city, LocalDateTime startDate, LocalDateTime endDate) {
        return weatherDataRepository.findByCityAndTimestampBetween(city, startDate, endDate);
    }
    

}
