package com.pizzaguideapp.models.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public class UserRegisterDto {
    @NotBlank(message = "Can't be blank!")
    @NotNull
    private final String username;
    @NotBlank(message = "Can't be blank!")
    @NotNull
    private final String password;
    @NotBlank(message = "Can't be blank!")
    @NotNull
    private final String email;
}
