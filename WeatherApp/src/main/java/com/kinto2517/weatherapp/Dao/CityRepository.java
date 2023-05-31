package com.kinto2517.weatherapp.Dao;

import com.kinto2517.weatherapp.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT c FROM City c WHERE c.name = ?1")
    City findByName(String name);
}
