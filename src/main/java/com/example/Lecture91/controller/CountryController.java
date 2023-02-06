package com.example.Lecture91.controller;

import com.example.Lecture91.dto.CountryDTO;
import com.example.Lecture91.dto.ErrorDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<?> addCountry(@RequestBody CountryDTO countryDTO) {
        countryService.addCountry(countryDTO);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<CountryDTO>> getCountries() {
        return ResponseEntity.ok(countryService.findAllCountriesOrderByIdAsc());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/delete/{countryId}")
    public ResponseEntity<?> deleteCityById(@PathVariable("countryId") Long id) {
        countryService.deleteCountryById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{countryId}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable("countryId") Long id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

@CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("edit")
    public ResponseEntity<?> editCountry(@Valid @RequestBody CountryDTO country, BindingResult br) {
        if (br.hasErrors()) {
            System.out.println("Validation error");
            String errMsg = br.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .findFirst().get();
            ErrorDTO errorDTO = new ErrorDTO(errMsg);
            return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        }
        countryService.editCountryById(country);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
