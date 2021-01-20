package com.pizzaguideapp.models.components.dto;

import com.pizzaguideapp.models.ingredients.dto.IngredientDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ComponentDto {
    private int amount;
    private IngredientDto ingredients;

}
