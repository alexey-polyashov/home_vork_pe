package ru.polyan.homework.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.polyan.homework.models.Message;
import ru.polyan.homework.models.User;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Long> {
    Page<Message> findByUser(User user, Pageable pageRequest);
}

