package com.pizzaguideapp.models.ingredients.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class IngredientDto {

    @NotBlank(message = "Can't be blank!")
    @NotNull(message = "Can't be null!")
    private String name;

}
