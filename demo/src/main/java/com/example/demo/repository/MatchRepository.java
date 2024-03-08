package com.example.demo.repository;

import com.example.demo.entities.Match;
import com.example.demo.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findBySport(String sport);
    List<Match> findByCity(String city);
}
