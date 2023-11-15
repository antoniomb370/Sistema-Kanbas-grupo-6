package com.SistemaKanbanGestionProyectos.GestorProyectos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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




}
