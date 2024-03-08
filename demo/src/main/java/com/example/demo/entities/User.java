package com.example.demo.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String username;
    private String mail;
    private String name;
    private String surname;
    private Integer age;
    private String password;
    private String rep_password;
    private Boolean active;
    private String picture;
    private String role;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;
    
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Suggestion> suggestions;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Message> messages;

    @ManyToMany(mappedBy = "users")
    private List<Match> matches;
}
