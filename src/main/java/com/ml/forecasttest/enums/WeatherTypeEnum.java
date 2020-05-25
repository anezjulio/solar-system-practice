package com.ml.forecasttest.enums;

public enum WeatherTypeEnum {
    RAINY("Lluvioso"),
    PEAK_INTENSITY_RAINY("Pico maximo de lluvia"),
    DRY("Sequia"),
    OPTIMAL_PRESSURE_AND_TEMPERATURE("Optima Presion y Temperatura"),
    UNPREDICTED("Sin Pronostico");

    private String name;

    WeatherTypeEnum(String name) {
        this.name = name;

    }

    public String getName() { return name;  }

}
