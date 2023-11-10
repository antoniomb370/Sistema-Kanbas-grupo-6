package com.SistemaKanbanGestionProyectos.GestorProyectos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_type")
public class TaskType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_task_type;

    @Column(name = "bug")
    private String bug;
    @Column(name = "story")
    private String story;


    @Column(name = "spike")
    private String spike;

    @Column(name = "bedt")
    private String bedt;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;


    public TaskType() {

    }

    public TaskType(Long id_task_type, String bug, String story, String spike, String bedt, Task task) {
        this.id_task_type = id_task_type;
        this.bug = bug;
        this.story = story;
        this.spike = spike;
        this.bedt = bedt;
        this.task = task;
    }

    public Long getId_task_type() {
        return id_task_type;
    }

    public void setId_task_type(Long id_task_type) {
        this.id_task_type = id_task_type;
    }

    public String getBug() {
        return bug;
    }

    public void setBug(String bug) {
        this.bug = bug;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getSpike() {
        return spike;
    }

    public void setSpike(String spike) {
        this.spike = spike;
    }

    public String getBedt() {
        return bedt;
    }

    public void setBedt(String bedt) {
        this.bedt = bedt;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}




