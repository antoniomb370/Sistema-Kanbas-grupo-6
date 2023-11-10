package com.SistemaKanbanGestionProyectos.GestorProyectos.dto;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Task;


public class TaskStatusDto {

    private Long id_status_type;

    private boolean to_do;

    private boolean in_progress;

    private boolean blocked;

    private boolean to_test;

    private boolean done;

    private Task task;


      public TaskStatusDto() {

      }
    public TaskStatusDto(  Long id_status_type, boolean to_do, boolean in_progress, boolean blocked, boolean to_test, boolean done, Task task) {
        this.id_status_type = id_status_type;
        this.to_do = to_do;
        this.in_progress = in_progress;
        this.blocked = blocked;
        this.to_test = to_test;
        this.done = done;
        this.task = task;
    }

    public TaskStatusDto(boolean to_do, boolean in_progress, boolean blocked, boolean to_test, boolean done, Task task) {
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

    public void setId_status_type(Long id_status_type) {
        this.id_status_type = id_status_type;
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
