package com.SistemaKanbanGestionProyectos.GestorProyectos.dto;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Task;
import jakarta.persistence.*;

public class TaskTypeDto {

    private Long id_task_type;
    private String bug;
    private String story;
    private String spike;
    private String bedt;
    private Task task;

      public TaskTypeDto() {
      }

      public TaskTypeDto(Long id_task_type, String bug, String story, String spike, String bedt, Task task) {
          this.id_task_type = id_task_type;
          this.bug = bug;
          this.story = story;
          this.spike = spike;
          this.bedt = bedt;
          this.task = task;
      }

    public TaskTypeDto(String bug, String story, String spike, String bedt, Task task) {
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
