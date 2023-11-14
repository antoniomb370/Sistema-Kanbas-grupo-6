package com.SistemaKanbanGestionProyectos.GestorProyectos.dto;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;


public class ProjectStatusDto {


    private Long id_status;
    private boolean active;
    private boolean inactive;
    private boolean paused;

    private Project project;

    public ProjectStatusDto(boolean active, boolean inactive, boolean paused, Project project) {
        this.active = active;
        this.inactive = inactive;
        this.paused = paused;
        this.project = project;
    }

    public ProjectStatusDto() {

    }

    public ProjectStatusDto(Long id_status, boolean active, boolean inactive, boolean paused, Project project) {
        this.id_status = id_status;
        this.active = active;
        this.inactive = inactive;
        this.paused = paused;
        this.project = project;
    }

    public Long getId_status() {
        return id_status;
    }

    public void setId_status(Long id_status) {
        this.id_status = id_status;
    }

    public void getActive(boolean active) {
        this.active = active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public void getInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public void getPaused(boolean paused) {
        this.paused = paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isInactive() {
        return inactive;
    }

    public boolean isPaused() {
        return paused;
    }
}