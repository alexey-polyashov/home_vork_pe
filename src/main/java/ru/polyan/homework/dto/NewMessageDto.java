package ru.polyan.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@NoArgsConstructor
@Data
public class NewMessageDto {

    @NotBlank(message = "Сообщение пустое")
    private String message;
    @NotBlank(message = "Не указана тема")
    private String theme;

}
