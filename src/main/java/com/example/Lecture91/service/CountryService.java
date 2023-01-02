package com.example.Lecture91.service;

import com.example.Lecture91.dto.CountryDTO;

import java.util.List;

public interface CountryService {
    void addCountry(CountryDTO countryDTO);

    List<CountryDTO> findAllCountries();

    void deleteCountryById(Long id);

    CountryDTO getCountryById(Long id);

    void editCountryById(CountryDTO countryDTO);
}
