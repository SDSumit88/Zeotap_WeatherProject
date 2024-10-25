package com.example.weather.repository;

import com.example.weather.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    List<WeatherData> findByCityAndTimestampBetween(String city, LocalDateTime start, LocalDateTime end);
    
    // Method to find WeatherData by city
    Optional<WeatherData> findByCity(String city);
}
