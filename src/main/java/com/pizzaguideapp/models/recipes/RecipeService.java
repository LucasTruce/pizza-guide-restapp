package com.pizzaguideapp.models.recipes;

import com.pizzaguideapp.exception.EntityNotFoundException;
import com.pizzaguideapp.models.recipes.dto.RecipeConverter;
import com.pizzaguideapp.models.recipes.dto.RecipeIdentificationDto;
import com.pizzaguideapp.models.recipes.dto.RecipeRequestDto;
import com.pizzaguideapp.models.recipes.dto.RecipeUpdateDto;
import com.pizzaguideapp.models.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public Page<RecipeIdentificationDto> getRecipes(int pageNumber, int pageSize, String orderBy, String direction) {
        Sort.Direction tempDirection;
        if(direction.equals("ASC"))
            tempDirection = Sort.Direction.ASC;
        else
            tempDirection = Sort.Direction.DESC;
        Page<Recipe> recipes = recipeRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(tempDirection, orderBy)));

        return recipes.map(recipeConverter::mapIdentification);
    }

    public Optional<Recipe> findById(Long id){
        return recipeRepository.findById(id);
    }

    public RecipeIdentificationDto getRecipeById(Long id){
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Recipe not found!"));

        return recipeConverter.mapIdentification(recipe);
    }

    public RecipeRequestDto updateRecipe(RecipeUpdateDto recipeUpdateDto, Recipe recipe) {
        recipe.setDescription(recipeUpdateDto.getDescription());
        recipe.setName(recipeUpdateDto.getName());
        return recipeConverter.map(recipeRepository.save(recipe));
    }
}
