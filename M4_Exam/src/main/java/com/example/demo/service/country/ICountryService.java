package com.example.demo.service.country;

import com.example.demo.model.Country;

public interface ICountryService {
    Iterable<Country> findAll();
}
