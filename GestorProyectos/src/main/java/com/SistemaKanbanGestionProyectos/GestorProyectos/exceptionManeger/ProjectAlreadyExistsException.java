package com.SistemaKanbanGestionProyectos.GestorProyectos.exceptionManeger;

public class ProjectAlreadyExistsException extends RuntimeException {
    public ProjectAlreadyExistsException(String message) {
            super(message);
        }

}
