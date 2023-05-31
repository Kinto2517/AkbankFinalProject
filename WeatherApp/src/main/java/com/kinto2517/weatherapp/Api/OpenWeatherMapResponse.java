package com.kinto2517.weatherapp.Api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenWeatherMapResponse {

    private String cod;
    private int message;
    private int cnt;
    private List<WeatherData> list;
    private WeatherCity city;

    public OpenWeatherMapResponse() {
    }

    public OpenWeatherMapResponse(String cod, int message, int cnt, List<WeatherData> list, WeatherCity city) {
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }

}
