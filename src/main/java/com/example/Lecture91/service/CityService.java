package com.example.Lecture91.service;

import com.example.Lecture91.entity.CityEntity;

import java.util.List;

public interface CityService {
    void addCity(CityEntity cityEntity);

    List<CityEntity> findAllCities();

    void deleteCityById(Long id);
}
