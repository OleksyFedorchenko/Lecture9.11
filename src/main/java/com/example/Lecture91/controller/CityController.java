package com.example.Lecture91.controller;

import com.example.Lecture91.entity.CityEntity;
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
    public ResponseEntity<?> addCity(@RequestBody CityEntity cityEntity) {
        cityService.addCity(cityEntity);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CityEntity>> getCities() {
        return ResponseEntity.ok(cityService.findAllCities());
    }
}
