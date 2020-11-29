package com.pizzaguideapp.models.ingredients;

import com.pizzaguideapp.models.ingredients.dto.IngredientConverter;
import com.pizzaguideapp.models.ingredients.dto.IngredientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientConverter ingredientConverter;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientConverter = new IngredientConverter();
    }

    public List<IngredientDto> getIngredients(){
        return ingredientConverter.map(ingredientRepository.findAll());
    }

    public IngredientDto saveIngredient(IngredientDto ingredientDto){
        return ingredientConverter.map(ingredientRepository.save(ingredientConverter.map(ingredientDto)));
    }
}
