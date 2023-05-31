package com.kinto2517.weatherapp.Controller.Contract.Impl;

import com.kinto2517.weatherapp.Controller.Contract.UserControllerContract;
import com.kinto2517.weatherapp.Dto.UserDTO;
import com.kinto2517.weatherapp.Dto.UserSaveRequest;
import com.kinto2517.weatherapp.Entity.User;
import com.kinto2517.weatherapp.Mapper.UserMapper;
import com.kinto2517.weatherapp.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
@RequiredArgsConstructor
public class UserControllerContractImpl implements UserControllerContract {

    private final UserService userService;

    @Override
    public UserDTO save(UserSaveRequest userSaveRequest) {
        User user = UserMapper.INSTANCE.convertToUser(userSaveRequest);
        user = userService.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO update(Long id, UserSaveRequest userSaveRequest) {
        User updatedUser = userService.update(id, userSaveRequest);
        return UserMapper.INSTANCE.convertToUserDTO(updatedUser);
    }

    @Override
    public void delete(Long id) {
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userService.delete(user);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userService.findAll();
        return UserMapper.INSTANCE.convertToUserDTOs(users);
    }

    @Override
    public UserDTO updateWithCities(Long id, List<Long> cityIds) {
        User updatedUser = userService.updateWithCityIds(id, cityIds);
        return UserMapper.INSTANCE.convertToUserDTO(updatedUser);
    }

    @Override
    public UserDTO updateWithCity(Long id, Long cityId) {
        User updatedUser = userService.updateWithCityId(id, cityId);
        return UserMapper.INSTANCE.convertToUserDTO(updatedUser);
    }

    @Override
    public UserDTO updateWithWeatherForecasts(Long id, List<Long> weatherForecastIds) {
        User updatedUser = userService.updateWithWeatherIds(id, weatherForecastIds);
        return UserMapper.INSTANCE.convertToUserDTO(updatedUser);
    }

    @Override
    public UserDTO updateWithWeatherForecast(Long id, Long weatherForecastId) {
        User updatedUser = userService.updateWithWeatherId(id, weatherForecastId);
        return UserMapper.INSTANCE.convertToUserDTO(updatedUser);
    }
}
