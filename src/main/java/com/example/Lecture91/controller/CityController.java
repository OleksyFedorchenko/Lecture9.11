package com.example.Lecture91.controller;

import com.example.Lecture91.dto.CityDTO;
import com.example.Lecture91.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
CityService cityService;
    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<?> addCity(@RequestBody CityDTO cityDTO) {
        cityService.addCity(cityDTO);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CityDTO>> getCities() {
        return ResponseEntity.ok(cityService.findAllCities());
    }
    @DeleteMapping("{cityId}")
    public ResponseEntity<?> deleteCityById(@PathVariable("cityId") Long id) {
        cityService.deleteCityById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @GetMapping("{cityId}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable("cityId") Long id) {
        return ResponseEntity.ok(cityService.getCityById(id));
    }
}
