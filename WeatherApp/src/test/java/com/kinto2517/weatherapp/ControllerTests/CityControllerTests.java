package com.kinto2517.weatherapp.ControllerTests;

import com.kinto2517.weatherapp.Controller.CityController;
import com.kinto2517.weatherapp.Controller.Contract.CityControllerContract;
import com.kinto2517.weatherapp.Dto.CityDTO;
import com.kinto2517.weatherapp.Dto.CitySaveRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityControllerTests {

    @Mock
    private CityControllerContract cityControllerContract;

    @InjectMocks
    private CityController cityController;

    @Test
    public void shouldGetAllCities() {
        List<CityDTO> cityDTOList = new ArrayList<>();

        CityDTO cityDTO = new CityDTO(1L, "test", "test", "test", "100");

        cityDTOList.add(cityDTO);

        when(cityControllerContract.findAll()).thenReturn(cityDTOList);

        ResponseEntity<List<CityDTO>> response = cityController.getAllCities();

        assertEquals(cityDTOList, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void shouldGetCityById() {
        CityDTO cityDTO = new CityDTO(1L, "test", "test", "test", "100");

        when(cityControllerContract.findById(1L)).thenReturn(cityDTO);

        ResponseEntity<CityDTO> response = cityController.getCityById(1L);

        assertEquals(cityDTO, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void shouldSaveCity() {
        CitySaveRequest citySaveRequest = new CitySaveRequest("test", "test", "test", "100");

        CityDTO cityDTO = new CityDTO(1L, "test", "test", "test", "100");

        when(cityControllerContract.save(citySaveRequest)).thenReturn(cityDTO);

        ResponseEntity<CityDTO> response = cityController.saveCity(citySaveRequest);

        assertEquals(cityDTO, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void shouldUpdateCity() {
        CitySaveRequest citySaveRequest = new CitySaveRequest("test", "test", "test", "100");

        CityDTO cityDTO = new CityDTO(1L, "test", "test", "test", "100");

        when(cityControllerContract.update(1L, citySaveRequest)).thenReturn(cityDTO);

        ResponseEntity<CityDTO> response = cityController.updateCity(1L, citySaveRequest);

        assertEquals(cityDTO, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void shouldDeleteCity() {
        CityDTO cityDTO = new CityDTO(1L, "test", "test", "test", "100");

        ResponseEntity<List<CityDTO>> response = cityController.deleteCity(1L);

        assertEquals(200, response.getStatusCodeValue());
    }

}
