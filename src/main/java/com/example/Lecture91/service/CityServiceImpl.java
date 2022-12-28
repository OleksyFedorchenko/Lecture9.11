package com.example.Lecture91.service;

import com.example.Lecture91.dto.CityDTO;
import com.example.Lecture91.entity.CityEntity;
import com.example.Lecture91.exception.ResourceNotFoundException;
import com.example.Lecture91.repository.CityRepository;
import com.example.Lecture91.utils.ObjectMapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements CityService{
    CityRepository cityRepository;
    private final ObjectMapperUtils modelMapper;

    public CityServiceImpl(CityRepository cityRepository, ObjectMapperUtils modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public void addCity(CityDTO cityDTO) {
        CityEntity city=modelMapper.map(cityDTO,CityEntity.class);
        cityRepository.save(city);
    }

    @Override
    public List<CityDTO> findAllCities() {
        List<CityEntity> cityEntities=cityRepository.findAll();
        return modelMapper.mapAll(cityEntities,CityDTO.class);
}

    @Override
    public void deleteCityById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public CityDTO getCityById(Long id) {
        CityEntity cityEntity=cityRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Could not find COUNTRY with id [" + id + "]"));
        return modelMapper.map(cityEntity,CityDTO.class);
    }
}
