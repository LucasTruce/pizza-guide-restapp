package com.pizzaguideapp.models.recipes.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RecipeUpdateDto {
    private final Long id;
    private final String name;
    private final String description;
}
