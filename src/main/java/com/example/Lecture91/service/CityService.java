package com.example.Lecture91.service;

import com.example.Lecture91.dto.CityDTO;

import java.util.List;

public interface CityService {
    void addCity(CityDTO cityDTO);

    List<CityDTO> findAllCities();

    void deleteCityById(Long id);

    CityDTO getCityById(Long id);
}
