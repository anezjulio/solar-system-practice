package com.ml.forecasttest.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ml.forecasttest.enums.WeatherTypeEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @JsonAlias(value = "dia")
    private Long predictionDay;
    @Enumerated(EnumType.STRING)
    @JsonAlias(value = "clima")
    private WeatherTypeEnum weatherType;
    @JsonIgnore
    private Double trianglePerimeter;
    @JsonIgnore
    private LocalDate startDate;
    @JsonIgnore
    private LocalDate predictionDate;

    public Weather() {}

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", predictionDay=" + predictionDay +
                ", weatherType=" + weatherType +
                ", trianglePerimeter=" + trianglePerimeter +
                ", startDate=" + startDate +
                ", predictionDate=" + predictionDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Objects.equals(id, weather.id) &&
                Objects.equals(predictionDay, weather.predictionDay) &&
                weatherType == weather.weatherType &&
                Objects.equals(trianglePerimeter, weather.trianglePerimeter) &&
                Objects.equals(startDate, weather.startDate) &&
                Objects.equals(predictionDate, weather.predictionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, predictionDay, weatherType, trianglePerimeter, startDate, predictionDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPredictionDay() {
        return predictionDay;
    }

    public void setPredictionDay(Long predictionDay) {
        this.predictionDay = predictionDay;
    }

    public WeatherTypeEnum getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(WeatherTypeEnum weatherType) {
        this.weatherType = weatherType;
    }

    public Double getTrianglePerimeter() {
        return trianglePerimeter;
    }

    public void setTrianglePerimeter(Double trianglePerimeter) {
        this.trianglePerimeter = trianglePerimeter;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getPredictionDate() {
        return predictionDate;
    }

    public void setPredictionDate(LocalDate predictionDate) {
        this.predictionDate = predictionDate;
    }
}
