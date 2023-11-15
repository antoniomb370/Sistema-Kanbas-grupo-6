package com.SistemaKanbanGestionProyectos.GestorProyectos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskTypeDto {

    private Long id_task_type;
    private boolean bug;
    private boolean story;
    private boolean spike;
    private boolean bedt;
    private boolean task;




}
