package com.example.demo.service.city;

import com.example.demo.model.City;
import com.example.demo.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements ICityService{
    @Autowired
    private ICityRepository cityRepository;
    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Optional<City> findById(Long cityId) {
        return cityRepository.findById(cityId);
    }

    @Override
    public void remove(Long cityId) {
        cityRepository.deleteById(cityId);
    }
}
