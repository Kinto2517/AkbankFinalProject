package com.kinto2517.weatherapp.Dto;

import java.util.List;

public record CityDTO(Long id, String name, String country, String timezone, String population) {

    public CityDTO {
    }
}
