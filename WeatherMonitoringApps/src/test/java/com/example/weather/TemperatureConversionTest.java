package com.example.weather;
import com.example.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TemperatureConversionTest {

    @Autowired
    private WeatherService weatherService; // Spring injects this

    @Test
    public void testKelvinToCelsiusConversion() {
        double kelvinTemp = 300.0;
        double expectedCelsius = 26.85;

        double actualCelsius = weatherService.convertKelvinToCelsius(kelvinTemp);
        assertEquals(expectedCelsius, actualCelsius, 0.01, "Temperature conversion from Kelvin to Celsius failed");
    }
    
    
    
}
