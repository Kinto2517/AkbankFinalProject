package com.kinto2517.weatherapp.Api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenWeather {

    private int id;
    private String main;
    private String description;
    private String icon;

    public OpenWeather() {
    }

    public OpenWeather(int id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

}
