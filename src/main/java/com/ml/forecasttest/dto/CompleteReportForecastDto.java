package com.ml.forecasttest.dto;

import java.util.List;
import java.util.Objects;

public class CompleteReportForecastDto {
    private List<String> ForecastList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompleteReportForecastDto that = (CompleteReportForecastDto) o;
        return Objects.equals(ForecastList, that.ForecastList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ForecastList);
    }

    public List<String> getForecastList() {
        return ForecastList;
    }

    public void setForecastList(List<String> forecastList) {
        this.ForecastList = forecastList;
    }

    public CompleteReportForecastDto(List<String> typeWeatherList) {
        this.ForecastList = typeWeatherList;
    }
}
