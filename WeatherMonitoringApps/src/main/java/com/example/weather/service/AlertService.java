package com.example.weather.service;

import com.example.weather.entity.WeatherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AlertService {
    private final Logger logger = LoggerFactory.getLogger(AlertService.class);

    // Store alert thresholds for each city
    private final Map<String, AlertThreshold> alertThresholds = new HashMap<>();

    // Method to set alert thresholds for a specific city
    public void setAlertThreshold(String city, double maxTemp, int updates) {
        alertThresholds.put(city, new AlertThreshold(maxTemp, updates));
        logger.info("Alert threshold set for city: {} (Max Temp: {}, Consecutive Updates: {})",
                     city, maxTemp, updates);
    }

    // Method to check if the latest weather data exceeds the thresholds
    public void checkAlertThresholds(WeatherData latestData) {
        String city = latestData.getCity();
        if (alertThresholds.containsKey(city)) {
            AlertThreshold threshold = alertThresholds.get(city);
            if (latestData.getTemperature() > threshold.maxTemp) {
                threshold.consecutiveUpdates++;
                logger.warn("Current Temp: {}°C exceeds threshold for city: {}. Consecutive updates: {}",
                             latestData.getTemperature(), city, threshold.consecutiveUpdates);
                
                // Check if the consecutive updates reached the required threshold
                if (threshold.consecutiveUpdates >= threshold.requiredUpdates) {
                    triggerAlert(latestData);
                    threshold.consecutiveUpdates = 0; // Reset after alerting
                }
            } else {
                threshold.consecutiveUpdates = 0; // Reset if temperature is below threshold
            }
        }
    }

    // Method to trigger an alert based on weather data
    public void triggerAlert(WeatherData weatherData) {
        // Log or handle the alert as needed
        logger.warn("ALERT! Temperature exceeded threshold for city: {}. Current Temp: {}°C", 
                     weatherData.getCity(), weatherData.getTemperature());

        // Here, you can implement additional alerting logic, such as sending an email
        // or notifying the user through another mechanism
        // e.g., sendEmailNotification(weatherData);
    }

    // Inner class to hold alert threshold information
    private static class AlertThreshold {
        double maxTemp; // Maximum temperature threshold
        int requiredUpdates; // Required consecutive updates
        int consecutiveUpdates; // Current count of consecutive updates

        AlertThreshold(double maxTemp, int requiredUpdates) {
            this.maxTemp = maxTemp;
            this.requiredUpdates = requiredUpdates;
            this.consecutiveUpdates = 0; // Start with zero updates
        }
    }
}
