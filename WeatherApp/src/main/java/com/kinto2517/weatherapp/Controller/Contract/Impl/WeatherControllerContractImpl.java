package com.kinto2517.weatherapp.Controller.Contract.Impl;

import com.kinto2517.weatherapp.Controller.Contract.WeatherControllerContract;
import com.kinto2517.weatherapp.Dto.WeatherDTO;
import com.kinto2517.weatherapp.Dto.WeatherSaveRequest;
import com.kinto2517.weatherapp.Entity.Weather;
import com.kinto2517.weatherapp.Mapper.WeatherMapper;
import com.kinto2517.weatherapp.Service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherControllerContractImpl implements WeatherControllerContract {

    private final WeatherService weatherService;
    private static final Logger logger = LogManager.getLogger(WeatherControllerContractImpl.class);
    @Override
    public List<WeatherDTO> findAll() {
        List<Weather> weathers = weatherService.findAll();
        logger.info("findAll() method called");
        return WeatherMapper.INSTANCE.convertToWeatherDTOs(weathers);
    }

    @Override
    public WeatherDTO save(WeatherSaveRequest weatherSaveRequest) {
        Weather weather = WeatherMapper.INSTANCE.convertToWeather(weatherSaveRequest);
        weather = weatherService.save(weather);
        logger.info("save() method called");
        return WeatherMapper.INSTANCE.convertToWeatherDTO(weather);
    }

    @Override
    public WeatherDTO findById(Long id) {
        Weather weather = weatherService.findById(id).orElseThrow(() -> new RuntimeException("Weather not found"));
        if (weather == null) {
            logger.error("Weather not found");
            throw new RuntimeException("Weather not found");
        }
        logger.info("findById() method called");
        return WeatherMapper.INSTANCE.convertToWeatherDTO(weather);
    }

    @Override
    public WeatherDTO update(Long id, WeatherSaveRequest weatherSaveRequest) {
        Weather updatedWeather = weatherService.update(id, weatherSaveRequest);
        logger.info("update() method called");
        return WeatherMapper.INSTANCE.convertToWeatherDTO(updatedWeather);
    }

    @Override
    public void delete(Long id) {
        Weather weather = weatherService.findById(id).orElseThrow(() -> new RuntimeException("Weather not found"));
        weatherService.delete(weather);
        logger.info("delete() method called");
    }

    @Override
    public List<WeatherDTO> saveTemp(Long cityid) {
        List<Weather> weathers = weatherService.saveTemp(cityid);
        logger.info("saveTemp() method called");
        return WeatherMapper.INSTANCE.convertToWeatherDTOs(weathers);
    }
}
