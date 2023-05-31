package com.kinto2517.weatherapp.Dto;

import java.util.List;

public record CitySaveRequest(String name, String country, String timezone, String population) {
}

