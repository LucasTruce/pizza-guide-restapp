package com.pizzaguideapp.models.steps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepsDto {
    private Long id;
    private String name;
    private String description;
    private Time time;
    private float temperature;
}
