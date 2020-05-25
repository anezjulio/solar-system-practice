package com.ml.forecasttest.mapper;

import com.ml.forecasttest.dto.WeatherDto;
import com.ml.forecasttest.model.Weather;

public class WeatherMapper {
    public static WeatherDto mapWeather(Weather weather){
        return new WeatherDto(
                weather.getPredictionDay(),
                weather.getWeatherType().getName()
        );
    }
}
