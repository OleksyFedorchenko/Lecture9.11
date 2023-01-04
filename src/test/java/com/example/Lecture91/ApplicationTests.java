package com.example.Lecture91;

import com.example.Lecture91.dto.CityDTO;
import com.example.Lecture91.entity.CountryEntity;
import com.example.Lecture91.repository.CityRepository;
import com.example.Lecture91.repository.CountryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ApplicationTests {
	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private ObjectMapper objectMapper;
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
	@Test
	void givenCity_whenAdd_thenStatus201()throws Exception {
		CountryEntity countryEntity=new CountryEntity();
		countryEntity.setId(1L);
		countryEntity.setName("Urugwai");
		countryRepository.save(countryEntity);
		CityDTO cityDTO = new CityDTO();
		cityDTO.setId(1L);
		cityDTO.setName("Bavaria");
		cityDTO.setPopulation(200000);
		cityDTO.setCapitalOfCountry(false);
		cityDTO.setCountry(countryEntity);

		mockMvc.perform(
						post("/city")
								.content(objectMapper.writeValueAsString(cityDTO))
								.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isCreated());
	}

}
