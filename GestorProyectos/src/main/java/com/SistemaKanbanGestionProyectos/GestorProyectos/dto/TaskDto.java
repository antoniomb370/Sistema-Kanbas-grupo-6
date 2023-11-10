package com.SistemaKanbanGestionProyectos.GestorProyectos.dto;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.TaskStatus;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TaskDto {

    private Long id_task;

    private String name;

    private String description;

    private LocalDate start_date;

    private LocalDate due_date;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private List<TaskStatus> task_status;

    private TaskTypeDto taskType;

    public TaskDto(TaskTypeDto taskType) {
        this.taskType = taskType;
    }

    public TaskTypeDto getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskTypeDto taskType) {
        this.taskType = taskType;
    }

    private List<TaskType> tas_type;


      public TaskDto() {
      }

      public TaskDto(Long id_task, String name, String description, LocalDate start_date, LocalDate due_date, LocalDateTime createAt, LocalDateTime updateAt, List<TaskStatus> task_status, List<TaskType> tas_type) {
          this.id_task = id_task;
          this.name = name;
          this.description = description;
          this.start_date = start_date;
          this.due_date = due_date;
          this.createAt = LocalDateTime.now();
          this.updateAt = LocalDateTime.now();
          this.task_status = task_status;
          this.tas_type = tas_type;
      }

      public TaskDto(String name, String description, LocalDate start_date, LocalDate due_date, LocalDateTime createAt, LocalDateTime updateAt, List<TaskStatus> task_status, List<TaskType> tas_type) {
          this.name = name;
          this.description = description;
          this.start_date = start_date;
          this.due_date = due_date;
          this.createAt = createAt;
          this.updateAt = updateAt;
          this.task_status = task_status;
          this.tas_type = tas_type;
      }

    public Long getId_task() {
        return id_task;
    }

    public void setId_task(Long id_task) {
        this.id_task = id_task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date() {
        this.start_date = start_date;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date() {
        this.due_date = due_date;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public List<TaskStatus> getTask_status() {
        return task_status;
    }

    public void setTask_status(List<TaskStatus> task_status) {
        this.task_status = task_status;
    }

    public List<TaskType> getTas_type() {
        return tas_type;
    }

    public void setTas_type(List<TaskType> tas_type) {
        this.tas_type = tas_type;
    }


}
