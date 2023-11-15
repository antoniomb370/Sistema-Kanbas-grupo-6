package com.SistemaKanbanGestionProyectos.GestorProyectos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String status;

    public ProjectDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}


