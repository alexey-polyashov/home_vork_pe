package ru.polyan.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.polyan.homework.models.User;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String address;
    private String phone;
    private String firstname;
    private String lastname;
    private LocalDate birthday;

    public UserDto(
            Long id, String username, String email, String address,
            String phone, String firstname, String lastname, LocalDate birthday) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.birthday = user.getBirthday();
    }

}
