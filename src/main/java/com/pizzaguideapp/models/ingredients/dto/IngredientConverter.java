package com.pizzaguideapp.models.ingredients.dto;

import com.pizzaguideapp.models.ingredients.Ingredient;
import com.pizzaguideapp.models.recipes.dto.RecipeConverter;

import java.util.List;
import java.util.stream.Collectors;

public class IngredientConverter {
    public IngredientDto map(Ingredient ingredient) {
        RecipeConverter recipeConverter = new RecipeConverter();

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
