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

import java.util.Arrays;
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
        long id = createTestCity("Spain", "Madrid").getId();
        mockMvc.perform(
                        get("/city/{cityId}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Madrid"));
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
        CityEntity cityEntity = createTestCity("Spain", "Madrid");
        mockMvc.perform(
                        delete("/city/{id}", cityEntity.getId()))
                .andExpect(status().isOk());
    }


    //В цьому тесті зробив саме таким чином, бо в базі після першого запуску
    // вже є країни і відповідно отримуємо список включно з цими країнами.
    @Test
    public void givenCountries_whenGetCountries_thenStatus200() throws Exception {
        CountryEntity countryEntity1 = new CountryEntity();
        countryEntity1.setId(1L);
        countryEntity1.setName("Ukraine");
        CountryEntity countryEntity2 = new CountryEntity();
        countryEntity2.setId(2L);
        countryEntity2.setName("Poland");
        CountryEntity countryEntity3 = new CountryEntity();
        countryEntity3.setId(3L);
        countryEntity3.setName("Germany");
        mockMvc.perform(
                        get("/country"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(countryEntity1, countryEntity2, countryEntity3))));
    }


}
