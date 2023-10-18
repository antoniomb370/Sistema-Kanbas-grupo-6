package com.SistemaKanbanGestionProyectos.GestorProyectos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_status")
public class Task_status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_status_type;

    @Column(name = "to_do", unique = true)
    private boolean to_do;

    @Column(name = "in_progress", unique = true)
    private boolean in_progress;

    @Column(name = "blocked", unique = true)
    private boolean blocked;

    @Column(name = "to_test", unique = true)
    private boolean to_test;

    @Column(name = "done", unique = true)
    private boolean done;


    @ManyToOne
    @JoinColumn(name = "id_task")
    private Task task;

      public Task_status() {

      }

    public Task_status(Long id_status_type, boolean to_do, boolean in_progress, boolean blocked, boolean to_test, boolean done, Task task) {
        this.id_status_type = id_status_type;
        this.to_do = to_do;
        this.in_progress = in_progress;
        this.blocked = blocked;
        this.to_test = to_test;
        this.done = done;
        this.task = task;
    }

    public Task_status(boolean to_do, boolean in_progress, boolean blocked, boolean to_test, boolean done, Task task) {
        this.to_do = to_do;
        this.in_progress = in_progress;
        this.blocked = blocked;
        this.to_test = to_test;
        this.done = done;
        this.task = task;
    }

    public Long getId_status_type() {
        return id_status_type;
    }

    public void setId_status_type(Long id_status_type) {
        this.id_status_type = id_status_type;
    }

    public boolean isTo_do() {
        return to_do;
    }

    public void setTo_do(boolean to_do) {
        this.to_do = to_do;
    }

    public boolean isIn_progress() {
        return in_progress;
    }

    public void setIn_progress(boolean in_progress) {
        this.in_progress = in_progress;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isTo_test() {
        return to_test;
    }

    public void setTo_test(boolean to_test) {
        this.to_test = to_test;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
