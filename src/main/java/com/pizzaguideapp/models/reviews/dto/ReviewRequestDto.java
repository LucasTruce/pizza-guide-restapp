package com.pizzaguideapp.models.reviews.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class ReviewRequestDto {

    private final int score;

    @NotBlank
    private final String description;
}
