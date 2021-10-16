package ru.polyan.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.polyan.homework.models.Role;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@NoArgsConstructor
@Data
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String phone;
    private String firstname;
    private String lastname;
    private LocalDateTime birthday;

}
