package com.ml.forecasttest.repository;

import com.ml.forecasttest.model.Weather;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository< Weather, Long > {

    @Query(value = "SELECT p FROM Weather p where p.predictionDay = :day and p.startDate = :current_date ")
    Weather findByPredictionDayAndStartDate(long day, LocalDate current_date);

    @Query(value = "SELECT p FROM Weather p where p.startDate = :current_date ")
    List<Weather> findAllByStartDate(LocalDate current_date);
}
