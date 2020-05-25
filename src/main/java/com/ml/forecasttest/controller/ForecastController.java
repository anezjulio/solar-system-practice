package com.ml.forecasttest.controller;

import com.ml.forecasttest.service.ForecastService;
import com.ml.forecasttest.util.ForecastUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vulcanos")
public class ForecastController {

    private ForecastService forecastService;

    @Autowired
    public void setForecastService(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping(value = "/clima")
    public ResponseEntity<?> getClima(@RequestParam Long day) {
        if (day <= 0 || day > ForecastUtil.DAYS_PER_YEAR * ForecastUtil.YEAR_PREDICTION) {
            return ResponseEntity.badRequest()
                    .body("Dia no contemplado.");
        } else {
            return ResponseEntity.ok()
                    .body(forecastService.getDayForecast(day));
        }
    }

    @GetMapping(value = "/reporte")
    public ResponseEntity<?> getReporte() {
        return ResponseEntity.ok()
                .body(forecastService.getCompleteReportForecast());
    }

}
