package ru.polyan.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.polyan.homework.models.Message;

@NoArgsConstructor
@Data
public class MessageDto {

    private Long id;
    private String message;
    private String theme;
    private Long userId;

    public MessageDto(Long id, String message, String theme, Long userId) {
        this.id = id;
        this.message = message;
        this.theme = theme;
        this.userId = userId;
    }
    public MessageDto(Message mes) {
        this.id = mes.getId();
        this.message = mes.getMessage();
        this.theme = mes.getTheme();
        this.userId = mes.getUser_id();
    }
}
