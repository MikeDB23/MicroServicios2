package com.example.demo.repository;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameAndSurname(String name, String surname);
    List<User> findByUsernameOrMail(String username, String mail);
    List<User> findByNameLike(String name);
}
