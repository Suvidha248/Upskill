package com.upskiller.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name="roadmap")
public class Roadmap {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Goal goal;

    private Integer weekNumber;

    @Lob
    private String tasksJson;

    @Lob
    private String resourcesJson;

    // getters and setters
}
