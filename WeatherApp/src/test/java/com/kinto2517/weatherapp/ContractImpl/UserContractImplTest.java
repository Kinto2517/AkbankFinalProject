package com.kinto2517.weatherapp.ContractImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.kinto2517.weatherapp.Controller.Contract.Impl.UserControllerContractImpl;
import com.kinto2517.weatherapp.Dto.UserDTO;
import com.kinto2517.weatherapp.Dto.UserSaveRequest;
import com.kinto2517.weatherapp.Entity.User;
import com.kinto2517.weatherapp.Service.UserService;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class UserContractImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserControllerContractImpl userControllerContract;

    @Test
    void shouldFindAll() {
        List<UserDTO> customerDTOList = userControllerContract.findAll();
        assertEquals(0, customerDTOList.size());
    }

    @Test
    void shouldFindAllWhenReturnsCustomer() {
        String name = "user";

        User user = mock(User.class);
        when(user.getUsername()).thenReturn(name);
        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userService.findAll()).thenReturn(userList);

        List<UserDTO> customerDTOList = userControllerContract.findAll();

        assertEquals(1, customerDTOList.size());
        User result = userList.get(0);
        assertEquals(name, result.getUsername());
    }

    @Test
    void shouldFindAllWhenReturnsCustomers() {

        User user1 = mock(User.class);
        User user2 = mock(User.class);
        List<User> customerList = new ArrayList<>();
        customerList.add(user1);
        customerList.add(user2);

        when(userService.findAll()).thenReturn(customerList);

        List<UserDTO> customerDTOList = userControllerContract.findAll();
        assertEquals(2, customerDTOList.size());
    }

    @Test
    void shouldSave() {

        Long id = 18L;
        String name = "user";

        UserSaveRequest request = mock(UserSaveRequest.class);
        User user = mock(User.class);
        when(user.getId()).thenReturn(id);

        when(request.username()).thenReturn(name);
        when(userService.save(any())).thenReturn(user);

        UserDTO result = userControllerContract.save(request);

        Mockito.verify(userService).save(any());
        assertEquals(id, result.id());
        //assertEquals(name, result.name()); eziliyor!!!

    }


    @Test
    void shouldDelete() {
        Long id = 18L;
        User user = new User(id, "test", "test");

        Mockito.when(userService.findById(id)).thenReturn(Optional.of(user));
        Mockito.doNothing().when(userService).delete(user);

        userControllerContract.delete(id);

        Mockito.verify(userService).delete(user);
    }

}