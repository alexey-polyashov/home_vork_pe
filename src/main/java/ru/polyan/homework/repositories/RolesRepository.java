package ru.polyan.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.polyan.homework.models.Role;
import ru.polyan.homework.models.User;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
