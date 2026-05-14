package com.upskiller.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

    private Integer completedTasks;
    private Integer totalTasks;

    @Lob
    private String weakAreasJson;

    private LocalDateTime updatedAt = LocalDateTime.now();

    // Getters and setters

    public Long getId() {
        return id;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Integer getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(Integer completedTasks) {
        this.completedTasks = completedTasks;
    }

    public Integer getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(Integer totalTasks) {
        this.totalTasks = totalTasks;
    }

    public String getWeakAreasJson() {
        return weakAreasJson;
    }

    public void setWeakAreasJson(String weakAreasJson) {
        this.weakAreasJson = weakAreasJson;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
