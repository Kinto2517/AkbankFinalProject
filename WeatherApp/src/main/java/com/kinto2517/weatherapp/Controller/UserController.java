package com.kinto2517.weatherapp.Controller;

import com.kinto2517.weatherapp.Controller.Contract.UserControllerContract;
import com.kinto2517.weatherapp.Dao.UserRepository;
import com.kinto2517.weatherapp.Dto.UserSaveRequest;
import com.kinto2517.weatherapp.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kinto2517.weatherapp.Dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserControllerContract userControllerContract;

    @Autowired
    private final UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers () {
        List<UserDTO> userDTOList = userControllerContract.findAll();

        if (userDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(userDTOList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById (@PathVariable("id") Long id) {
        UserDTO userDTO = userControllerContract.findById(id);

        if (userDTO == null) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(userDTO);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<UserDTO>> deleteUser (@RequestParam("id") Long id) {
        userControllerContract.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser (@PathVariable("id") Long id , @RequestBody UserSaveRequest userSaveRequest) {
        UserDTO userDTO = userControllerContract.update(id,userSaveRequest);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<UserDTO> saveUser (@RequestBody UserSaveRequest userSaveRequest) {
        UserDTO userDTO = userControllerContract.save(userSaveRequest);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/saveWeb")
    public ResponseEntity<UserDTO> saveUserWeb (@RequestParam("username") String username, @RequestParam("password") String password) {
        UserSaveRequest userSaveRequest = new UserSaveRequest(username,password);
        UserDTO userDTO = userControllerContract.save(userSaveRequest);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/updateCity/{id}")
    public ResponseEntity<UserDTO> updateCity (@PathVariable("id") Long id, @RequestParam("cityid") Long cityid) {
        UserDTO userDTO = userControllerContract.updateWithCity(id,cityid);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/updateWeather/{id}")
    public ResponseEntity<UserDTO> updateWeather (@PathVariable("id") Long id, @RequestParam("weatherid") Long weatherid) {
        UserDTO userDTO = userControllerContract.updateWithWeatherForecast(id,weatherid);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/updateCities/{id}")
    public ResponseEntity<UserDTO> updateCities (@PathVariable("id") Long id, @RequestBody List<Long> cities) {
        UserDTO userDTO = userControllerContract.updateWithCities(id,cities);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/updateWeathers/{id}")
    public ResponseEntity<UserDTO> updateWeathers (@PathVariable("id") Long id, @RequestBody List<Long> weathers) {
        UserDTO userDTO = userControllerContract.updateWithWeatherForecasts(id,weathers);
        return ResponseEntity.ok(userDTO);
    }



}
