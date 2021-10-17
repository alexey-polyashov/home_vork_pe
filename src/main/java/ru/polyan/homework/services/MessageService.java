package ru.polyan.homework.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.polyan.homework.dto.MessageDto;
import ru.polyan.homework.exceptions.ResourceNotFoundException;
import ru.polyan.homework.models.Message;
import ru.polyan.homework.models.User;
import ru.polyan.homework.repositories.MessagesRepository;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessagesRepository messagesRepository;

    public void createMessage(User user, String message, String theme){
        Message newMes = new Message();
        newMes.setUser_id(user.getId());
        newMes.setMessage(message);
        newMes.setTheme(theme);
        messagesRepository.save(newMes);
    }

    public Page<Message> getMessageList(int page, int recordsOnPage){
        return messagesRepository.findAll(PageRequest.of(page, recordsOnPage));
    }

    public Message getMessageData(Long messageId){
        return messagesRepository.findById(messageId).orElseThrow(()->new ResourceNotFoundException("Message with id '" + messageId + "' not found!"));
    }

}
