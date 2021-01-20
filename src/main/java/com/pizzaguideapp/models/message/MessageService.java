package com.pizzaguideapp.models.message;

import com.pizzaguideapp.exception.EntityNotFoundException;
import com.pizzaguideapp.models.chat.Chat;
import com.pizzaguideapp.models.chat.ChatRepository;
import com.pizzaguideapp.models.message.dto.MessageConverter;
import com.pizzaguideapp.models.message.dto.MessageRequestDto;
import com.pizzaguideapp.models.message.dto.MessageResponseDto;
import com.pizzaguideapp.models.user.User;
import com.pizzaguideapp.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    private final MessageConverter messageConverter;
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, ChatRepository chatRepository, UserRepository userRepository) {
        this.messageConverter = new MessageConverter();
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public Page<MessageResponseDto> getMessageForOwnerUser(Long chatId, int pageNumber, int pageSize, String orderBy, String direction){
        Sort.Direction tempDirection;
        if(direction.equals("ASC"))
            tempDirection = Sort.Direction.ASC;
        else
            tempDirection = Sort.Direction.DESC;

        Page<Message> messages = messageRepository.findAllByChatId(chatId, PageRequest.of(pageNumber, pageSize, Sort.by(tempDirection, orderBy)));
        return messages.map(messageConverter::map);
    }

    public MessageResponseDto saveMessage(Long chatId, MessageRequestDto messageRequestDto, String ownerLogin){
        Message message = messageConverter.map(messageRequestDto);
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new EntityNotFoundException("Chat not found by id!"));
        User owner = userRepository.findUserByUsername(ownerLogin).orElseThrow(() -> new EntityNotFoundException("User not found by id!"));
        message.setChat(chat);
        message.setSender(owner);
        message.setSendTime(LocalDateTime.now().plusHours(1));

        return messageConverter.map(messageRepository.save(message));
    }

}
