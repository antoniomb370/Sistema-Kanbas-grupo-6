package com.SistemaKanbanGestionProyectos.GestorProyectos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "project_status")
public class ProjectStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_status;

    @Column(name = "active", unique = true)
    private boolean active;

    @Column(name = "inactive", unique = true)
    private boolean inactive;

    @Column(name = "paused", unique = true)
    private boolean paused;

    // relacion uno a muchos entre project y project_status

      @ManyToOne
      @JoinColumn(name = "id_project")
      private Project project;

    public ProjectStatus(Long id_status, boolean active, boolean inactive, boolean paused, Project project) {
        this.id_status = id_status;
        this.active = active;
        this.inactive = inactive;
        this.paused = paused;
        this.project = project;
    }

      public ProjectStatus() {

      }

      public ProjectStatus(boolean active, boolean inactive, boolean paused, Project project) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public boolean isPaused() {
        return paused;
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

}
