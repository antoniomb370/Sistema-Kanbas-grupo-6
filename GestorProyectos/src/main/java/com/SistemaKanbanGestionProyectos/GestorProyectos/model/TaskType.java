package com.SistemaKanbanGestionProyectos.GestorProyectos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_type")
public class TaskType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_task_type;
    @Column(name = "bug")
    private boolean bug;
    @Column(name = "story")
    private boolean story;
    @Column(name = "spike")
    private boolean spike;
    @Column(name = "bedt")
    private boolean bedt;

    public TaskType() {

    }

    public Long getId_task_type() {
        return id_task_type;
    }

    public void setId_task_type(Long id_task_type) {
        this.id_task_type = id_task_type;
    }

    public boolean isBug() {
        return bug;
    }

    public void setBug(boolean bug) {
        this.bug = bug;
    }

    public boolean isStory() {
        return story;
    }

    public void setStory(boolean story) {
        this.story = story;
    }

    public boolean isSpike() {
        return spike;
    }

    public void setSpike(boolean spike) {
        this.spike = spike;
    }

    public boolean isBedt() {
        return bedt;
    }

    public void setBedt(boolean bedt) {
        this.bedt = bedt;
    }

}




