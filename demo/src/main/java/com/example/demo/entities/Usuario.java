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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuarios")
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String username;
    private String mail;
    private String name;
    private String apellidos;
    private Integer edad;
    private String contraseña;
    private String rep_password;
    private Boolean activo;
    private String foto;
    private String rol;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;
    
    @OneToMany(mappedBy = "usuario", orphanRemoval = true)
    private List<Sugerencia> sugerencias;

    @OneToMany(mappedBy = "usuario", orphanRemoval = true)
    private List<Mensaje> mensajes;

    @ManyToMany(mappedBy = "usuarios")
    private List<Partida> partidas;
}
