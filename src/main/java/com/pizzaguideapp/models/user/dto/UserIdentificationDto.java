package com.pizzaguideapp.models.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class UserIdentificationDto {

    @NotBlank(message = "Can't be blank!")
    @NotNull
    private final Long id;
    @NotBlank(message = "Can't be blank!")
    @NotNull
    private final String username;
    @NotBlank(message = "Can't be blank!")
    @NotNull
    private final String email;
    @NotBlank(message = "Can't be blank!")
    @NotNull
    private final boolean enabled;
}
