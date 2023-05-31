package com.kinto2517.weatherapp.Entity;

import com.kinto2517.weatherapp.Base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Weather extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(nullable = false, length = 50, name = "weather_main")
    private String main;

    @Column(nullable = false, length = 50, name = "weather_description")
    private String description;

    @Column(nullable = false, length = 50, name = "weather_temperature")
    private String temperature;

    @Column(nullable = false, length = 50, name = "weather_feels_like")
    private String feelsLike;

    @Column(nullable = false, length = 50, name = "weather_min_temperature")
    private String minTemperature;

    @Column(nullable = false, length = 50, name = "weather_max_temperature")
    private String maxTemperature;

    @Column(nullable = false, length = 50, name = "weather_humidity")
    private String humidity;

    @Column(nullable = false, name = "weather_date_time")
    private LocalDateTime dateTime;

    public Weather() {
    }
    public Weather(City city, String main, String description, String temperature, String feelsLike, String minTemperature, String maxTemperature, String humidity, LocalDateTime dateTime) {
        this.city = city;
        this.main = main;
        this.description = description;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.humidity = humidity;
        this.dateTime = dateTime;
    }

    public Weather(String main, String description, String temperature, String feelsLike, String minTemperature, String maxTemperature, String humidity, LocalDateTime dateTime) {
        this.main = main;
        this.description = description;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.humidity = humidity;
        this.dateTime = dateTime;
    }

    public Weather(Long id, String main, String description, String temperature, String feelsLike, String minTemperature, String maxTemperature, String humidity, LocalDateTime dateTime) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.humidity = humidity;
        this.dateTime = dateTime;
    }

    public Weather(Long id, City city, String main, String description) {
        this.id = id;
        this.city = city;
        this.main = main;
        this.description = description;
    }

    public Weather(Long id, String city, String description) {
        this.id = id;
        this.main = city;
        this.description = description;
    }
}
