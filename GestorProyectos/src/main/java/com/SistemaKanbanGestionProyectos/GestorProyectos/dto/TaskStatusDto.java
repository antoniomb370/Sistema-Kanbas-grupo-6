package com.SistemaKanbanGestionProyectos.GestorProyectos.dto;

public class TaskStatusDto {

    private Long id_status_type;

    private boolean toDo;
    private boolean inProgress;
    private boolean blocked;
    private boolean done;



    public TaskStatusDto() {

    }

    public TaskStatusDto(Long id_status_type, boolean toDo, boolean inProgress, boolean blocked, boolean done) {
        this.id_status_type = id_status_type;
        this.toDo = toDo;
        this.inProgress = inProgress;
        this.blocked = blocked;
        this.done = done;

    }

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
