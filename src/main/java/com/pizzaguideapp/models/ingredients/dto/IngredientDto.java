package com.pizzaguideapp.models.ingredients.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
public class IngredientDto {

    @NotBlank
    private final String name;
}
