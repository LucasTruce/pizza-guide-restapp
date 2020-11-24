package com.pizzaguideapp.models.recipes;

import com.pizzaguideapp.exception.EntityNotFoundException;
import com.pizzaguideapp.models.recipes.dto.RecipeRequestDto;
import com.pizzaguideapp.models.user.User;
import com.pizzaguideapp.models.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<RecipeRequestDto>> getRecipes(){
        return new ResponseEntity<>(recipeService.getRecipes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecipeRequestDto> saveRecipe(@Valid @RequestBody RecipeRequestDto recipeRequestDto, Principal principal){
        User user = userService.findUserByLogin(principal.getName()).orElseThrow(() -> new EntityNotFoundException("User not logged!"));
        return new ResponseEntity<>(recipeService.saveRecipe(recipeRequestDto, user), HttpStatus.OK);
    }

    //@PutMapping("/{id}")
    //public ResponseEntity<?> updateRecipe(@PathVariable Long id, @Valid @RequestBody RecipeUpdateDto recipeUpdateDto){
    //    Recipe recipe = recipeService.findById(id).orElseThrow(() -> new EntityNotFoundException("Recipe not found!"));
    //    return new ResponseEntity<>(recipeService.updateRecipe(recipeUpdateDto, recipe), HttpStatus.OK);
    //}
}
