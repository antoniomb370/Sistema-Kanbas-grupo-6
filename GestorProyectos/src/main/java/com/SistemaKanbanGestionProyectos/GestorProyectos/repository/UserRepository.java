package com.SistemaKanbanGestionProyectos.GestorProyectos.repository;


import com.SistemaKanbanGestionProyectos.GestorProyectos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    public User findByUsername(String username);
}
