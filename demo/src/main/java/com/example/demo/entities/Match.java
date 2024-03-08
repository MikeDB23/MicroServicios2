package com.example.demo.entities;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="matches")
@NoArgsConstructor
@Setter
@Getter

public class Match {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String creator;
    private String sport;
    private String city;
    private String province;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime start_hour;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime end_hour;
    private Integer participants;
    private Integer substitudes;
    private String comments;

    @ManyToMany
    @JoinTable(name = "matches-users",
                joinColumns = @JoinColumn(name = "id_match", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"))    
    private List<User> users;

}

// docker run -d \
// 	--name microg2-postgres \
//     -e POSTGRES_USER=microg2 \
// 	-e POSTGRES_PASSWORD= microg2 \
//     -e POSTGRES_DB=microg2 \
//     -p 5432:5432\
//     postgres
