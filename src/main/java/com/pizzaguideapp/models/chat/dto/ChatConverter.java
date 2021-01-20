package com.pizzaguideapp.models.chat.dto;

import com.pizzaguideapp.models.chat.Chat;
import com.pizzaguideapp.models.message.dto.MessageConverter;
import com.pizzaguideapp.models.user.dto.UserConverter;

import java.util.List;
import java.util.stream.Collectors;

public class ChatConverter {

    public ChatResponseDto map(Chat chat) {
        UserConverter userConverter = new UserConverter();
        MessageConverter messageConverter = new MessageConverter();
        return new ChatResponseDto(
                chat.getId(),
                userConverter.map(chat.getSender()),
                userConverter.map(chat.getReceiver()),
                messageConverter.map(chat.getMessages())
        );
    }

    public Chat map(ChatRequestDto requestDto) {
        return new Chat();
    }


    public List<ChatResponseDto> map(List<Chat> entityObjects){
        return entityObjects.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
