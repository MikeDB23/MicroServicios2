package com.example.demo.repository;

import com.example.demo.AbstractsIntegrationDBTest;
import com.example.demo.TestUtil;
import com.example.demo.entities.Message;
import com.example.demo.entities.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

// import static org.junit.jupiter.api.Assertions.*;

class MessageRepositoryTest extends AbstractsIntegrationDBTest {
    UserRepository userRepository;
    MessageRepository messageRepository;

    @Autowired
    public MessageRepositoryTest(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    void init_test(){
        List<User> users = TestUtil.genUser();
        userRepository.saveAll(users);

        List<Message> mss = new ArrayList<>();
        mss.add(Message.builder()
                    .creator("Juan")
                    .destinatary("Camilo")
                    .user(users.get(0))
                    .content("love u")
                    .build()
        );
        mss.add(Message.builder()
                    .creator("Juan")
                    .destinatary("Camilo")
                    .user(users.get(0))
                    .content("reminder that I love u")
                    .build()
        );
        mss.add(Message.builder()
                    .creator("Camile")
                    .destinatary("Unexisted user")
                    .user(users.get(1))
                    .content("hate u")
                    .build()
        );

        messageRepository.saveAll(mss);
    }
    
    @BeforeEach
    void setUp() {
        messageRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void givenAMessage_whenFindByUser_thenGetUserMessages(){
        init_test();
        List<Message> messages =  messageRepository.findByUser(userRepository.findByNameLike("Juan").get(0));
        assertThat(messages).isNotEmpty();
    }

    @Test
    void givenAString_whenSetContent_thenUpdateMessageContent(){
        init_test();
        Message ms = messageRepository.findAll().get(1);

        ms.setContent("it was a lie :c");
        messageRepository.save(ms);

        Optional<Message> updatedMessageOptional = messageRepository.findById(ms.getId());
        assertTrue(updatedMessageOptional.isPresent(), "Message not found");

        Message updatedMessage = updatedMessageOptional.get();
        assertEquals(ms.getContent(), updatedMessage.getContent(), "Content not updated");
    }

    @Test
    void givenAMessageArray_whenDeleteAll_thenDeleteMessages(){
        init_test();
        List<Message> mss = messageRepository.findByCreatorAndDestinatary("Juan", "Camilo");
        messageRepository.deleteAll(mss);

        assertThat(messageRepository.findAll()).hasSize(1);
    }

    @Test
    void givenACreatorAndDestinatary_whenFindByCreatorAndDestinatary_thenGetMessageList(){
        init_test();
        List<Message> mss = messageRepository.findByCreatorAndDestinatary("Juan", "Camilo");
        assertThat(mss).hasSize(2);
    }
}