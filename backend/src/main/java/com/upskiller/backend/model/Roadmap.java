package com.upskiller.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roadmap")
public class Roadmap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

    private Integer weekNumber;

    @Lob
    private String tasksJson;

    @Lob
    private String resourcesJson;

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

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public String getTasksJson() {
        return tasksJson;
    }

    public void setTasksJson(String tasksJson) {
        this.tasksJson = tasksJson;
    }

    public String getResourcesJson() {
        return resourcesJson;
    }

    public void setResourcesJson(String resourcesJson) {
        this.resourcesJson = resourcesJson;
    }
}
