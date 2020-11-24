package com.pizzaguideapp.models.recipes;

import com.pizzaguideapp.models.recipes.dto.RecipeConverter;
import com.pizzaguideapp.models.recipes.dto.RecipeRequestDto;
import com.pizzaguideapp.models.recipes.dto.RecipeUpdateDto;
import com.pizzaguideapp.models.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeConverter recipeConverter;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeConverter = new RecipeConverter();
    }

    public RecipeRequestDto saveRecipe(RecipeRequestDto recipeRequestDto, User user){
        Recipe recipe = recipeConverter.map(recipeRequestDto);
        recipe.setUser(user);
        return recipeConverter.map(recipeRepository.save(recipe));
    }

    public List<RecipeRequestDto> getRecipes() {
        return recipeConverter.map(recipeRepository.findAll());
    }

    public Optional<Recipe> findById(Long id){
        return recipeRepository.findById(id);
    }

    public RecipeRequestDto updateRecipe(RecipeUpdateDto recipeUpdateDto, Recipe recipe) {
        recipe.setDescription(recipeUpdateDto.getDescription());
        recipe.setName(recipeUpdateDto.getName());
        return recipeConverter.map(recipeRepository.save(recipe));
    }
}
