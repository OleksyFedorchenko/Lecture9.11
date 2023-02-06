package com.example.Lecture91.service;

import com.example.Lecture91.dto.CountryDTO;
import com.example.Lecture91.entity.CountryEntity;
import com.example.Lecture91.exception.AlreadyExistsException;
import com.example.Lecture91.exception.ResourceNotFoundException;
import com.example.Lecture91.utils.ObjectMapperUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.Lecture91.repository.CountryRepository;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    CountryRepository countryRepository;
    private final ObjectMapperUtils modelMapper;

    public CountryServiceImpl(CountryRepository countryRepository, ObjectMapperUtils modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCountry(CountryDTO countryDTO) {
        CountryEntity country = modelMapper.map(countryDTO, CountryEntity.class);
        countryRepository.save(country);
    }

    @Override
    public List<CountryDTO> findAllCountriesOrderByIdAsc() {
        List<CountryEntity> countryEntities = countryRepository.findAll(Sort.by("id").ascending());
        return modelMapper.mapAll(countryEntities, CountryDTO.class);
    }

    @Override
    public void deleteCountryById(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public CountryDTO getCountryById(Long id) {
        CountryEntity countryEntity = countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find COUNTRY with id [" + id + "]"));
        return modelMapper.map(countryEntity, CountryDTO.class);
    }

    @Override
    public void editCountryById(CountryDTO countryDTO) {
        CountryEntity country = modelMapper.map(countryDTO, CountryEntity.class);
        countryRepository.save(country);
    }
}
