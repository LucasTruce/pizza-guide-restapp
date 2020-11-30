package com.pizzaguideapp.models.recipes.dto;


import com.pizzaguideapp.models.components.dto.ComponentDto;
import com.pizzaguideapp.models.media.dto.MediaDto;
import com.pizzaguideapp.models.reviews.dto.ReviewRequestDto;
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
    private final List<ReviewRequestDto> reviewList;
    private final List<ComponentDto> componentList;
}
