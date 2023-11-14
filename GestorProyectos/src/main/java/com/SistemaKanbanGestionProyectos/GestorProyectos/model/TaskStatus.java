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
    private boolean toDo;

    @Column(name = "in_progress")
    private boolean inProgress;

    @Column(name = "blocked")
    private boolean blocked;

    @Column(name = "done")
    private boolean done;


    public Long getId_status_type() {
        return id_status_type;
    }

    public void setId_status_type(Long id_status_type) {
        this.id_status_type = id_status_type;
    }

    public boolean getToDo() {
        return toDo;
    }

    public void setToDo(boolean toDo) {
        this.toDo = toDo;
    }

    public boolean getInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


}
