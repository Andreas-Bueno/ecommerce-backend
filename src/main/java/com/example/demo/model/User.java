package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Entity(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private String log;

    @PrePersist
    public void prePersist() {
        // Formata as datas para inserção no banco (se necessário em String)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedLogTime = LocalDateTime.now().withNano(0).format(formatter);

        // Define apenas a data/hora no campo 'log' e 'created', sem texto adicional
        this.setLog(formattedLogTime); // log como String
        this.setCreated(LocalDateTime.now().withNano(0)); // created como LocalDateTime
    }
}
