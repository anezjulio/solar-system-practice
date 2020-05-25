package com.ml.forecasttest.job;

import com.ml.forecasttest.service.ForecastService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherPredictionJob {

    private static final Logger LOGGER = LogManager.getLogger(ForecastService.class);

    private ForecastService forecastService;
    private CacheManager cacheManager;

    @Autowired
    public void setForecastService(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @Autowired
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Scheduled(cron = "${forecast.job-cron}")
    public void onScheduledCron() {
        LOGGER.info("onScheduledCron");
        for (String name : cacheManager.getCacheNames()) {
            cacheManager.getCache(name).clear();
        }
        doForecastPrediction();
    }

    private void doForecastPrediction() {
        forecastService.doForecastWeather();
    }

}
