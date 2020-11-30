package com.pizzaguideapp.models.components;

import com.pizzaguideapp.exception.EntityNotFoundException;
import com.pizzaguideapp.models.components.dto.ComponentConverter;
import com.pizzaguideapp.models.components.dto.ComponentDto;
import com.pizzaguideapp.models.ingredients.Ingredient;
import com.pizzaguideapp.models.ingredients.IngredientRepository;
import com.pizzaguideapp.models.recipes.Recipe;
import com.pizzaguideapp.models.recipes.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {

    private final ComponentConverter componentConverter;
    private final ComponentRepository componentRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public ComponentService(ComponentRepository componentRepository, RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.componentRepository = componentRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.componentConverter = new ComponentConverter();
    }

    public List<ComponentDto> getComponentsForRecipe(Long recipeId){
        return componentConverter.map(componentRepository.findAllByRecipeId(recipeId));
    }

    public List<ComponentDto> saveComponents(List<ComponentDto> componentDtos, Long recipeId){
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new EntityNotFoundException("Recipe not found by id!"));
        Ingredient ingredient;
        List<Component> components = componentConverter.mapDtos(componentDtos);
        for(Component component : components){
            ingredient = ingredientRepository.findByName(component.getIngredient().getName()).orElseThrow(() -> new EntityNotFoundException("Ingredient not found!"));
            component.setIngredient(ingredient);
            component.setRecipe(recipe);
        }
        return componentConverter.map(componentRepository.saveAll(components));
    }


}
