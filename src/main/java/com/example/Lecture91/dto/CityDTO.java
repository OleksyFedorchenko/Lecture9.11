package com.example.Lecture91.dto;

import com.example.Lecture91.entity.CountryEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CityDTO {
    private Long id;
    @NotNull(message = "Field 'NAME' can't be null")
    @NotBlank(message = "Field 'NAME' can't be blank")
    private String name;
    @NotNull(message = "Field 'POPULATION' can't be null")
    @Size(message = "Population can't be less than 0")
    private int population;
    @NotNull(message = "Field 'CapitalOfCountry' can't be null")
    private boolean capitalOfCountry;
    @NotNull(message = "Field 'Country' can't be null")
    private CountryEntity country;
}
