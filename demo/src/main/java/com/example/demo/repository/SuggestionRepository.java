package com.example.demo.repository;

import com.example.demo.entities.Suggestion;
import com.example.demo.entities.User;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    List<Suggestion> findByUser(User user);
    List<Suggestion> findBycreateAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
