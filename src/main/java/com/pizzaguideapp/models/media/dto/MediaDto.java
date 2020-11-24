package com.pizzaguideapp.models.media.dto;

import com.pizzaguideapp.models.recipes.dto.RecipeRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MediaDto {
    private String name;
    private String link;
    //private RecipeRequestDto recipe;
}
