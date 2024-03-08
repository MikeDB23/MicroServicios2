package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.checkerframework.checker.units.qual.s;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.AbstractsIntegrationDBTest;
import com.example.demo.TestUtil;
import com.example.demo.entities.Suggestion;
import com.example.demo.entities.User;

public class SuggestionRepositoryTest extends AbstractsIntegrationDBTest{
    UserRepository userRepository;
    SuggestionRepository suggestionRepository;

    @Autowired
    public SuggestionRepositoryTest(UserRepository userRepository, SuggestionRepository suggestionRepository){
        this.userRepository = userRepository;
        this.suggestionRepository = suggestionRepository;
    }

    void init_test(){
        List<User> users = TestUtil.genUser();
        List<Suggestion> sugs = new ArrayList<>();
        sugs.add(Suggestion.builder()
                    .description("Contraten un aguatero")
                    .createAt(LocalDateTime.of(2010,6,9,8,2,4))
                    .user(users.get(1))
                    .build()
        );
        sugs.add(Suggestion.builder()
                    .description("Digale al 4 que deje de lesionarse")
                    .createAt(LocalDateTime.of(2012,10,5,21,5,20))
                    .user(users.get(0))
                    .build()
        );
        
        sugs.add(Suggestion.builder()
                    .description("Retirese")
                    .createAt(LocalDateTime.of(2022,12,22,10,30,50))
                    .user(users.get(0))
                    .build()
        );

        userRepository.saveAll(users);
        suggestionRepository.saveAll(sugs);
    }

    @BeforeEach
    void setUp() {
        suggestionRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    void givenTwoDates_whenFindBetweenDates_thenGetSuggestions(){
        init_test();
        List<Suggestion> sugs = suggestionRepository.findBycreateAtBetween(LocalDateTime.of(2012,1,1,0,0,0), LocalDateTime.of(2022,12,31,23,59,59));
        assertThat(sugs).hasSize(2);
    }

    @Test
    void givenAnUser_whenDeleteUserSuggestions_thenDeleteSuggestions(){
        init_test();
        User user = userRepository.findAll().get(0);
        List<Suggestion> sugs = suggestionRepository.findByUser(user);
        suggestionRepository.deleteAll(sugs);
        assertThat(suggestionRepository.findAll()).hasSize(1);
    }

    @Test
    void givenAUser_whenfindByUser_thenGetUserSuggestions(){
        init_test();
        User user = userRepository.findAll().get(0);
        List<Suggestion> sugs = suggestionRepository.findByUser(user);
        assertThat(sugs).hasSize(2);
    }
}
