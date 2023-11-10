package com.SistemaKanbanGestionProyectos.GestorProyectos.dto;

import java.time.LocalDateTime;

public class ProjectDto {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String status;

    public ProjectDto() {
    }


    public ProjectDto(Long id, String name, String description, LocalDateTime createAt, LocalDateTime updateAt, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public ProjectDto(String name, String description, LocalDateTime createAt, LocalDateTime updateAt, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}


