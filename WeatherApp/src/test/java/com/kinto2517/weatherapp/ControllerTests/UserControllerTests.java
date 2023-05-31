package com.kinto2517.weatherapp.ControllerTests;

import com.kinto2517.weatherapp.Controller.Contract.UserControllerContract;
import com.kinto2517.weatherapp.Controller.UserController;
import com.kinto2517.weatherapp.Dto.UserDTO;
import com.kinto2517.weatherapp.Dto.UserSaveRequest;
import com.kinto2517.weatherapp.Entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

    @Mock
    private UserControllerContract userControllerContract;

    @InjectMocks
    private UserController userController;

    @Test
    public void shouldGetAllUsers() {

        List<UserDTO> userDTOList = new ArrayList<>();

        UserDTO userDTO = new UserDTO(1L, "test", "test");

        userDTOList.add(userDTO);

        when(userControllerContract.findAll()).thenReturn(userDTOList);

        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(userDTOList, response.getBody());
    }

    @Test
    public void shouldGetUserById() {

        UserDTO userDTO = new UserDTO(1L, "test", "test");

        when(userControllerContract.findById(1L)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(userDTO, response.getBody());
    }

    @Test
    public void shouldSaveUser() {

        UserSaveRequest userSaveRequest = new UserSaveRequest("test", "test");

        UserDTO userDTO = new UserDTO(1L, "test", "test");

        when(userControllerContract.save(userSaveRequest)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.saveUser(userSaveRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(userDTO, response.getBody());
    }

    @Test
    public void shouldUpdateUser() {

        UserDTO existingUser = new UserDTO(1L, "test", "test");
        UserSaveRequest userUpdateRequest = new UserSaveRequest( "updatedName", "updatedEmail");

        when(userControllerContract.update(1L, userUpdateRequest)).thenReturn(existingUser);

        ResponseEntity<UserDTO> response = userController.updateUser(1L, userUpdateRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingUser, response.getBody());
    }

    @Test
    public void shouldDeleteUser() {

        ResponseEntity<List<UserDTO>> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldUpdateCity() {

        UserDTO existingUser = new UserDTO(1L, "test", "test");

        when(userControllerContract.updateWithCity(1L, 1L)).thenReturn(existingUser);

        ResponseEntity<UserDTO> response = userController.updateCity(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingUser, response.getBody());
    }

}
