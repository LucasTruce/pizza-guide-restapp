package com.pizzaguideapp.models.message.dto;

import com.pizzaguideapp.models.message.Message;
import com.pizzaguideapp.models.user.dto.UserConverter;
import com.pizzaguideapp.models.user.dto.UserIdentificationDto;

import java.util.List;
import java.util.stream.Collectors;

public class MessageConverter {

    public MessageResponseDto map(Message message) {
        UserConverter userConverter = new UserConverter();
        if(message == null){
            return new MessageResponseDto();
        }
        return new MessageResponseDto(
                message.getMessage(),
                message.getSendTime(),
                userConverter.map(message.getSender())
        );
    }

    public Message map(MessageRequestDto messageRequestDto) {
        Message message = new Message();
        message.setMessage(messageRequestDto.getMessage());
        return message;
    }


    public List<MessageResponseDto> map(List<Message> entityObjects){
        return entityObjects.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
