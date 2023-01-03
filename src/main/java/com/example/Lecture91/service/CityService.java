package com.example.Lecture91.service;

import com.example.Lecture91.dto.CityDTO;
import com.example.Lecture91.entity.CityEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {
    void addCity(CityDTO cityDTO);

    List<CityDTO> findAllCities();

    void deleteCityById(Long id);

    CityDTO getCityById(Long id);

    void editCityById(CityDTO cityDTO);

    List<CityDTO> findByPopulationAndCountryName(int population, String countryName, Pageable pageable);
}
