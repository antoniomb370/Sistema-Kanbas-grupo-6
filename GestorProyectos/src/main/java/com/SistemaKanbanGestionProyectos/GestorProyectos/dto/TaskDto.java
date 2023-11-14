package com.SistemaKanbanGestionProyectos.GestorProyectos.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
public class TaskDto {

    private Long id_task;
    private String name;
    private String description;
    private String currentStatus;
    private LocalDate start_date;
    private LocalDate due_date;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private TaskStatusDto taskStatus;
    private TaskTypeDto taskType;
    private String taskTypeString;

    public TaskDto() {
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

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
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

    public TaskStatusDto getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatusDto taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskTypeDto getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskTypeDto taskType) {
        this.taskType = taskType;
    }

    public String getTaskTypeString() {
        return taskTypeString;
    }

    public void setTaskTypeString(String taskTypeString) {
        this.taskTypeString = taskTypeString;
    }
}
