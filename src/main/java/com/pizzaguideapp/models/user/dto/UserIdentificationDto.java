package com.pizzaguideapp.models.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class UserIdentificationDto {

    private final Long id;

    private final String username;

    private final String email;

    private final boolean enabled;
}
