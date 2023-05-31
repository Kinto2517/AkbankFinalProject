package com.kinto2517.weatherapp.Controller.Contract;

import com.kinto2517.weatherapp.Dto.CitySaveRequest;
import com.kinto2517.weatherapp.Dto.CityDTO;

import java.util.List;

public interface CityControllerContract {

    List<CityDTO> findAll();

    CityDTO save(CitySaveRequest citySaveRequest);

    CityDTO findById(Long id);

    CityDTO findByName(String name);

    CityDTO update(Long id, CitySaveRequest citySaveRequest);

    CityDTO updateWithWeatherForecast(Long id, Long weatherForecastId);

    CityDTO updateWithUser(Long id, Long userId);

    CityDTO updateWithWeatherForecasts(Long id, List<Long> weatherForecastIds);

    CityDTO updateWithUsers(Long id, List<Long> userIds);

    void delete(Long id);





}
