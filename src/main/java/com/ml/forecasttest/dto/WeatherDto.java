package com.ml.forecasttest.dto;

import java.util.Objects;

public class WeatherDto {
    private long dia;
    private String clima;

    public WeatherDto(long dia, String clima) {
        this.dia = dia;
        this.clima = clima;
    }

    @Override
    public String toString() {
        return "WeatherDto{" +
                "dia=" + dia +
                ", clima='" + clima + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherDto that = (WeatherDto) o;
        return dia == that.dia &&
                Objects.equals(clima, that.clima);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dia, clima);
    }

    public long getDia() {
        return dia;
    }

    public void setDia(long dia) {
        this.dia = dia;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }
}
