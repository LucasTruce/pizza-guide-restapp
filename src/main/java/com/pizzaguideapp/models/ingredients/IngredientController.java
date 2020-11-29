package com.pizzaguideapp.models.ingredients;

import com.pizzaguideapp.models.ingredients.dto.IngredientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getIngredients(){
        return new ResponseEntity<>(ingredientService.getIngredients(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IngredientDto> saveIngredient(@RequestBody @Valid IngredientDto ingredientDto){
        return new ResponseEntity<>(ingredientService.saveIngredient(ingredientDto), HttpStatus.OK);
    }

}
