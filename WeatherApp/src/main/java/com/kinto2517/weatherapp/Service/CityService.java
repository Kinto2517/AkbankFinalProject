package com.kinto2517.weatherapp.Service;

import com.kinto2517.weatherapp.Base.BaseEntityService;
import com.kinto2517.weatherapp.Dao.CityRepository;
import com.kinto2517.weatherapp.Dao.UserRepository;
import com.kinto2517.weatherapp.Dao.WeatherRepository;
import com.kinto2517.weatherapp.Dto.CitySaveRequest;
import com.kinto2517.weatherapp.Entity.City;
import com.kinto2517.weatherapp.Entity.User;
import com.kinto2517.weatherapp.Entity.Weather;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Service
public class CityService extends BaseEntityService<City, CityRepository> {

    private final CityRepository repository;

    private final UserRepository userRepository;

    private final WeatherRepository weatherRepository;

    private static final Logger logger = LogManager.getLogger(CityService.class);

    public CityService(CityRepository repository, CityRepository repository1, UserRepository userRepository, WeatherRepository weatherRepository) {
        super(repository);
        this.repository = repository1;
        this.userRepository = userRepository;
        this.weatherRepository = weatherRepository;
    }

    public City update(Long id, CitySaveRequest citySaveRequest) {
        City city = findById(id).orElseThrow(() -> new RuntimeException("City not found"));
        city.setName(citySaveRequest.name());
        city.setCountry(citySaveRequest.country());
        city.setTimezone(citySaveRequest.timezone());
        city.setPopulation(citySaveRequest.population());
        logger.info("City updated: " + city.toString());
        return save(city);
    }

    public City findByName(String name) {
        logger.info("City found: " + name);
        return repository.findByName(name);
    }

    public City updateWithWeatherForecasts(Long id, List<Long> weatherForecastIds) {
        City city = findById(id).orElseThrow(() -> new RuntimeException("City not found"));
        List<Weather> weathers = city.getWeatherList();

        for (Long weatherId : weatherForecastIds) {
            Weather weather = weatherRepository.findById(weatherId).orElseThrow(() -> new RuntimeException("Weather not found"));
            weathers.add(weather);
        }

        city.setWeatherList(weathers);
        save(city);
        logger.info("City updated: " + city.toString());
        return city;
    }

    public City updateWithUsers(Long id, List<Long> userIds) {
        City city = findById(id).orElseThrow(() -> new RuntimeException("City not found"));

        List<User> users = city.getUserList();

        for (Long userId : userIds) {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            users.add(user);
        }

        city.setUserList(users);
        save(city);
        logger.info("City updated: " + city.toString());
        return city;
    }

    public City updateWithWeatherForecast(Long id, Long weatherForecastId) {
        City city = findById(id).orElseThrow(() -> new RuntimeException("City not found"));
        Weather weather = weatherRepository.findById(weatherForecastId).orElseThrow(() -> new RuntimeException("Weather not found"));
        city.getWeatherList().add(weather);
        save(city);
        logger.info("City updated: " + city.toString());
        return city;
    }

    public City updateWithUser(Long id, Long userId) {
        City city = findById(id).orElseThrow(() -> new RuntimeException("City not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        city.getUserList().add(user);
        save(city);
        logger.info("City updated: " + city.toString());
        return city;
    }

}
