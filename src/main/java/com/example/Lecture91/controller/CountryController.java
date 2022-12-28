package com.example.Lecture91.controller;

import com.example.Lecture91.entity.CountryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Lecture91.service.CountryService;

import java.util.List;
@RestController
@RequestMapping("/country")
public class CountryController {
    CountryService countryService;
    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @PostMapping
    public ResponseEntity<?> addCountry(@RequestBody CountryEntity countryEntity) {
        countryService.addCountry(countryEntity);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CountryEntity>> getCountries() {
        return ResponseEntity.ok(countryService.findAllCountries());
    }
    @DeleteMapping("{countryId}")
    public ResponseEntity<?> deleteCityById(@PathVariable("countryId") Long id) {
        countryService.deleteCountryById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
