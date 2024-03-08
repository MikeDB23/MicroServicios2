package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.AbstractsIntegrationDBTest;
import com.example.demo.TestUtil;
import com.example.demo.entities.Match;
import com.example.demo.entities.User;

public class MatchRepositoryTest extends AbstractsIntegrationDBTest{
    UserRepository userRepository;
    MatchRepository matchRepository;

    @Autowired
    public MatchRepositoryTest(UserRepository userRepository, MatchRepository matchRepository) {
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
    }

    void init_test(){
        List<User> users = TestUtil.genUser();
        List<User> users2 = new ArrayList<>();
        users2.add(users.get(0));
        List<Match> matches = new ArrayList<>();
        matches.add(Match.builder()
                    .creator("Julio")
                    .city("Santa Marta")
                    .sport("Soccer")
                    .participants(25)
                    .substitudes(10)
                    .users(users)
                    .build()
                    );
        matches.add(Match.builder()
                    .creator("Jose")
                    .city("Barranquilla")
                    .sport("Archery")
                    .participants(12)
                    .substitudes(6)
                    .users(users2)
                    .build()
                    );
        matches.add(Match.builder()
                    .creator("Carlos")
                    .city("Barranquilla")
                    .sport("Soccer")
                    .participants(25)
                    .substitudes(6)
                    .users(users2)
                    .build()
                    );
        
        userRepository.saveAll(users);
        matchRepository.saveAll(matches);
    }

    @BeforeEach
    void setUp() {
        matchRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void givenASport_whenFindBySport_thenGetAll(){
        init_test();
        List<Match> matches = matchRepository.findBySport("Soccer");
        assertThat(matches).hasSize(2);
    }

    @Test
    void givenACity_whenDeleteByCity_thenDeleteMatches(){
        init_test();
        List<Match> matches = matchRepository.findByCity("Barranquilla");
        matchRepository.deleteAll(matches);
        assertThat(matchRepository.findAll()).hasSize(1);
    }

    @Test
    void givenAnInt_whenSetParticipants_thenUpdateMatchParticipants(){
        init_test();
        Match match = matchRepository.findAll().get(1);

        match.setParticipants(30);
        matchRepository.save(match);

        Optional<Match> updatedMatchOptional = matchRepository.findById(match.getId());
        assertTrue(updatedMatchOptional.isPresent(), "Match not found");

        Match updatedMatch = updatedMatchOptional.get();
        assertEquals(match.getParticipants(), updatedMatch.getParticipants(), "Partcipants not updated");
    }
}
