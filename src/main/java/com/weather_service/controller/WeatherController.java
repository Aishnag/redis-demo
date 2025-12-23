package com.weather_service.controller;

import com.weather_service.model.Weather;
import com.weather_service.repository.WeatherRepository;
import com.weather_service.service.CacheInspectionService;
import com.weather_service.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService service;

    private final WeatherRepository repo;

    @Autowired
    CacheInspectionService inspectionService;

    public WeatherController(WeatherService service, WeatherRepository repo) {
        this.service = service;
        this.repo = repo;

    }

    @PostMapping
    public Weather createWeather(@RequestBody Weather weather) {
        return repo.save(weather);
    }

    @GetMapping
    public String getWeatherByCity(@RequestParam String city) {
        String weatherByCity = service.getWeatherByCity(city);
        return weatherByCity;
    }

    @GetMapping("/cacheData")
    public void getCacheData() {
        inspectionService.printCacheContent("weather");
    }

    @GetMapping("all")
    public List<Weather> getAllWeathers() {
        return repo.findAll();
    }


    @PutMapping("/{city}")
    public String updateWeather(@PathVariable String city, @RequestParam String updatedWeather) {
        return service.updateWeather(city, updatedWeather);

    }

    @DeleteMapping("/{city}")
    public void deleteWeatherById(@PathVariable String city) {
        service.deleteWeatherByCity(city);
        System.out.println("weather data for " + city + " has been deleted and cache evicted.");
    }


}
