package ru.polyan.homework.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.polyan.homework.dto.MessageDto;
import ru.polyan.homework.dto.NewMessageDto;
import ru.polyan.homework.exceptions.ResourceNotFoundException;
import ru.polyan.homework.models.Message;
import ru.polyan.homework.models.User;
import ru.polyan.homework.services.MessageService;
import ru.polyan.homework.services.UserService;

import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/messages/")
@Slf4j
public class MessagesApi {

    private final MessageService messageService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/")
    @ResponseBody
    public Page<MessageDto> getMessageList(@RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "5") int recordsOnPage,
                                           @RequestParam(required = false, defaultValue = "0") Long userId){
        Page<Message> messageList = messageService.getMessageList(page, recordsOnPage, userId);
        Page<MessageDto> messageDtoList = messageList.map(p-> new MessageDto(p));
        return messageDtoList;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public MessageDto getMessage(@PathVariable(name="id") Long messageId){
        Message message = messageService.getMessageData(messageId);
        return modelMapper.map(message, MessageDto.class);
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseEntity<?>  addMessage(Principal principal, @Valid @RequestBody NewMessageDto messageData){

        String userName = principal.getName();
        User user = userService.findByUsername(userName).orElseThrow(()->new ResourceNotFoundException("User name '" + userName + "' not found"));
        Message message = modelMapper.map(messageData, Message.class);
        message.setUser(user);
        messageService.createMessage(message);

        return new ResponseEntity(HttpStatus.OK);

    }

}
