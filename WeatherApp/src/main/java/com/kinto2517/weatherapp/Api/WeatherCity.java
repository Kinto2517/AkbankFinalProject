package com.kinto2517.weatherapp.Api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherCity {
    private int id;
    private String name;
    private String country;
    private int population;
    private int timezone;
    private long sunrise;
    private long sunset;

    public WeatherCity() {
    }

    public WeatherCity(int id, String name, String country, int population, int timezone, long sunrise, long sunset) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.population = population;
        this.timezone = timezone;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }
}
