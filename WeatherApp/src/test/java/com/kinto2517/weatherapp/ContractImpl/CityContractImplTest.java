package com.kinto2517.weatherapp.ContractImpl;

import com.kinto2517.weatherapp.Controller.Contract.Impl.CityControllerContractImpl;
import com.kinto2517.weatherapp.Controller.Contract.Impl.UserControllerContractImpl;
import com.kinto2517.weatherapp.Dto.CityDTO;
import com.kinto2517.weatherapp.Dto.CitySaveRequest;
import com.kinto2517.weatherapp.Dto.UserDTO;
import com.kinto2517.weatherapp.Dto.UserSaveRequest;
import com.kinto2517.weatherapp.Entity.City;
import com.kinto2517.weatherapp.Entity.User;
import com.kinto2517.weatherapp.Service.CityService;
import com.kinto2517.weatherapp.Service.UserService;
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
class CityContractImplTest {

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityControllerContractImpl cityControllerContract;

    @Test
    void shouldFindAll() {
        List<CityDTO> cityDTOList = cityControllerContract.findAll();
        assertEquals(0, cityDTOList.size());
    }

    @Test
    void shouldFindAllWhenReturnsCustomer() {
        String city = "Usak";

        City city1 = mock(City.class);
        when(city1.getName()).thenReturn(city);
        List<City> cityList = new ArrayList<>();
        cityList.add(city1);

        when(cityService.findAll()).thenReturn(cityList);

        List<CityDTO> cityDTOList = cityControllerContract.findAll();

        assertEquals(1, cityDTOList.size());
        City result = cityList.get(0);
        assertEquals(city, result.getName());
    }

    @Test
    void shouldFindAllWhenReturnsCustomers() {

        City city1 = mock(City.class);
        City city2 = mock(City.class);
        List<City> cityList = new ArrayList<>();
        cityList.add(city1);
        cityList.add(city2);

        when(cityService.findAll()).thenReturn(cityList);

        List<CityDTO> weatherDTOListList = cityControllerContract.findAll();
        assertEquals(2, weatherDTOListList.size());
    }

    @Test
    void shouldSave() {

        Long id = 18L;
        String name = "city";

        CitySaveRequest request = mock(CitySaveRequest.class);
        City city = mock(City.class);
        when(city.getId()).thenReturn(id);

        when(request.name()).thenReturn(name);
        when(cityService.save(any())).thenReturn(city);

        CityDTO result = cityControllerContract.save(request);

        Mockito.verify(cityService).save(any());
        assertEquals(id, result.id());
    }


    @Test
    void shouldDelete() {
        Long id = 18L;
        City city = new City(id, "city");

        Mockito.when(cityService.findById(id)).thenReturn(Optional.of(city));
        Mockito.doNothing().when(cityService).delete(city);

        cityControllerContract.delete(id);

        Mockito.verify(cityService).delete(city);
    }
    
}