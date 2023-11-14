package com.SistemaKanbanGestionProyectos.GestorProyectos.dto;

public class TaskTypeDto {

    private Long id_task_type;
    private boolean bug;
    private boolean story;
    private boolean spike;
    private boolean bedt;
    private boolean task;

    public TaskTypeDto() {
    }

    public TaskTypeDto(Long id_task_type, boolean bug, boolean story, boolean spike, boolean bedt, boolean task) {
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

    public boolean isTask() {
        return task;
    }

    public void setTask(boolean task) {
        this.task = task;
    }
}
