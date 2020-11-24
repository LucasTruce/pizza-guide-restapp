package com.pizzaguideapp.models.media;

import com.pizzaguideapp.exception.EntityNotFoundException;
import com.pizzaguideapp.models.media.dto.MediaDto;
import com.pizzaguideapp.models.recipes.Recipe;
import com.pizzaguideapp.models.recipes.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;
    private final RecipeService recipeService;


    @PostMapping("recipes/{id}/media")
    public ResponseEntity<MediaDto> saveRecipe(@PathVariable Long id,
                                               @RequestPart("image") MultipartFile file)
            throws IOException {
        Recipe recipe = recipeService.findById(id).orElseThrow(() -> new EntityNotFoundException("Recipe not found!"));
        return new ResponseEntity<>(mediaService.saveMediaUploadImage(file, recipe), HttpStatus.OK);
    }
}
