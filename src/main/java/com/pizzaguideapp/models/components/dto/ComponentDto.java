package com.pizzaguideapp.models.components.dto;

import com.pizzaguideapp.models.ingredients.dto.IngredientDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class ComponentDto {
    private final int amount;
    private final IngredientDto ingredients;
}
