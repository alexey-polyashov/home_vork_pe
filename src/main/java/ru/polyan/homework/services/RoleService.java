package ru.polyan.homework.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.polyan.homework.models.Role;
import ru.polyan.homework.models.User;
import ru.polyan.homework.repositories.RolesRepository;
import ru.polyan.homework.repositories.UsersRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RolesRepository rolesRepository;

    public Optional<Role> findName(String roleName) {
        return rolesRepository.findByName(roleName);
    }

}
