package com.upskiller.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="learning_resources")
public class LearningResource {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skill;
    private String title;
    private String url;
    private String type;

    // getters and setters
}
