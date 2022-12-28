package com.example.Lecture91.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CountryDTO {
    private Long id;
    @NotNull(message = "Field 'NAME' can't be null")
    @Size(min = 1, max = 15, message = "Name length should be between 1 and 15")
    private String name;
}
