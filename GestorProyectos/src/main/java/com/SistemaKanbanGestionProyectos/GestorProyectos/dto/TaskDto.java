package com.SistemaKanbanGestionProyectos.GestorProyectos.dto;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Task;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Long id_task;
    private String name;
    private String description;
    private String currentStatus;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private TaskStatusDto taskStatus;
    private TaskTypeDto taskType;
    private String taskTypeString;
    private Project project;


    public TaskDto(Long id_task, String name, String description, LocalDate startDate, LocalDate dueDate, String taskTypeString, String currentStatus) {
        this.id_task = id_task;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.taskTypeString = taskTypeString;
        this.currentStatus = currentStatus;
    }
    public Long getId_task() {
        return id_task;
    }

    public void setId_task(Long id_task) {
        this.id_task = id_task;
    }

}
