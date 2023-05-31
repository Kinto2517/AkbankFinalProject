package com.kinto2517.weatherapp.Controller;

import com.kinto2517.weatherapp.Controller.Contract.CityControllerContract;
import com.kinto2517.weatherapp.Dto.CityDTO;
import com.kinto2517.weatherapp.Dto.CitySaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
public class CityController {

    private final CityControllerContract cityControllerContract;

    @GetMapping("/all")
    public ResponseEntity<List<CityDTO>> getAllCities () {
        List<CityDTO> cityDTOList = cityControllerContract.findAll();
        if (cityDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(cityDTOList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById (@PathVariable("id") Long id) {
        CityDTO cityDTO = cityControllerContract.findById(id);
        if (cityDTO == null) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(cityDTO);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<CityDTO> saveCity (@RequestBody CitySaveRequest citySaveRequest) {
        CityDTO cityDTO = cityControllerContract.save(citySaveRequest);
        return ResponseEntity.ok(cityDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<CityDTO>> deleteCity (@RequestParam("id") Long id) {
        cityControllerContract.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CityDTO> updateCity (@PathVariable("id") Long id , @RequestBody CitySaveRequest citySaveRequest) {
        CityDTO cityDTO = cityControllerContract.update(id,citySaveRequest);
        return ResponseEntity.ok(cityDTO);
    }

    @PutMapping("/updateWeather/{id}")
    public ResponseEntity<CityDTO> updateWeather (@PathVariable("id") Long id, @RequestParam("weatherid") Long weatherid) {
        CityDTO cityDTO = cityControllerContract.updateWithWeatherForecast(id,weatherid);
        return ResponseEntity.ok(cityDTO);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<CityDTO> updateUser (@PathVariable("id") Long id, @RequestParam("userid") Long userid) {
        CityDTO cityDTO = cityControllerContract.updateWithUser(id,userid);
        return ResponseEntity.ok(cityDTO);
    }

    @PutMapping("/updateWeathers/{id}")
    public ResponseEntity<CityDTO> updateWeathers (@PathVariable("id") Long id, @RequestBody List<Long> weathers) {
        CityDTO cityDTO = cityControllerContract.updateWithWeatherForecasts(id,weathers);
        return ResponseEntity.ok(cityDTO);
    }


}


