package com.pizzaguideapp.models.chat.dto;

import com.pizzaguideapp.models.message.dto.MessageResponseDto;
import com.pizzaguideapp.models.user.dto.UserIdentificationDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ChatResponseDto {
    private final Long id;
    private final UserIdentificationDto sender;
    private final UserIdentificationDto receiver;
    private final List<MessageResponseDto> message;
}
