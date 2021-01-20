package com.pizzaguideapp.models.steps.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Time;

@Getter
@RequiredArgsConstructor
public class StepsDto {
    private final Long id;

    @NotBlank(message = "Can't be blank!")
    private final String name;

    @NotBlank(message = "Can't be blank!")
    private final String description;

    private final Time time;

}
