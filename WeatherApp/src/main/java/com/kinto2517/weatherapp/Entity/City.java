package com.kinto2517.weatherapp.Entity;

import com.kinto2517.weatherapp.Base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "city")
@ToString(exclude = {"userList", "weatherList"})
public class City extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "city_name", nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50, name = "city_country")
    private String country;

    @Column(nullable = false, length = 50, name = "city_timezone")
    private String timezone;

    @Column(nullable = false, length = 50, name = "city_population")
    private String population;

    @ManyToMany(mappedBy = "cityList")
    private List<User> userList;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Weather> weatherList;

    public City() {
    }

    public City(String name, String country, String timezone, String population) {
        this.name = name;
        this.country = country;
        this.timezone = timezone;
        this.population = population;
    }

    public City(String name, String country, String timezone, String population, List<User> userList, List<Weather> weatherList) {
        this.name = name;
        this.country = country;
        this.timezone = timezone;
        this.population = population;
        this.userList = userList;
        this.weatherList = weatherList;
    }


    public City(Long id, String name, String country, String timezone, String population) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.timezone = timezone;
        this.population = population;
    }

    public City(Long id, String city) {
        this.id = id;
        this.name = city;
    }
}
