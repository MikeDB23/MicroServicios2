package com.example.demo.repository;

import com.example.demo.AbstractsIntegrationDBTest;
import com.example.demo.TestUtil;
import com.example.demo.entities.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends AbstractsIntegrationDBTest{
    UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    void initUserTest() {
        List<User> users = TestUtil.genUser(); 
        userRepository.saveAll(users);
        userRepository.flush();
    }

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void givenAnUser_whenSave_thenUserwithId(){
        //given
        User user = User.builder()
                .username("Pzr")        
                .mail("pizarapQ@gmail.com")
                .name("Pablo")
                .surname("Mango")
                .age(30)
                .password("a")
                .rep_password("a")
                .active(true)
                .picture("random_img.png")
                .role("trainer")
                .build();
        //when
        User userSaved = userRepository.save(user);
        //then
        assertThat(userSaved.getId()).isNotNull();

    }
    @Test
    @DisplayName("dado un conjunto de usuarios al buscarlo todos obtenemos la lista de los usuarios en la base de datos")
    void shouldGetAllUsers(){
        initUserTest();
        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(2);
    }

    @Test
    void givenUsers_whenFindByNameAndSurname_thenGetUserList(){
        initUserTest();
        List<User> users = userRepository.findByNameAndSurname("Juan", "Quintero");
        assertThat(users).isNotEmpty();
        assertThat(users).first().hasFieldOrPropertyWithValue("name","Juan");
    }

    @Test
    void givenAnUser_whenDeleteByUser_thenDeleteUser(){
        initUserTest();
        User objective = userRepository.findAll().get(0);
        userRepository.delete(objective);
        assertThat(userRepository.findAll()).hasSize(1);

    }
}