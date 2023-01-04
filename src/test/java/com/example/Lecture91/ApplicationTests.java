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

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private CityEntity createTestCity() {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setName("Urugwai");
        countryRepository.save(countryEntity);
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName("Bavaria");
        cityEntity.setPopulation(200000);
        cityEntity.setCapitalOfCountry(true);
        cityEntity.setCountry(countryEntity);
        return cityRepository.save(cityEntity);
    }

    @Test
    void givenCity_whenAdd_thenStatus201() throws Exception {
        CityEntity cityEntity = createTestCity();
        mockMvc.perform(
                        post("/city")
                                .content(objectMapper.writeValueAsString(cityEntity))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }

    @Test
    public void givenId_whenGetExistingCity_thenStatus200andCityReturned() throws Exception {
        long id = createTestCity().getId();
        mockMvc.perform(
                        get("/city/{cityId}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Bavaria"));
    }

    @Test
    public void givenId_whenGetNotExistingCity_thenStatus404anExceptionThrown() throws Exception {
        mockMvc.perform(
                        get("/city/351"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("Could not find CITY with id [351]", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }


}
