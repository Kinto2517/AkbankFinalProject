package com.kinto2517.weatherapp.Controller.Contract;

import com.kinto2517.weatherapp.Dto.WeatherDTO;
import com.kinto2517.weatherapp.Dto.WeatherSaveRequest;

import java.util.List;

public interface WeatherControllerContract {

    List<WeatherDTO> findAll();

    WeatherDTO save(WeatherSaveRequest weatherSaveRequest);

    WeatherDTO findById(Long id);

    WeatherDTO update(Long id, WeatherSaveRequest weatherSaveRequest);

    void delete(Long id);

    List<WeatherDTO> saveTemp(Long cityid);

}
