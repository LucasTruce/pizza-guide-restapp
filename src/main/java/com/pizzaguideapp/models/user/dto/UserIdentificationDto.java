package com.pizzaguideapp.models.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserIdentificationDto {

    private final Long id;
    private final String username;
    private final String email;
    private final boolean enabled;
}
