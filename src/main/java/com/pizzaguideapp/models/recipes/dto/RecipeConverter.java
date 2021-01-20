package com.pizzaguideapp.models.recipes.dto;

import com.pizzaguideapp.models.components.dto.ComponentConverter;
import com.pizzaguideapp.models.media.dto.MediaConverter;
import com.pizzaguideapp.models.recipes.Recipe;
import com.pizzaguideapp.models.reviews.dto.ReviewConverter;
import com.pizzaguideapp.models.steps.dto.StepsConverter;
import com.pizzaguideapp.models.user.dto.UserConverter;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeConverter {

    public RecipeRequestDto map(Recipe recipe) {
        MediaConverter mediaConverter = new MediaConverter();

        return new RecipeRequestDto(
                recipe.getId(),
                recipe.getName(),
                recipe.getDescription(),
                mediaConverter.map(recipe.getMediaList())
        );
    }

    public Recipe map(RecipeRequestDto recipeRequestDto) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeRequestDto.getId());
        recipe.setName(recipeRequestDto.getName());
        recipe.setDescription(recipeRequestDto.getDescription());
        return recipe;
    }

    public RecipeIdentificationDto mapIdentification(Recipe recipe){
        UserConverter userConverter = new UserConverter();
        MediaConverter mediaConverter = new MediaConverter();
        StepsConverter stepsConverter = new StepsConverter();
        ReviewConverter reviewConverter = new ReviewConverter();
        ComponentConverter componentConverter = new ComponentConverter();
        return new RecipeIdentificationDto(
                recipe.getId(),
                recipe.getName(),
                recipe.getDescription(),
                userConverter.map(recipe.getUser()),
                mediaConverter.map(recipe.getMediaList()),
                stepsConverter.map(recipe.getStepsList()),
                componentConverter.map(recipe.getComponentList()),
                reviewConverter.map(recipe.getReviewList())
        );
    }


    public List<RecipeRequestDto> map(List<Recipe> entityObjects){
        return entityObjects.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
