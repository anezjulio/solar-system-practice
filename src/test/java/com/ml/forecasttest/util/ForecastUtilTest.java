package com.ml.forecasttest.util;

import com.ml.forecasttest.forecast.Planet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ForecastUtilTest {

    @Test
    public void givenPlanetsPositionsShouldReturnATriangleArea() {
        final Planet[] planets = {
                new Planet(5.0, 5.0, 1000.0),
                new Planet(359.0, -1.0, 500.0),
                new Planet(357.0, -3.0, 2000.0)
        };
        final double expectedArea = 95591.23679190179;
        final double actualArea = ForecastUtil.getTriangleArea(planets);
        assertEquals( expectedArea, actualArea);
    }

    @Test
    public void givenPlanetsPositionsShouldNotReturnTriangleArea() {
        final Planet[] planets = {
                new Planet(0.0, 5.0, 1000.0),
                new Planet(0.0, -1.0, 500.0),
                new Planet(0.0, -3.0, 2000.0)
        };
        final double expectedArea = 0.0;
        final double actualArea = ForecastUtil.getTriangleArea(planets);
        assertEquals( expectedArea, actualArea);
    }

    @Test
    public void givenApproxValuesShouldReturnTrue() {
        final boolean expected = ForecastUtil.approxEqual(30.005, 30.00455555);
        assertTrue(expected);
    }

    @Test
    public void givenApproxValuesShouldReturnFalse() {
        final boolean expected = ForecastUtil.approxEqual(30.05, 30.059999);
        assertFalse(expected);
    }

    @Test
    public void whenMovePlanetSholdBeEqualsToNextPlanetPosition() {
        final Planet actualPlanet = new Planet(0.0, 5.0, 1000.0);
        final Planet expectedPlanet = new Planet(5.0, 5.0, 1000.0);
        actualPlanet.movePlanet();
        assertEquals(expectedPlanet.getX(), actualPlanet.getX());
        assertEquals(expectedPlanet.getY(), actualPlanet.getY());
    }
}