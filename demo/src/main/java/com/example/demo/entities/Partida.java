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
@Table(name="partidas")
@NoArgsConstructor
@Setter
@Getter

public class Partida {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String creador;
    private String deporte;
    private String ciudad;
    private String provincia;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime hora_comienzo;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime hora_final;
    private Integer participantes;
    private Integer suplentes;
    private String comentarios;

    @ManyToMany
    @JoinTable(name = "partidas-usuarios",
                joinColumns = @JoinColumn(name = "id_partida", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"))    
    private List<Usuario> usuarios;

}

// docker run -d \
// 	--name microg2-postgres \
//     -e POSTGRES_USER=microg2 \
// 	-e POSTGRES_PASSWORD= microg2 \
//     -e POSTGRES_DB=microg2 \
//     -p 5432:5432\
//     postgres
