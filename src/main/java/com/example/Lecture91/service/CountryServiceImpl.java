package com.example.Lecture91.service;

import com.example.Lecture91.dto.CountryDTO;
import com.example.Lecture91.entity.CityEntity;
import com.example.Lecture91.entity.CountryEntity;
import com.example.Lecture91.utils.ObjectMapperUtils;
import org.springframework.stereotype.Service;
import com.example.Lecture91.repository.CountryRepository;

import java.util.List;
@Service
public class CountryServiceImpl implements CountryService{

    CountryRepository countryRepository;
    private final ObjectMapperUtils modelMapper;

    public CountryServiceImpl(CountryRepository countryRepository, ObjectMapperUtils modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCountry(CountryDTO countryDTO) {
        CountryEntity country=modelMapper.map(countryDTO,CountryEntity.class);
        countryRepository.save(country);
    }

    @Override
    public List<CountryDTO> findAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public void deleteCountryById(Long id) {
        countryRepository.deleteById(id);
    }
}
