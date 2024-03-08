package com.example.demo.repository;

import com.example.demo.entities.Message;
import com.example.demo.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByUser(User user);
    List<Message> findByCreatorAndDestinatary(String creator, String destinatary);
}
