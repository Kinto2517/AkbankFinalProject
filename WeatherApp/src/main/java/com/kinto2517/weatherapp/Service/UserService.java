package com.kinto2517.weatherapp.Service;

import com.kinto2517.weatherapp.Base.BaseEntityService;
import com.kinto2517.weatherapp.Dao.CityRepository;
import com.kinto2517.weatherapp.Dao.UserRepository;
import com.kinto2517.weatherapp.Dao.WeatherRepository;
import com.kinto2517.weatherapp.Dto.UserSaveRequest;
import com.kinto2517.weatherapp.Entity.City;
import com.kinto2517.weatherapp.Entity.User;
import com.kinto2517.weatherapp.Entity.Weather;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


@Service
public class UserService extends BaseEntityService<User, UserRepository> {

    private final UserRepository repository;
    private final CityRepository cityRepository;

    private final WeatherRepository weatherRepository;

    private static final Logger logger = LogManager.getLogger(UserService.class);

    public UserService(UserRepository repository, UserRepository repository1, CityRepository cityRepository, WeatherRepository weatherRepository) {
        super(repository);
        this.repository = repository1;
        this.cityRepository = cityRepository;
        this.weatherRepository = weatherRepository;
    }

    public User update(Long id, UserSaveRequest userSaveRequest) {
        User user = findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userSaveRequest.username());
        user.setPassword(userSaveRequest.password());

        logger.info("User updated: " + user.toString());

        return save(user);
    }

    public User updateWithCityIds(Long id, List<Long> cityIds) {
        User user = findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        List<City> cities = user.getCityList();

        for (Long cityId : cityIds) {
            City city = cityRepository.findById(cityId).orElseThrow(() -> new RuntimeException("City not found"));
            if (!cities.contains(city)) {
                cities.add(city);
            } else {
                throw new RuntimeException("City already exists for the user");
            }
        }

        user.setCityList(cities);
        save(user);
        logger.info("User updated: " + user.toString());
        return user;
    }

    public User updateWithWeatherIds(Long id, List<Long> weatherIds) {
        User user = findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        List<Weather> weathers = user.getWeatherList();

        for (Long weatherId : weatherIds) {
            Weather weather = weatherRepository.findById(weatherId).orElseThrow(() -> new RuntimeException("Weather not found"));
            if (!weathers.contains(weather)) {
                weathers.add(weather);
            } else {
                throw new RuntimeException("Weather already exists for the user");
            }

        }

        user.setWeatherList(weathers);
        save(user);
        logger.info("User updated: " + user.toString());
        return user;
    }

    public User updateWithWeatherId(Long id, Long weatherId) {
        User user = findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        List<Weather> weathers = user.getWeatherList();

        Weather weather = weatherRepository.findById(weatherId).orElseThrow(() -> new RuntimeException("Weather not found"));

        if (!weathers.contains(weather)) {
            weathers.add(weather);
        } else {
            throw new RuntimeException("Weather already exists for the user");
        }

        user.setWeatherList(weathers);
        save(user);
        logger.info("User updated: " + user.toString());
        return user;
    }

    public User updateWithCityId(Long id, Long cityId) {
        User user = findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        List<City> cities = user.getCityList();

        City city = cityRepository.findById(cityId).orElseThrow(() -> new RuntimeException("City not found"));
        if (!cities.contains(city)) {
            cities.add(city);
        } else {
            throw new RuntimeException("City already exists for the user");
        }

        user.setCityList(cities);
        save(user);
        logger.info("User updated: " + user.toString());
        return user;
    }

}

