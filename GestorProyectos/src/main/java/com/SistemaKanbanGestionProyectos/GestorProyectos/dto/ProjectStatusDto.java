package com.SistemaKanbanGestionProyectos.GestorProyectos.dto;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectStatusDto {


    private Long id_status;
    private boolean active;
    private boolean inactive;
    private boolean paused;
    private Project project;



}