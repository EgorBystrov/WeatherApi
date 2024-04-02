package org.example.controllers;

import org.example.DAO.WeathersDAO;
import org.example.WeatherTest;
import org.example.models.WeatherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    private final WeathersDAO weathersDAO;
    private final WeatherTest weatherTest;

    @Autowired
    public WeatherController(WeathersDAO weathersDAO, WeatherTest weatherTest) {
        this.weathersDAO = weathersDAO;
        this.weatherTest = weatherTest;
    }

    @GetMapping("/list")
    public String showWeatherList(Model model){
        model.addAttribute("weather_list", weathersDAO.getWeatherList());
        return "weather/list";
    }
    @GetMapping("/forecast")
    public String showWeatherForecast(@ModelAttribute("weather") WeatherEntity weatherEntity){
        return "weather/forecast";
    }
    @PostMapping()
    public String saveWeatherForecast(@ModelAttribute("weather") WeatherEntity weatherEntity){
        int temperature = weatherTest.apiConnection(weatherEntity.getCity());
        weatherEntity.setTemperature(temperature);
        weathersDAO.save(weatherEntity);
        return "weather/forecastNew";
    }


}
