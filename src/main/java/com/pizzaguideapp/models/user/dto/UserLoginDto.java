package com.pizzaguideapp.models.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
public class UserLoginDto {

    @NotBlank(message = "Can't be blank!")
    private final String username;

    @NotBlank(message = "Can't be blank!")
    private final String password;

}
