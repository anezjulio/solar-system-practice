package com.ml.forecasttest.service;

import com.ml.forecasttest.dto.CompleteReportForecastDto;
import com.ml.forecasttest.dto.WeatherDto;
import com.ml.forecasttest.mapper.WeatherMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ml.forecasttest.enums.WeatherTypeEnum;
import com.ml.forecasttest.model.Weather;
import com.ml.forecasttest.repository.WeatherRepository;
import com.ml.forecasttest.util.ForecastUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.annotations.Cacheable;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ForecastService {

    private WeatherRepository weatherRepository;

    @Autowired
    public void setWeatherRepository(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    private static final Logger LOGGER = LogManager.getLogger(ForecastService.class);

    @Cacheable("Weather")
    public WeatherDto getDayForecast(long day){
        return WeatherMapper.mapWeather(
                weatherRepository.findByPredictionDayAndStartDate(day, LocalDate.now())
        );
    }

    @Cacheable("CompleteReportForecast")
    public CompleteReportForecastDto getCompleteReportForecast() {
        return new CompleteReportForecastDto(
                getWeatherTypesCount(
                        weatherRepository.findAllByStartDate(LocalDate.now())
                )
        );
    }

    public List<String> getWeatherTypesCount(List<Weather> weatherList){
        List<String> reportList = new ArrayList<>();
        reportList.add("períodos de sequía: " + weatherList.stream().filter(c -> WeatherTypeEnum.DRY.equals(c.getWeatherType())).count());
        reportList.add("períodos de lluvia: " + weatherList.stream().filter(c -> WeatherTypeEnum.RAINY.equals(c.getWeatherType())).count());
        reportList.add("pico máximo de lluvia: dia " + weatherList.stream().filter(c -> WeatherTypeEnum.PEAK_INTENSITY_RAINY.equals(c.getWeatherType())).findFirst().get().getPredictionDay());
        reportList.add("períodos de condiciones óptimas de presión y temperatura: " + weatherList.stream() .filter(c -> WeatherTypeEnum.OPTIMAL_PRESSURE_AND_TEMPERATURE.equals(c.getWeatherType())) .count());
        reportList.add("períodos desconocidos: " + weatherList.stream().filter(c -> WeatherTypeEnum.UNPREDICTED.equals(c.getWeatherType())) .count());
        return reportList;

    }

    /**
     * by each day, start in 1 to Year * daysPerYear
     * <p>
     * predict weather
     * check if planets form a triangle amoung them
     * yes -> if 2 planets and the sun form a triangle, there is a optimal pressure and temperature weather
     * no  -> there is a dry weather
     * if the sum of internal triangles between 3 planets and the sun is the same as 3 planets triangle
     * yes -> there is a rainy weather
     * no  -> there is a unpredicted weather
     */
    public void doForecastWeather() {
        saveWeatherList(getForecastList());
        LOGGER.info("Forecast has been save complete. ");
    }

    /**
     * persist forecastList
     *
     * @param forecastList
     */
    @Transactional
    public void saveWeatherList(List<Weather> forecastList) {
        weatherRepository.saveAll(forecastList);
    }

    /**
     *
     * @return list of weather to each day
     */
    private List<Weather> getForecastList() {

        Weather peakIntensityRainyWeather = new Weather();
        peakIntensityRainyWeather.setTrianglePerimeter(0.0);
        List<Weather> forecastList = new ArrayList<>();

        for (long day = 1; day <= ForecastUtil.YEAR_PREDICTION * ForecastUtil.DAYS_PER_YEAR; day++) {
            // Planets form a triangle amoung them
            if (ForecastUtil.isTriangleFormationBetweenPlanets()) {
                // is sun inside triangle formation
                if (ForecastUtil.isSunInsidePlanetsTriangle()) {
                    // RAINY
                    final Weather actualWeather = buildWeather(day, WeatherTypeEnum.RAINY);
                    if (actualWeather.getTrianglePerimeter() > peakIntensityRainyWeather.getTrianglePerimeter()) {
                        // Max Rainy Weather
                        peakIntensityRainyWeather = actualWeather;
                    }
                    forecastList.add(actualWeather);
                } else {
                    // UNPREDICTED
                    forecastList.add(buildWeather(day, WeatherTypeEnum.UNPREDICTED));
                }
                // No triangle formation, Planets are aligned
            } else {
                // 2 planets and the sun form a triangle
                if (ForecastUtil.isTriangleFormationBetween2PlanetsAndSun()) {
                    // OPTIMAL
                    forecastList.add(buildWeather(day, WeatherTypeEnum.OPTIMAL_PRESSURE_AND_TEMPERATURE));
                } else {
                    // DRY
                    forecastList.add(buildWeather(day, WeatherTypeEnum.DRY));
                }
            }
            ForecastUtil.movePlanets();
        }
        forecastList.remove(peakIntensityRainyWeather);
        peakIntensityRainyWeather.setWeatherType(WeatherTypeEnum.PEAK_INTENSITY_RAINY);
        forecastList.add(peakIntensityRainyWeather);
        return forecastList;
    }

    /**
     * build a weather object to persist
     * @param day
     * @param weatherType
     * @return
     */
    private Weather buildWeather(long day, WeatherTypeEnum weatherType) {
        Weather weather = new Weather();
        weather.setStartDate(LocalDate.now());
        weather.setPredictionDate(LocalDate.now().plusDays(day));
        weather.setTrianglePerimeter(ForecastUtil.getTrianglePerimeter(ForecastUtil.planets));
        weather.setWeatherType(weatherType);
        weather.setPredictionDay(day);
        return weather;
    }

}
