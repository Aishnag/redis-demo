package com.weather_service.service;


import com.weather_service.model.Weather;
import com.weather_service.repository.WeatherRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class WeatherService {

    private final WeatherRepository repo;

    public WeatherService(WeatherRepository repo) {
        this.repo = repo;
    }

    @Cacheable(value = "weather", key = "#city")
    public String getWeatherByCity(String city) {

        System.out.println("Fetching data from DB for City : " + city);
        java.util.Optional<Weather> weather = repo.findByCity(city);
        return weather.map(Weather::getForecast).orElse("Forecast not available. ");
    }

    @CachePut(value = "weather", key = "#city")
    public String updateWeather(String city, String updatedWeather) {

        repo.findByCity(city).ifPresent(weather -> {
            weather.setForecast(updatedWeather);
            repo.save(weather);
        });
        return updatedWeather;
    }

    @Transactional
    @CacheEvict(value = "weather", key = "#city")
    public void deleteWeatherByCity(String city) {
        System.out.println("Removing weather data for city:" + city);
        repo.deleteWeatherByCity(city);
    }
}
