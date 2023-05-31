package com.kinto2517.weatherapp.Service;

import com.kinto2517.weatherapp.Api.MainData;
import com.kinto2517.weatherapp.Api.OpenWeather;
import com.kinto2517.weatherapp.Api.OpenWeatherMapResponse;
import com.kinto2517.weatherapp.Api.WeatherData;
import com.kinto2517.weatherapp.Base.BaseEntityService;
import com.kinto2517.weatherapp.Dao.CityRepository;
import com.kinto2517.weatherapp.Dao.WeatherRepository;
import com.kinto2517.weatherapp.Dto.WeatherSaveRequest;
import com.kinto2517.weatherapp.Entity.City;
import com.kinto2517.weatherapp.Entity.Weather;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService extends BaseEntityService<Weather, WeatherRepository> {

    private final WeatherRepository repository;

    private final CityRepository cityRepository;

    private static final Logger logger = LogManager.getLogger(WeatherService.class);
    private static final String API_KEY = "1030322fb02d2e48c8426c5cbcc27781";
    private static final String Base_URL = "http://api.openweathermap.org/data/2.5/forecast";

    public WeatherService(WeatherRepository repository, WeatherRepository repository1, CityRepository cityRepository) {
        super(repository);
        this.repository = repository1;
        this.cityRepository = cityRepository;
    }

    public List<Weather> saveTemp(Long cityId) {
        City city = cityRepository.findById(cityId).orElseThrow(() -> new RuntimeException("City not found"));

        String url = Base_URL + "?q=" + city.getName() + "&appid=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();

        OpenWeatherMapResponse weatherResponse = restTemplate.getForObject(url, OpenWeatherMapResponse.class);

        List<WeatherData> weatherDataList = weatherResponse.getList();
        List<Weather> savedWeathers = new ArrayList<>();



        for (WeatherData weatherData : weatherDataList) {
            MainData mainData = weatherData.getMain();
            OpenWeather openWeather = weatherData.getWeather().get(0);
            String date = weatherData.getDt_txt();
            LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


            Weather savedWeather = new Weather();
            savedWeather.setCity(city);
            savedWeather.setMain(openWeather.getMain());
            savedWeather.setDescription(openWeather.getDescription());
            savedWeather.setTemperature(String.valueOf(mainData.getTemp()));
            savedWeather.setFeelsLike(String.valueOf(mainData.getFeels_like()));
            savedWeather.setMinTemperature(String.valueOf(mainData.getTemp_min()));
            savedWeather.setMaxTemperature(String.valueOf(mainData.getTemp_max()));
            savedWeather.setHumidity(String.valueOf(mainData.getHumidity()));
            savedWeather.setDateTime(dateTime);

            city.getWeatherList().add(savedWeather);
            savedWeathers.add(savedWeather);
        }

        cityRepository.save(city);
        for (Weather weather : savedWeathers) {
            logger.info("Weather: " + weather);
            save(weather);
        }

        logger.info("Weather saved successfully");
        logger.info("City: " + city);
        logger.info("City weather list: " + city.getWeatherList());

        return savedWeathers;
    }

    public Weather update(Long id, WeatherSaveRequest weatherSaveRequest) {
        Weather weather = findById(id).orElseThrow(() -> new RuntimeException("Weather not found"));
        City city = cityRepository.findById(weatherSaveRequest.cityId()).orElseThrow(() -> new RuntimeException("City not found"));

        weather.setMain(weatherSaveRequest.main());
        weather.setDescription(weatherSaveRequest.description());
        weather.setTemperature(weatherSaveRequest.temperature());
        weather.setFeelsLike(weatherSaveRequest.feelsLike());
        weather.setMinTemperature(weatherSaveRequest.minTemperature());
        weather.setMaxTemperature(weatherSaveRequest.maxTemperature());
        weather.setHumidity(weatherSaveRequest.humidity());

        List<Weather> weathers = city.getWeatherList();
        weathers.add(weather);
        city.setWeatherList(weathers);
        save(weather);
        cityRepository.save(city);

        logger.info("Weather updated successfully");
        logger.info("Weather: " + weather);
        logger.info("City: " + city);
        logger.info("City weather list: " + city.getWeatherList());
        return weather;
    }

}
