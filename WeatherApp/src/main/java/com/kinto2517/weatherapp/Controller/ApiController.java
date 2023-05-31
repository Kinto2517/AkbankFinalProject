package com.kinto2517.weatherapp.Controller;

import com.kinto2517.weatherapp.Controller.Contract.CityControllerContract;
import com.kinto2517.weatherapp.Controller.Contract.UserControllerContract;
import com.kinto2517.weatherapp.Controller.Contract.WeatherControllerContract;
import com.kinto2517.weatherapp.Dao.CityRepository;
import com.kinto2517.weatherapp.Dao.UserRepository;
import com.kinto2517.weatherapp.Dao.WeatherRepository;
import com.kinto2517.weatherapp.Dto.CityDTO;
import com.kinto2517.weatherapp.Dto.UserDTO;
import com.kinto2517.weatherapp.Dto.WeatherDTO;
import com.kinto2517.weatherapp.Entity.City;
import com.kinto2517.weatherapp.Entity.User;
import com.kinto2517.weatherapp.Entity.Weather;
import com.kinto2517.weatherapp.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/pages")
public class ApiController {

    private final UserControllerContract userControllerContract;

    private final WeatherControllerContract weatherControllerContract;

    private final CityControllerContract cityControllerContract;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherRepository weatherRepository;


    public ApiController(UserControllerContract userControllerContract, WeatherControllerContract weatherControllerContract, CityControllerContract cityControllerContract) {
        this.userControllerContract = userControllerContract;
        this.weatherControllerContract = weatherControllerContract;
        this.cityControllerContract = cityControllerContract;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model) {

        List<CityDTO> cityDTOList = cityControllerContract.findAll();
        List<WeatherDTO> weatherDTOList = weatherControllerContract.findAll();
        model.addAttribute("cityList", cityDTOList);
        model.addAttribute("weatherList", weatherDTOList);

        return "dashboard";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/favorites")
    public String favoritesPage(Model model) {

        User user = userRepository.findById(9L).orElse(null);

        List<City> cityList = user.getCityList();
        List<Weather> weatherList = user.getWeatherList();

        model.addAttribute("cityList", cityList);
        model.addAttribute("weatherList", weatherList);

        System.out.println();

        return "favorites";
    }

}
