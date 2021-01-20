package com.pizzaguideapp.models.chat;

import com.pizzaguideapp.exception.EntityNotFoundException;
import com.pizzaguideapp.models.chat.dto.ChatConverter;
import com.pizzaguideapp.models.chat.dto.ChatRequestDto;
import com.pizzaguideapp.models.chat.dto.ChatResponseDto;
import com.pizzaguideapp.models.message.MessageRepository;
import com.pizzaguideapp.models.user.User;
import com.pizzaguideapp.models.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatConverter chatConverter;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.chatConverter = new ChatConverter();
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Page<ChatResponseDto> getChatsForUser(String username, int pageNumber, int pageSize, String orderBy, String direction){
        Sort.Direction tempDirection;
        if(direction.equals("ASC"))
            tempDirection = Sort.Direction.ASC;
        else
            tempDirection = Sort.Direction.DESC;

        Page<Chat> chats = chatRepository.findAllBySenderUsernameOrReceiverUsername(username, username, PageRequest.of(pageNumber, pageSize, Sort.by(tempDirection, orderBy)));
        chats.forEach(c -> c.getMessages().clear());
        chats.forEach(c -> c.getMessages().add(messageRepository.findTopByChatIdOrderBySendTimeDesc(c.getId())));

        return chats.map(chatConverter::map);
    }

    public ChatResponseDto saveChat(ChatRequestDto chatRequestDto, String username){
        User owner = userRepository.findUserByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found by username "));
        User receiver = userRepository.findUserByUsername(chatRequestDto.getUsername()).orElseThrow(() -> new EntityNotFoundException("User not found by username"));
        Chat chat = chatConverter.map(chatRequestDto);
        chat.setSender(owner);
        chat.setReceiver(receiver);
        return chatConverter.map(chatRepository.save(chat));
    }
}
