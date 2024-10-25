package com.example.weather;

import com.example.weather.entity.WeatherData;
import com.example.weather.service.WeatherService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Tag("integration")
public class WeatherServiceIntegrationTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    public void testFetchRealWeatherData() {
        // Test fetching real weather data from OpenWeatherMap API
        String city = "Delhi";
        WeatherData weatherData = weatherService.fetchWeatherData(city);

        // Check that weather data is returned
        assertNotNull(weatherData, "Weather data should not be null");
        System.out.println("Current temperature in " + city + ": " + weatherData.getTemperature() + "°C");

        // Optionally check for expected temperature ranges (may vary depending on the real data)
        assertTrue(weatherData.getTemperature() > -50 && weatherData.getTemperature() < 60, 
                   "Temperature is within realistic bounds");
    }
}
