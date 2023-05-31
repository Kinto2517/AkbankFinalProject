package com.kinto2517.weatherapp.Entity;

import com.kinto2517.weatherapp.Base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "`user`")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "user_name", nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 50, name = "user_password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_city",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id")
    )
    private List<City> cityList;

    @ManyToMany
    @JoinTable(
            name = "user_weather",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "weather_id")
    )
    private List<Weather> weatherList;

    public User() {
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, List<City> cityList, List<Weather> weatherList) {
        this.username = username;
        this.password = password;
        this.cityList = cityList;
        this.weatherList = weatherList;
    }

    public User(Long id, String username, String password, List<City> cityList, List<Weather> weatherList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cityList = cityList;
        this.weatherList = weatherList;
    }


    public User(Long userId, String username, String password) {
        this.id = userId;
        this.username = username;
        this.password = password;
    }
}
