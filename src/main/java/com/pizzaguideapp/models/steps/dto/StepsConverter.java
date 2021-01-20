package com.pizzaguideapp.models.steps.dto;

import com.pizzaguideapp.models.recipes.dto.RecipeConverter;
import com.pizzaguideapp.models.steps.Steps;

import java.util.List;
import java.util.stream.Collectors;

public class StepsConverter {

    public StepsDto map(Steps steps) {
        RecipeConverter recipeConverter = new RecipeConverter();

        return new StepsDto(
                steps.getId(),
                steps.getName(),
                steps.getDescription(),
                steps.getTime()
        );
    }

    public Steps map(StepsDto stepsDto) {
        RecipeConverter recipeConverter = new RecipeConverter();
        Steps steps = new Steps();
        steps.setName(stepsDto.getName());
        steps.setDescription(stepsDto.getDescription());
        steps.setTime(stepsDto.getTime());
        return steps;
    }

    public List<StepsDto> map(List<Steps> entityObjects){
        return entityObjects.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public List<Steps> map2(List<StepsDto> entityObjects){
        return entityObjects.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
