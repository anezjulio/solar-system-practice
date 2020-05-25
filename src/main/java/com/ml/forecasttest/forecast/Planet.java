package com.ml.forecasttest.forecast;

import static java.lang.Math.*;

public class Planet {

    private Double currentPosition;
    private Double angleMovement;
    private Double kilometersFromSun;

    public Planet(){}

    public Planet(Double currentPosition, Double angleMovement, Double kilometersFromSun){
        this.currentPosition = currentPosition;
        this.angleMovement = angleMovement;
        this.kilometersFromSun = kilometersFromSun;
    }

    public void movePlanet() {
        /**
         * Correct angle to get a positive angle equivalent
         *  -36 -> 324
         *  380 -> 20
         */
        if (this.currentPosition + this.angleMovement >= 360) {
            this.currentPosition = this.currentPosition + this.angleMovement - 360;
        } else if (this.currentPosition + this.angleMovement < 0) {
            this.currentPosition = this.currentPosition + this.angleMovement + 360;
        }else{
            this.currentPosition += this.angleMovement;
        }

    }

    /**
     * horizontal = range × cos( angle )
     *
     * @return horizontal catesian coordinate
     */
    public Double getX(){
        return this.kilometersFromSun * cos( toRadians( this.currentPosition ) );
    }


    /**
     * vertical = range × sin( angle )
     *
     * @return vertical catesian coordinate
     */
    public Double getY(){
        return this.kilometersFromSun * sin( toRadians( this.currentPosition ) );
    }

}
