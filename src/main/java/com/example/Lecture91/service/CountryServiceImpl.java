package com.example.Lecture91.service;

import com.example.Lecture91.entity.CountryEntity;
import org.springframework.stereotype.Service;
import com.example.Lecture91.repository.CountryRepository;

import java.util.List;
@Service
public class CountryServiceImpl implements CountryService{

    CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void addCountry(CountryEntity countryEntity) {
    countryRepository.save(countryEntity);
    }

    @Override
    public List<CountryEntity> findAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public void deleteCountryById(Long id) {
        countryRepository.deleteById(id);
    }
}
