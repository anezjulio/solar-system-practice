package com.ml.forecasttest.util;

import com.ml.forecasttest.forecast.Planet;

public class ForecastUtil {

    public static final int YEAR_PREDICTION = 10;
    public static final int DAYS_PER_YEAR = 365;
    public static final Double TOLERANCE = 0.001;

    public static Planet[] planets = {
            new Planet(0.0, 5.0, 1000.0),
            new Planet(0.0, -1.0, 500.0),
            new Planet(0.0, -3.0, 2000.0)
    };

    public static Planet sun = new Planet(0.0, 0.0, 0.0);

    public static void movePlanets() {
        planets[0].movePlanet();
        planets[1].movePlanet();
        planets[2].movePlanet();
    }


    /**
     * p1, p2, p3 = planets
     * A, B, C = distance between planets
     * <p>
     *          p1
     *         / |
     *        /  |
     *     A /   | C
     *      /    |
     *    p2 ___ p3
     *        B
     * <p>
     * Triangle formation is valid when distances:
     * <p>
     * A + B > C
     * A + C > B
     * B + C > A
     *
     * @return
     */

    private static boolean isTriangleFormation(Planet[] planets) {
        final Double A = getDistance(planets[0], planets[1]);
        final Double B = getDistance(planets[1], planets[2]);
        final Double C = getDistance(planets[2], planets[0]);
        return (A + B > C)
                && (A + C > B)
                && (B + C > A);
    }

    /**
     * @param p1
     * @param p2
     * @return Raiz ( ( x2 - x1 ) elevado 2 + ( y2 - y1 ) elevado 2 )
     */

    private static Double getDistance(Planet p1, Planet p2) {
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }

    public static boolean isTriangleFormationBetweenPlanets() {
        return getTriangleArea(planets) > TOLERANCE;
    }

    public static boolean isTriangleFormationBetween2PlanetsAndSun() {
        return getTriangleArea(new Planet[]{planets[0], planets[2], sun}) > TOLERANCE;
    }

    public static Double getTrianglePerimeter(Planet[] planets) {
        if(isTriangleFormation(planets)){
            final Double A = getDistance(planets[0], planets[1]);
            final Double B = getDistance(planets[1], planets[2]);
            final Double C = getDistance(planets[2], planets[0]);
            return A + B + C;
        }else{
            return 0.0;
        }
    }

    public static Double getTriangleSemiPerimeter(Planet[] planets) {
        return getTrianglePerimeter(planets) / 2;
    }

    public static Double getTriangleArea(Planet[] planets) {
        final Double A = getDistance(planets[0], planets[1]);
        final Double B = getDistance(planets[1], planets[2]);
        final Double C = getDistance(planets[2], planets[0]);
        final Double S = getTriangleSemiPerimeter(planets);
        return Math.sqrt( S * ( S - A ) * ( S - B ) * ( S - C ) );
    }

    public static boolean isSunInsidePlanetsTriangle() {
        return approxEqual(
                getTriangleArea(planets),
                getTriangleArea(new Planet[]{planets[0], planets[1], sun})
                    + getTriangleArea(new Planet[]{planets[1], planets[2], sun})
                    + getTriangleArea(new Planet[]{planets[2], planets[0], sun})
        );
    }

    public static boolean approxEqual(final double d1, final double d2) {
        return Math.abs(d1 - d2) < TOLERANCE;
    }

}
