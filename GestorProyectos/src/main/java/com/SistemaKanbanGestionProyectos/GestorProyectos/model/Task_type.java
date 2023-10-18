package com.SistemaKanbanGestionProyectos.GestorProyectos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_type")
public class Task_type {

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

    @Column(name = "task")
      private String task;

    @ManyToOne
    @JoinColumn(name = "id_task")
    private Task task_id;

    public Task_type() {

    }
    public Task_type(Long id_task_type, String bug, String story, String spike, String bedt, String task, Task task_id) {
        this.id_task_type = id_task_type;
        this.bug = bug;
        this.story = story;
        this.spike = spike;
        this.bedt = bedt;
        this.task = task;
        this.task_id = task_id;
    }

    public Task_type(String bug, String story, String spike, String bedt, String task, Task task_id) {
        this.bug = bug;
        this.story = story;
        this.spike = spike;
        this.bedt = bedt;
        this.task = task;
        this.task_id = task_id;
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

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Task getTask_id() {
        return task_id;
    }

    public void setTask_id(Task task_id) {
        this.task_id = task_id;
    }
}




