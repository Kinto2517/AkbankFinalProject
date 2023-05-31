package com.kinto2517.weatherapp.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record WeatherDTO(
        Long id,
        Long cityId,
        String main,
        String description,
        String temperature,
        String feelsLike,
        String minTemperature,
        String maxTemperature,
        String humidity,
        LocalDateTime dateTime
) {
    public WeatherDTO {
    }
}
