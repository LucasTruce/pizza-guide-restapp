package com.pizzaguideapp.models.message.dto;

import com.pizzaguideapp.models.user.dto.UserIdentificationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MessageResponseDto {

    private String message;
    private LocalDateTime sendTime;
    private UserIdentificationDto sender;
}
