package com.SistemaKanbanGestionProyectos.GestorProyectos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Long id;


    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @Column(name = "status")
    private String status;

    @JsonManagedReference
    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
    private ProjectStatus  projectStatuses;


    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }

    public ProjectStatus getProjectStatuses() {
        return projectStatuses;
    }

    public void setProjectStatuses(ProjectStatus projectStatuses) {
        this.projectStatuses = projectStatuses;
    }

    public Project() {
    }

    public Project(String name, String description, LocalDateTime createAt, LocalDateTime updateAt, ProjectStatus projectStatuses, String status) {
        this.name = name;
        this.description = description;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.projectStatuses = projectStatuses;
         this.status = status;
    }

    public Project(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


    public void setTasks(List<Task> tasks) {
    }
}
