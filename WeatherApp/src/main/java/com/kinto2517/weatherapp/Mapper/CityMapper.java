package com.kinto2517.weatherapp.Mapper;

import com.kinto2517.weatherapp.Dto.CityDTO;
import com.kinto2517.weatherapp.Dto.CitySaveRequest;
import com.kinto2517.weatherapp.Entity.City;
import com.kinto2517.weatherapp.Entity.User;
import com.kinto2517.weatherapp.Entity.Weather;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    City convertToCity(CitySaveRequest citySaveRequest);

    CityDTO convertToCityDTO(City city);

    List<CityDTO> convertToCityDTOs(List<City> cities);

}
