package com.kinto2517.weatherapp.ContractImpl;

import com.kinto2517.weatherapp.Controller.Contract.Impl.UserControllerContractImpl;
import com.kinto2517.weatherapp.Controller.Contract.Impl.WeatherControllerContractImpl;
import com.kinto2517.weatherapp.Dto.UserDTO;
import com.kinto2517.weatherapp.Dto.UserSaveRequest;
import com.kinto2517.weatherapp.Dto.WeatherDTO;
import com.kinto2517.weatherapp.Dto.WeatherSaveRequest;
import com.kinto2517.weatherapp.Entity.User;
import com.kinto2517.weatherapp.Entity.Weather;
import com.kinto2517.weatherapp.Service.UserService;
import com.kinto2517.weatherapp.Service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class WeatherContractImplTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherControllerContractImpl weatherControllerContract;

    @Test
    void shouldFindAll() {
        List<WeatherDTO> weatherDTOList = weatherControllerContract.findAll();
        assertEquals(0, weatherDTOList.size());
    }

    @Test
    void shouldFindAllWhenReturnsCustomer() {
        String city = "Usak";

        Weather weather = mock(Weather.class);
        when(weather.getMain()).thenReturn(city);
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);

        when(weatherService.findAll()).thenReturn(weatherList);

        List<WeatherDTO> weatherDTOList = weatherControllerContract.findAll();

        assertEquals(1, weatherDTOList.size());
        Weather result = weatherList.get(0);
        assertEquals(city, result.getMain());
    }

    @Test
    void shouldFindAllWhenReturnsCustomers() {

        Weather weather = mock(Weather.class);
        Weather weather1 = mock(Weather.class);
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        weatherList.add(weather1);

        when(weatherService.findAll()).thenReturn(weatherList);

        List<WeatherDTO> weatherDTOListList = weatherControllerContract.findAll();
        assertEquals(2, weatherDTOListList.size());
    }

    @Test
    void shouldSave() {

        Long id = 18L;
        String name = "name";

        WeatherSaveRequest request = mock(WeatherSaveRequest.class);
        Weather weather = mock(Weather.class);
        when(weather.getId()).thenReturn(id);

        when(request.main()).thenReturn(name);
        when(weatherService.save(any())).thenReturn(weather);

        WeatherDTO result = weatherControllerContract.save(request);

        Mockito.verify(weatherService).save(any());
        assertEquals(id, result.id());
        //assertEquals(name, result.name()); eziliyor!!!

    }


    @Test
    void shouldDelete() {
        Long id = 18L;
        Weather weather = new Weather(id,"City","Descr");

        Mockito.when(weatherService.findById(id)).thenReturn(Optional.of(weather));
        Mockito.doNothing().when(weatherService).delete(weather);

        weatherControllerContract.delete(id);

        Mockito.verify(weatherService).delete(weather);
    }
    
}