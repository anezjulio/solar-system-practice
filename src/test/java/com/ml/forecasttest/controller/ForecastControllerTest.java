package com.ml.forecasttest.controller;

import com.ml.forecasttest.dto.WeatherDto;
import com.ml.forecasttest.service.ForecastService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ForecastControllerTest {

    @Mock
    private ForecastService forecastService;

    private ForecastController forecastController;

    @Before
    public void setUp() {
        forecastController = new ForecastController();
        forecastController.setForecastService(forecastService);
    }

    @Test
    public void givenDayShouldResponseBodyNotNull() {
        final long dia = 1l;
        final WeatherDto expected = new WeatherDto(dia, "Lluvioso");
        when(forecastService.getDayForecast(dia))
                .thenReturn(expected);
        final ResponseEntity<?> actualResponse = forecastController.getClima(dia);
        final WeatherDto actual = (WeatherDto)actualResponse.getBody();
        assertEquals(expected,actual);
    }

}