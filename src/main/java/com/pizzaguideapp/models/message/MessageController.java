package com.pizzaguideapp.models.message;

import com.pizzaguideapp.models.message.dto.MessageRequestDto;
import com.pizzaguideapp.models.message.dto.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/chats/{id}/messages")
    public ResponseEntity<Page<MessageResponseDto>> getMessages(@PathVariable(name = "id") Long id,
                                                                @RequestParam(defaultValue = "0") int pageNumber,
                                                                @RequestParam(defaultValue = "20") int pageSize,
                                                                @RequestParam(defaultValue = "id") String orderBy,
                                                                @RequestParam(defaultValue = "ASC") String direction){
        return new ResponseEntity<>(messageService.getMessageForOwnerUser(id, pageNumber, pageSize, orderBy, direction), HttpStatus.OK);
    }

    @PostMapping("/chats/{id}/messages") //must have message
    public ResponseEntity<MessageResponseDto> saveMessage(@PathVariable(name = "id") Long id, @RequestBody MessageRequestDto messageRequestDto, Principal principal){
        return new ResponseEntity<>(messageService.saveMessage(id, messageRequestDto, principal.getName()), HttpStatus.OK);
    }

}
