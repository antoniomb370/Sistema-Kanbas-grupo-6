package com.SistemaKanbanGestionProyectos.GestorProyectos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatusDto {

    private Long id_status_type;

    private boolean toDo;
    private boolean inProgress;
    private boolean blocked;
    private boolean done;

}
