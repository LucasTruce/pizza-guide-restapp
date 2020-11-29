package com.pizzaguideapp.models.recipes;

import com.pizzaguideapp.exception.EntityNotFoundException;
import com.pizzaguideapp.models.recipes.dto.RecipeRequestDto;
import com.pizzaguideapp.models.user.User;
import com.pizzaguideapp.models.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<RecipeRequestDto>> getRecipes(@RequestParam(defaultValue = "0") int pageNumber,
                                                             @RequestParam(defaultValue = "5") int pageSize,
                                                             @RequestParam(defaultValue = "id") String orderBy,
                                                             @RequestParam(defaultValue = "ASC") String direction){
        return new ResponseEntity<>(recipeService.getRecipes(pageNumber, pageSize, orderBy, direction), HttpStatus.OK);
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
