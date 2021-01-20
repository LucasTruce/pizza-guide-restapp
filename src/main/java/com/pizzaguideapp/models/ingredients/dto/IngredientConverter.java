package com.pizzaguideapp.models.ingredients.dto;

import com.pizzaguideapp.models.ingredients.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

public class IngredientConverter {
    public IngredientDto map(Ingredient ingredient) {

        return new IngredientDto(
                ingredient.getName()
        );
    }

    public Ingredient map(IngredientDto ingredientDto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDto.getName());
        return ingredient;
    }


    public List<IngredientDto> map(List<Ingredient> entityObjects){
        return entityObjects.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
