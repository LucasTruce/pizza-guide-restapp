package com.pizzaguideapp.models.recipes.dto;


import com.pizzaguideapp.models.media.dto.MediaDto;
import com.pizzaguideapp.models.steps.Steps;
import com.pizzaguideapp.models.steps.dto.StepsDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class RecipeRequestDto {

    private final Long id;
    private final String name;
    private final String description;
    private final List<MediaDto> mediaList;
}
