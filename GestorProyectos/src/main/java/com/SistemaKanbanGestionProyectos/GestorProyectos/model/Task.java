package com.SistemaKanbanGestionProyectos.GestorProyectos.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_task;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", length = 225, nullable = false)
    private String description;
    @Column(name = "start_date", nullable = false)
    private String start_date;
    @Column(name = "due_date", nullable = false)
    private String due_date;
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "id_project")
    private Project project;

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }
    public Task() {

    }
    public Task(Long id_task, String name, String description, String start_date, String due_date, LocalDateTime createAt, LocalDateTime updateAt, Project project) {
        this.id_task = id_task;
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.due_date = due_date;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.project = project;
    }

    public Task(String name, String description, String start_date, String due_date, LocalDateTime createAt, LocalDateTime updateAt, Project project) {
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.due_date = due_date;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.project = project;
    }

    public Long getId_task() {
        return id_task;
    }

    public void setId_task(Long id_task) {
        this.id_task = id_task;
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
