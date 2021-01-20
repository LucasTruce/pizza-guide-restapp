package com.pizzaguideapp.models.ingredients.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto {

    @NotBlank(message = "Can't be blank!")
    @NotNull(message = "Can't be null!")
    private String name;

}
