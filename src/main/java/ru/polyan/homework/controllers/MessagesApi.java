package ru.polyan.homework.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.polyan.homework.dto.MessageDto;
import ru.polyan.homework.exceptions.ResourceNotFoundException;
import ru.polyan.homework.exceptions.ServiceError;
import ru.polyan.homework.models.Message;
import ru.polyan.homework.models.User;
import ru.polyan.homework.services.MessageService;
import ru.polyan.homework.services.UserService;
import ru.polyan.homework.utils.Checker;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/messages/")
@Slf4j
public class MessagesApi {

    private final MessageService messageService;
    private final UserService userService;

    @GetMapping(value = "/")
    @ResponseBody
    public Page<MessageDto> getMessageList(@RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "5") int recordsOnPage){
        Page<Message> messageList = messageService.getMessageList(page, recordsOnPage);
        Page<MessageDto> messageDtoList = messageList.map(p-> new MessageDto(p));
        return messageDtoList;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public MessageDto getMessage(@PathVariable(name="id") Long messageId){
        Message message = messageService.getMessageData(messageId);
        return new MessageDto(message);
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseEntity<?>  addMessage(Principal principal, @RequestParam Map<String, String> messageData){

        Map<String, String> reqFields = new HashMap<>()
        {{
            put("theme", "Theme");
            put("message", "Message");
        }};

        ServiceError srvError = Checker.checkReqFields(reqFields, messageData);

        String userName = principal.getName();
        User user = userService.findByUsername(userName).orElseThrow(()->new ResourceNotFoundException("User name '" + userName + "' not found"));

        if(!srvError.getMessage().isBlank()){
            return new ResponseEntity(srvError, HttpStatus.BAD_REQUEST);
        }

        messageService.createMessage(user, messageData.get("message"), messageData.get("theme"));

        return new ResponseEntity(HttpStatus.OK);

    }

}
