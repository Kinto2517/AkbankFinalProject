package com.kinto2517.weatherapp.Controller.Contract;

import com.kinto2517.weatherapp.Dto.UserDTO;
import com.kinto2517.weatherapp.Dto.UserSaveRequest;

import java.util.List;

public interface UserControllerContract {

    UserDTO save(UserSaveRequest userSaveRequest);

    UserDTO findById(Long id);

    UserDTO update(Long id, UserSaveRequest userSaveRequest);

    void delete(Long id);

    List<UserDTO> findAll();

    UserDTO updateWithCities(Long id, List<Long> cityIds);

    UserDTO updateWithCity(Long id, Long cityId);

    UserDTO updateWithWeatherForecasts(Long id, List<Long> weatherForecastIds);

    UserDTO updateWithWeatherForecast(Long id, Long weatherForecastId);


}

