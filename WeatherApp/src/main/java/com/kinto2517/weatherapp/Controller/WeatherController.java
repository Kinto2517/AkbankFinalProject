package com.kinto2517.weatherapp.Controller;

import com.kinto2517.weatherapp.Controller.Contract.WeatherControllerContract;
import com.kinto2517.weatherapp.Dto.WeatherDTO;
import com.kinto2517.weatherapp.Dto.WeatherSaveRequest;
import com.kinto2517.weatherapp.Entity.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {


    private final WeatherControllerContract weatherControllerContract;

    @GetMapping("/all")
    public ResponseEntity<List<WeatherDTO>> getAllWeather () {
        List<WeatherDTO> weatherDTOList = weatherControllerContract.findAll();

        if (weatherDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(weatherDTOList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeatherDTO> getWeatherById (@PathVariable("id") Long id) {
        WeatherDTO weatherDTO = weatherControllerContract.findById(id);

        if (weatherDTO == null) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(weatherDTO);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<List<WeatherDTO>> saveWeather (@RequestParam("cityid") Long cityid) {
        List<WeatherDTO> weatherDTO = weatherControllerContract.saveTemp(cityid);
        return ResponseEntity.ok(weatherDTO);
    }



}
