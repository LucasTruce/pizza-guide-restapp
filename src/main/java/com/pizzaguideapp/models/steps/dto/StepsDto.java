package com.pizzaguideapp.models.steps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepsDto {
    private Long id;

    @NotBlank(message = "Can't be blank!")
    private String name;

    @NotBlank(message = "Can't be blank!")
    private String description;

    private Time time;

    private float temperature;
}
