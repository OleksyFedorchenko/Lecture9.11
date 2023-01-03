package com.example.Lecture91.controller;

import com.example.Lecture91.dto.CityDTO;
import com.example.Lecture91.dto.ErrorDTO;
import com.example.Lecture91.service.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @PutMapping("edit")
    public ResponseEntity<?> editCity(@Valid @RequestBody CityDTO city, BindingResult br) {
        if (br.hasErrors()) {
            System.out.println("Validation error");
            String errMsg = br.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .findFirst().get().toString();
            ErrorDTO errorDTO = new ErrorDTO(errMsg);
            return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        }
        cityService.editCityById(city);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("{population}/{countryname}/{pagenumber}")
    public ResponseEntity<List<CityDTO>> getCityByPopulationAndCountryName(@PathVariable("population") int population, @PathVariable("countryname") String countryName, @PathVariable("pagenumber") int pageNumber) {
        int pageSize = 3;
        Pageable pageWithThreeElements = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return ResponseEntity.ok(cityService.findByPopulationAndCountryName(population, countryName, pageWithThreeElements));
    }
}
