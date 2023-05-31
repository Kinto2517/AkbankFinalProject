package com.kinto2517.weatherapp.Controller.Contract.Impl;

import com.kinto2517.weatherapp.Controller.Contract.CityControllerContract;
import com.kinto2517.weatherapp.Dto.CityDTO;
import com.kinto2517.weatherapp.Dto.CitySaveRequest;
import com.kinto2517.weatherapp.Entity.City;
import com.kinto2517.weatherapp.Mapper.CityMapper;
import com.kinto2517.weatherapp.Service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityControllerContractImpl implements CityControllerContract {

    private final CityService cityService;

    private static final Logger logger = LogManager.getLogger(CityControllerContractImpl.class);

    @Override
    public List<CityDTO> findAll() {
        List<City> cities = cityService.findAll();
        if (cities == null) {
            logger.error("Cities not found");
            throw new RuntimeException("Cities not found");
        }
        logger.info("Cities found successfully");
        return CityMapper.INSTANCE.convertToCityDTOs(cities);
    }

    @Override
    public CityDTO save(CitySaveRequest citySaveRequest) {
        City city = CityMapper.INSTANCE.convertToCity(citySaveRequest);
        city = cityService.save(city);
        logger.info("City saved successfully");
        return CityMapper.INSTANCE.convertToCityDTO(city);
    }

    @Override
    public CityDTO findById(Long id) {
        City city = cityService.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
        if (city == null) {
            logger.error("City not found");
            throw new RuntimeException("City not found");
        }
        logger.info("City found successfully");
        return CityMapper.INSTANCE.convertToCityDTO(city);
    }

    @Override
    public CityDTO findByName(String name) {
        City city = cityService.findByName(name);
        if (city == null) {
            logger.error("City not found");
            throw new RuntimeException("City not found");
        }
        logger.info("City found successfully");
        return CityMapper.INSTANCE.convertToCityDTO(city);
    }

    @Override
    public CityDTO update(Long id, CitySaveRequest citySaveRequest) {
        City updatedCity = cityService.update(id, citySaveRequest);
        if (updatedCity == null) {
            logger.error("City not found");
            throw new RuntimeException("City not found");
        }
        logger.info("City updated successfully");
        return CityMapper.INSTANCE.convertToCityDTO(updatedCity);
    }

    @Override
    public CityDTO updateWithWeatherForecast(Long id, Long weatherForecastId) {
        City updatedCity = cityService.updateWithWeatherForecast(id, weatherForecastId);
        return CityMapper.INSTANCE.convertToCityDTO(updatedCity);
    }

    @Override
    public CityDTO updateWithUser(Long id, Long userId) {
        City updatedCity = cityService.updateWithUser(id, userId);
        return CityMapper.INSTANCE.convertToCityDTO(updatedCity);
    }

    @Override
    public CityDTO updateWithWeatherForecasts(Long id, List<Long> weatherForecastIds) {
        City updatedCity = cityService.updateWithWeatherForecasts(id, weatherForecastIds);
        return CityMapper.INSTANCE.convertToCityDTO(updatedCity);
    }

    @Override
    public CityDTO updateWithUsers(Long id, List<Long> userIds) {
        City updatedCity = cityService.updateWithUsers(id, userIds);
        return CityMapper.INSTANCE.convertToCityDTO(updatedCity);
    }

    @Override
    public void delete(Long id) {
        City city = cityService.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
        cityService.delete(city);
    }
}
