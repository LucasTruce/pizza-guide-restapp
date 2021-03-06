package com.pizzaguideapp.models.steps;

import com.pizzaguideapp.exception.EntityNotFoundException;
import com.pizzaguideapp.models.recipes.Recipe;
import com.pizzaguideapp.models.recipes.RecipeService;
import com.pizzaguideapp.models.steps.dto.StepsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StepsController {

    private final StepsService stepsService;
    private final RecipeService recipeService;

    @GetMapping("/recipes/{id}/steps")
    public ResponseEntity<?> getStepsForRecipe(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(stepsService.getStepsForRecipe(id), HttpStatus.OK);
    }


    //change to save as list
    @PostMapping("recipes/{id}/steps")
    public ResponseEntity<List<StepsDto>> saveStep(@PathVariable Long id, @RequestBody @Valid List<StepsDto> stepsDtos){
        Recipe recipe = recipeService.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found recipe!"));
        return new ResponseEntity<>(stepsService.saveStep(stepsDtos, recipe), HttpStatus.OK);
    }
}
