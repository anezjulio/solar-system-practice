package com.ml.forecasttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class ForecastTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForecastTestApplication.class, args);
    }

}
