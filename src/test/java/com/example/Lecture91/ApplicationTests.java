package com.example.Lecture91;

import com.example.Lecture91.entity.CityEntity;
import com.example.Lecture91.entity.CountryEntity;
import com.example.Lecture91.exception.ResourceNotFoundException;
import com.example.Lecture91.repository.CityRepository;
import com.example.Lecture91.repository.CountryRepository;
import com.example.Lecture91.utils.ObjectMapperUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ApplicationTests {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    ObjectMapperUtils modelMapper;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void resetDb() {
        cityRepository.deleteAll();

    }

    private CityEntity createTestCity(String countryName, String cityName) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setName(countryName);
        countryRepository.save(countryEntity);
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName(cityName);
        cityEntity.setPopulation(200000);
        cityEntity.setCapitalOfCountry(true);
        cityEntity.setCountry(countryEntity);
        return cityRepository.save(cityEntity);
    }

    @Test
    void givenCity_whenAdd_thenStatus201() throws Exception {
        CityEntity cityEntity = createTestCity("Spain", "Madrid");
        mockMvc.perform(
                        post("/city")
                                .content(objectMapper.writeValueAsString(cityEntity))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }

    @Test
    public void givenId_whenGetExistingCity_thenStatus200andCityReturned() throws Exception {
        long id = createTestCity("Hungary", "Budapest").getId();
        mockMvc.perform(
                        get("/city/{cityId}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Budapest"));
    }

    @Test
    public void givenId_whenGetNotExistingCity_thenStatus404anExceptionThrown() throws Exception {
        mockMvc.perform(
                        get("/city/351"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("Could not find CITY with id [351]", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenCity_whenDeleteCity_thenStatus200() throws Exception {
        CityEntity cityEntity = createTestCity("Italy", "Rome");
        mockMvc.perform(
                        delete("/city/{id}", cityEntity.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void givenCities_whenGetCities_thenStatus200() throws Exception {
        List<CityEntity> listCities = cityRepository.findAll();
        mockMvc.perform(
                        get("/city"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listCities)));
    }

    @Test
    public void giveCity_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {
        CityEntity oldCity = createTestCity("USA", "Washington");
        CityEntity updateCity = new CityEntity();
        updateCity.setId(oldCity.getId());
        updateCity.setName("Canada");
        updateCity.setPopulation(oldCity.getPopulation());
        updateCity.setCapitalOfCountry(false);
        updateCity.setCountry(oldCity.getCountry());
        mockMvc.perform(
                        put("/city/edit")
                                .content(objectMapper.writeValueAsString(updateCity))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(oldCity.getId()))
                .andExpect(jsonPath("$.name").value("Canada"))
                .andExpect(jsonPath("$.population").value(updateCity.getPopulation()))
                .andExpect(jsonPath("$.capitalOfCountry").value(false));
    }


}
