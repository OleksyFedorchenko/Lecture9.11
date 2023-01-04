package com.example.Lecture91.service;

import com.example.Lecture91.dto.CityDTO;
import com.example.Lecture91.entity.CityEntity;
import com.example.Lecture91.exception.AlreadyExistsException;
import com.example.Lecture91.exception.ResourceNotFoundException;
import com.example.Lecture91.repository.CityRepository;
import com.example.Lecture91.utils.ObjectMapperUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    CityRepository cityRepository;
    private final ObjectMapperUtils modelMapper;

    public CityServiceImpl(CityRepository cityRepository, ObjectMapperUtils modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCity(CityDTO cityDTO) {
        CityEntity city = modelMapper.map(cityDTO, CityEntity.class);
        cityRepository.save(city);
    }

    @Override
    public List<CityDTO> findAllCitiesOrderByIdAsc() {
        List<CityEntity> cityEntities = cityRepository.findAll(Sort.by("id").ascending());
        return modelMapper.mapAll(cityEntities, CityDTO.class);
    }

    @Override
    public void deleteCityById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public CityDTO getCityById(Long id) {
        CityEntity cityEntity = cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find CITY with id [" + id + "]"));
        return modelMapper.map(cityEntity, CityDTO.class);
    }

    @Override
    public void editCityById(CityDTO cityDTO) {
//        Optional<CityEntity> c =cityRepository.findById(id);
//        if (!c.isPresent())
//            throw new ResourceNotFoundException("Could not edit this id not found");
//        CityEntity oldCity = c.get();
//        oldCity.setName(cityDTO.getName());
//        CityEntity tempCity = cityRepository.findByName(oldCity.getName());
//        if (tempCity != null) {
//            if (tempCity.getName().equals(oldCity.getName())) {
//                throw new AlreadyExistsException("City with name [" + oldCity.getName() + "]already exists");
//            }
//        }
//        oldCity = modelMapper.map(cityDTO, CityEntity.class);
//        cityRepository.save(oldCity);


        CityEntity city = cityRepository.findById(cityDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Could not edit this id not found"));
        city.setName(cityDTO.getName());
        CityEntity city1 = cityRepository.findByName(city.getName());
        if (city1 != null) {
            if (city1.getName().equals(city.getName())) {
                throw new AlreadyExistsException("City with name [" + city.getName() + "]already exists");
            }
        }
        city = modelMapper.map(cityDTO, CityEntity.class);
        cityRepository.save(city);
    }

    @Override
    public List<CityDTO> findByPopulationAndCountryName(int population, String countryName, Pageable pageable) {
        List<CityEntity> cityEntities = cityRepository.findByPopulationAndCountryName(population, countryName, pageable);
        return modelMapper.mapAll(cityEntities, CityDTO.class);
    }
}
