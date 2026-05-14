package com.upskiller.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="reflections")
public class Reflection {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Goal goal;

    @Lob
    private String reflectionText;

    @Lob
    private String aiFeedback;

    private LocalDateTime createdAt = LocalDateTime.now();

    // getters and setters
}
