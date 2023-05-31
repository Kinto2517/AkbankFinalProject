package com.kinto2517.weatherapp.Dto;

import java.time.LocalDateTime;

public record WeatherSaveRequest(
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
}
