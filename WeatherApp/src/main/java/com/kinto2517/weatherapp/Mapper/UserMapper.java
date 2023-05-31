package com.kinto2517.weatherapp.Mapper;

import com.kinto2517.weatherapp.Dto.UserDTO;
import com.kinto2517.weatherapp.Dto.UserSaveRequest;
import com.kinto2517.weatherapp.Entity.City;
import com.kinto2517.weatherapp.Entity.User;
import com.kinto2517.weatherapp.Entity.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User convertToUser(UserSaveRequest userSaveRequest);

    UserDTO convertToUserDTO(User user);

    List<UserDTO> convertToUserDTOs(List<User> users);

    User userDTOToUser(UserDTO userdto);
}
