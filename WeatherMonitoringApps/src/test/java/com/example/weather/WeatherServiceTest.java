package com.example.weather;

import com.example.weather.entity.WeatherData;
import com.example.weather.repository.WeatherDataRepository;
import com.example.weather.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeatherServiceTest {

    @Mock
    private WeatherDataRepository weatherDataRepository;

    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        weatherService = Mockito.mock(WeatherService.class);
    }

    @Test
    void testFetchWeatherData() {
        String city = "Delhi";

        // Mock the weather data response
        WeatherData mockWeatherData = new WeatherData();
        mockWeatherData.setTemperature(30.0); // Set a mocked temperature value

        // Define the behavior of the mock service
        when(weatherService.fetchWeatherData(city)).thenReturn(mockWeatherData);

        // Call the method under test
        WeatherData weatherData = weatherService.fetchWeatherData(city);

        // Set a range for the expected temperature
        double actualTemperature = weatherData.getTemperature();
        double minExpectedTemperature = 29.0; // Set a minimum expected value
        double maxExpectedTemperature = 31.0; // Set a maximum expected value

        // Assert that the temperature is within the expected range
        assertTrue(actualTemperature >= minExpectedTemperature && actualTemperature <= maxExpectedTemperature,
                "Temperature is out of expected range: " + actualTemperature);
    }
}
