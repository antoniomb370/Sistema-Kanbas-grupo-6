package com.SistemaKanbanGestionProyectos.GestorProyectos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_task;
    @Column(name = "name")
    private String name;
    @Column(name = "description", length = 225)
    private String description;
    @Column(name = "start_date")
    private LocalDate start_date;
    @Column(name = "due_date")
    private LocalDate due_date;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "id_project")
    private Project project;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TaskStatus> task_status;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskType> tas_type;

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

    public Task(Long id_task, String name, String description, LocalDate start_date, LocalDate due_date, LocalDateTime createAt, LocalDateTime updateAt, Project project, List<TaskStatus> task_status, List<TaskType> tas_type) {
        this.id_task = id_task;
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.due_date = due_date;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.project = project;
        this.task_status = task_status;
        this.tas_type = tas_type;
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

    public LocalDate getStart_date(LocalDate start_date) {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }


    public LocalDate  getDue_date(LocalDate due_date) {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
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

    public List<TaskStatus> getTask_status() {
        return task_status;
    }

    public void setTask_status(List<TaskStatus> task_status) {
        this.task_status = task_status;
    }

    public List<TaskType> getTas_type() {
        return tas_type;
    }

    public void setTas_type(List<TaskType> tas_type) {
        this.tas_type = tas_type;
    }
}
