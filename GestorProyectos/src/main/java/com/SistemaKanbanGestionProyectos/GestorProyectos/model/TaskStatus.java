package com.SistemaKanbanGestionProyectos.GestorProyectos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "task_status")
public class TaskStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_status_type;

    @Column(name = "to_do")
    private boolean to_do;

    @Column(name = "in_progress")
    private boolean in_progress;

    @Column(name = "blocked")
    private boolean blocked;

    @Column(name = "to_test")
    private boolean to_test;

    @Column(name = "done")
    private boolean done;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonBackReference
    private Task task;

      public TaskStatus() {

      }

    public TaskStatus(Long id_status_type, boolean to_do, boolean in_progress, boolean blocked, boolean to_test, boolean done, Task task) {
        this.id_status_type = id_status_type;
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

    public boolean getTo_do() {
        return to_do;
    }

    public boolean getIn_progress() {
        return in_progress;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public boolean getTo_test() {
        return to_test;
    }

    public boolean getDone() {
        return done;
    }

    public Task getTask() {
        return task;
    }


    public void setTo_do(boolean to_do) {
        this.to_do = to_do;
    }

    public void setIn_progress(boolean in_progress) {
        this.in_progress = in_progress;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setTo_test(boolean to_test) {
        this.to_test = to_test;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    public void setTask(Task task) {
        this.task = task;
    }
}
