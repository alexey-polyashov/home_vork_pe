package ru.polyan.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class NewUserDto {

    @NotBlank(message = "Не указан логин")
    @UniqUserName
    private String username;

    @NotBlank(message = "Не указан email")
    @Pattern(
            regexp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$",
            message = "Не корректный email"
    )
    @UniqUserEmail
    private String email;

    private String address;
    @Pattern(
            regexp = "^\\+\\d{1}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$",
            message = "Не корректный номер телефона"
    )
    private String phone;

    @NotBlank(message = "Не указано имя")
    private String firstname;

    private String lastname;
    @Past(message = "Дата рождения не может быть позже текущей даты")
    private LocalDate birthday;

    @NotBlank(message = "Не указан пароль")
    private String password;

}
