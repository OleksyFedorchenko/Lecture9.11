package com.example.Lecture91.service;

import com.example.Lecture91.entity.CityEntity;
import com.example.Lecture91.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements CityService{
    CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void addCity(CityEntity cityEntity) {
        cityRepository.save(cityEntity);
    }

    @Override
    public List<CityEntity> findAllCities() {
        return cityRepository.findAll();
}

    @Override
    public void deleteCityById(Long id) {
        cityRepository.deleteById(id);
    }
}
