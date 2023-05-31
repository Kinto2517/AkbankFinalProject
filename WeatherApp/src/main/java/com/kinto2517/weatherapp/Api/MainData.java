package com.kinto2517.weatherapp.Api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainData {
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int sea_level;
    private int grnd_level;
    private int humidity;
    private double temp_kf;

    public MainData() {
    }

    public MainData(double temp, double feels_like, double temp_min, double temp_max, int pressure, int sea_level, int grnd_level, int humidity, double temp_kf) {
        this.temp = temp;
        this.feels_like = feels_like;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.pressure = pressure;
        this.sea_level = sea_level;
        this.grnd_level = grnd_level;
        this.humidity = humidity;
        this.temp_kf = temp_kf;
    }


}
