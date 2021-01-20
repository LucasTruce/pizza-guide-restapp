package com.pizzaguideapp.models.chat;

import com.pizzaguideapp.models.chat.dto.ChatRequestDto;
import com.pizzaguideapp.models.chat.dto.ChatResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chats")
    public ResponseEntity<Page<ChatResponseDto>> getChatsForUser(Principal principal,
                                                                 @RequestParam(defaultValue = "0") int pageNumber,
                                                                 @RequestParam(defaultValue = "15") int pageSize,
                                                                 @RequestParam(defaultValue = "id") String orderBy,
                                                                 @RequestParam(defaultValue = "ASC") String direction){
        return new ResponseEntity<>(chatService.getChatsForUser(principal.getName(), pageNumber, pageSize, orderBy, direction), HttpStatus.OK);
    }

    @PostMapping("/chats") // must be receiver with username whom we want to chat
    public ResponseEntity<ChatResponseDto> saveChat(@RequestBody @Valid ChatRequestDto chatRequestDto, Principal principal){
        return new ResponseEntity<>(chatService.saveChat(chatRequestDto, principal.getName()), HttpStatus.OK);
    }


}
