package com.pizzaguideapp.models.recipes.dto;


import com.pizzaguideapp.models.media.dto.MediaDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@RequiredArgsConstructor
public class RecipeRequestDto {
    private final Long id;
    @NotBlank(message = "Can't be blank!")
    private final String name;
    @NotBlank(message = "Can't be blank!")
    private final String description;
    private final List<MediaDto> mediaList;
}
