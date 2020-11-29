package com.pizzaguideapp.models.recipes.dto;

import com.pizzaguideapp.models.components.dto.ComponentConverter;
import com.pizzaguideapp.models.media.dto.MediaConverter;
import com.pizzaguideapp.models.recipes.Recipe;
import com.pizzaguideapp.models.reviews.dto.ReviewConverter;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeConverter {

    public RecipeRequestDto map(Recipe recipe) {
        MediaConverter mediaConverter = new MediaConverter();
        ReviewConverter reviewConverter = new ReviewConverter();
        ComponentConverter componentConverter = new ComponentConverter();
        return new RecipeRequestDto(
                recipe.getId(),
                recipe.getName(),
                recipe.getDescription(),
                mediaConverter.map(recipe.getMediaList()),
                reviewConverter.map(recipe.getReviewList()),
                componentConverter.map(recipe.getComponentList())

        );
    }

    public Recipe map(RecipeRequestDto recipeRequestDto) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeRequestDto.getId());
        recipe.setName(recipeRequestDto.getName());
        recipe.setDescription(recipeRequestDto.getDescription());
        return recipe;
    }


    public List<RecipeRequestDto> map(List<Recipe> entityObjects){
        return entityObjects.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
