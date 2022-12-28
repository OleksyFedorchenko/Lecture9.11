package com.example.Lecture91.dto;

import com.example.Lecture91.entity.CountryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CityDto {
    private Long id;
    @NotNull(message = "Field 'NAME' can't be null")
    @Size(min = 1, max = 60, message = "Name length should be between 1 and 60")
    private String name;
    @NotNull(message = "Field 'NAME' can't be null")
    @Size(message = "Population can't be less than 0")
    private int population;
    @NotNull(message = "Field 'CapitalOfCountry' can't be null")
    private boolean capitalOfCountry;
    @NotNull(message = "Field 'Country' can't be null")
    private CountryEntity country;
}
