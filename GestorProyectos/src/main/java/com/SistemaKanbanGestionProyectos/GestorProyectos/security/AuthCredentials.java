package com.SistemaKanbanGestionProyectos.GestorProyectos.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;

}
