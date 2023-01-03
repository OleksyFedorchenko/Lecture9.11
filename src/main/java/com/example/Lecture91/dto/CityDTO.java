package com.example.Lecture91.dto;

import com.example.Lecture91.entity.CountryEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CityDTO {
    private Long id;
    @NotNull(message = "Field 'NAME' can't be null")
    @Size(min = 1, max = 60, message = "Name length should be between 1 and 60")
    private String name;
    @NotNull(message = "Field 'POPULATION' can't be null")
    @Size(message = "Population can't be less than 0")
    private int population;
    @NotNull(message = "Field 'CapitalOfCountry' can't be null")
    private boolean capitalOfCountry;
    @NotNull(message = "Field 'Country' can't be null")
    private CountryEntity country;
}
