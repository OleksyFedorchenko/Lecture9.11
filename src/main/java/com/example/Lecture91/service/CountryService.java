package com.example.Lecture91.service;

import com.example.Lecture91.dto.CountryDTO;
import com.example.Lecture91.entity.CountryEntity;

import java.util.List;

public interface CountryService {
    void addCountry(CountryDTO countryDTO);

    List<CountryDTO> findAllCountries();

    void deleteCountryById(Long id);
}
