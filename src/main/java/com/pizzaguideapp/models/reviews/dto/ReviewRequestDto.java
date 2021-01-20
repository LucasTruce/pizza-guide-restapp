package com.pizzaguideapp.models.reviews.dto;

import com.pizzaguideapp.models.user.dto.UserIdentificationDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class ReviewRequestDto {

    private final int score;

    @NotBlank
    private final String description;

    private final UserIdentificationDto user;
}
