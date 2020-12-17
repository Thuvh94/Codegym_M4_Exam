package com.example.demo.service.city;

import com.example.demo.model.City;

import java.util.Optional;

public interface ICityService {
    Iterable<City> findAll();

    City save(City city);

    Optional<City> findById(Long cityId);

    void remove(Long cityId);

}
