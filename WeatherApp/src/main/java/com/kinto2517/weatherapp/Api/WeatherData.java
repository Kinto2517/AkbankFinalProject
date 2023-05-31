package com.kinto2517.weatherapp.Api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherData {
    private long dt;
    private MainData main;
    private List<OpenWeather> weather;
    private int visibility;
    private double pop;
    private String dt_txt;

    public WeatherData() {
    }

    public WeatherData(long dt, MainData main, List<OpenWeather> weather, int visibility, double pop, String dt_txt) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;
        this.visibility = visibility;
        this.pop = pop;
        this.dt_txt = dt_txt;
    }
}
