package com.example.Lecture91.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TaskDTO {
    private Long id;
    @NotNull(message = "Field 'NAME' can't be null")
    @NotBlank(message = "Field 'NAME' can't be blank")
    private String name;
}
