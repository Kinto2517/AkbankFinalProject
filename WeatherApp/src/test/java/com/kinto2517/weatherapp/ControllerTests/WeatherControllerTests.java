package com.kinto2517.weatherapp.ControllerTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kinto2517.weatherapp.Controller.Contract.WeatherControllerContract;
import com.kinto2517.weatherapp.Controller.WeatherController;
import com.kinto2517.weatherapp.Dto.WeatherDTO;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTests {

    @Mock
    private WeatherControllerContract weatherControllerContract;

    @InjectMocks
    private WeatherController weatherController;

    @Test
    public void shouldGetAllWeather() {
        List<WeatherDTO> weatherDTOList = new ArrayList<>();

        when(weatherControllerContract.findAll()).thenReturn(weatherDTOList);

        ResponseEntity<List<WeatherDTO>> response = weatherController.getAllWeather();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void shouldGetWeatherById() {
        WeatherDTO weatherDTO = null;

        when(weatherControllerContract.findById(1L)).thenReturn(weatherDTO);

        ResponseEntity<WeatherDTO> response = weatherController.getWeatherById(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void shouldSaveWeather() {
        List<WeatherDTO> weatherDTOList = new ArrayList<>();

        when(weatherControllerContract.saveTemp(1L)).thenReturn(weatherDTOList);

        ResponseEntity<List<WeatherDTO>> response = weatherController.saveWeather(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(weatherDTOList, response.getBody());
    }

    @Test
    public void shouldGetAllWeatherWithRecord() throws Exception {
        List<WeatherDTO> weatherDTOList = new ArrayList<>();


        LocalDateTime localDateTime = LocalDateTime.now();
        WeatherDTO dummyWeather = new WeatherDTO(1L, 25L, "Sunny", "Hey", "H", "H", "H", "H", "H", localDateTime);

        weatherDTOList.add(dummyWeather);

        when(weatherControllerContract.findAll()).thenReturn(weatherDTOList);

        ResponseEntity<List<WeatherDTO>> response = weatherController.getAllWeather();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(weatherDTOList, response.getBody());
    }



}
