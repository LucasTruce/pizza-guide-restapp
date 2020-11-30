package com.pizzaguideapp.models.steps;

import com.pizzaguideapp.models.recipes.Recipe;
import com.pizzaguideapp.models.steps.dto.StepsConverter;
import com.pizzaguideapp.models.steps.dto.StepsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StepsService {

    private final StepsRepository stepsRepository;
    private final StepsConverter stepsConverter;

    @Autowired
    public StepsService(StepsRepository stepsRepository){
        this.stepsRepository = stepsRepository;
        this.stepsConverter = new StepsConverter();
    }

    public List<StepsDto> getStepsForRecipe(Long recipeId) {
        return stepsConverter.map(stepsRepository.findAllByRecipeId(recipeId));
    }

    public List<StepsDto> saveStep(List<StepsDto> stepsDtos, Recipe recipe){

        List<Steps> steps = stepsConverter.map2(stepsDtos);
        steps.forEach(s -> s.setRecipe(recipe));
        return stepsConverter.map(stepsRepository.saveAll(steps));
    }



}
