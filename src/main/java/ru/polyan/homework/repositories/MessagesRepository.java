package ru.polyan.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.polyan.homework.models.Message;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Long> {

}
