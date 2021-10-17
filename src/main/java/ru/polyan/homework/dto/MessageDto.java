package ru.polyan.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.polyan.homework.models.Message;
import ru.polyan.homework.models.User;

@NoArgsConstructor
@Data
public class MessageDto {

    private Long id;
    private String message;
    private String theme;
    private UserDto user;

    public MessageDto(Long id, String message, String theme, User user) {
        this.id = id;
        this.message = message;
        this.theme = theme;
        this.user = new UserDto(user);
    }
    public MessageDto(Message mes) {
        this.id = mes.getId();
        this.message = mes.getMessage();
        this.theme = mes.getTheme();
        this.user = new UserDto(mes.getUser());
    }
}
