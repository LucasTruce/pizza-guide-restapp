package com.pizzaguideapp.models.recipes.dto;

import com.pizzaguideapp.models.components.dto.ComponentDto;
import com.pizzaguideapp.models.media.dto.MediaDto;
import com.pizzaguideapp.models.reviews.dto.ReviewRequestDto;
import com.pizzaguideapp.models.steps.dto.StepsDto;
import com.pizzaguideapp.models.user.dto.UserIdentificationDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class RecipeIdentificationDto {
    private final String name;
    private final String description;
    private final UserIdentificationDto user;
    private final List<MediaDto> mediaList;
    private final List<StepsDto> steps;
    private final List<ComponentDto> components;
    private final List<ReviewRequestDto> reviews;

}
