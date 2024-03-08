package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.User;

public class TestUtil {
    public static List<User> genUser(){
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                    .username("Mike")        
                    .mail("juanQ@gmail.com")
                    .name("Juan")
                    .surname("Quintero")
                    .age(21)
                    .password("123")
                    .rep_password("123")
                    .active(false)
                    .picture("img.png")
                    .role("pet")
                    .build()
        );
        
        users.add(User.builder()
                    .username("Cooler Juan")        
                    .mail("juanQ@gmail.com")
                    .name("Camilo")
                    .surname("Galvis")
                    .age(21)
                    .password("456")
                    .rep_password("456")
                    .active(true)
                    .picture("cooler_img.png")
                    .role("carry")
                    .build()
        );
        return users;
    }
}
