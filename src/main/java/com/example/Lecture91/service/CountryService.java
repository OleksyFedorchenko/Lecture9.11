package com.example.Lecture91.service;

import com.example.Lecture91.entity.CountryEntity;

import java.util.List;

public interface CountryService {
    void addCountry(CountryEntity countryEntity);

    List<CountryEntity> findAllCountries();
}
