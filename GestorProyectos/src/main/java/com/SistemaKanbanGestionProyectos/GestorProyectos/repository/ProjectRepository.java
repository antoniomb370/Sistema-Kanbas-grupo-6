package com.SistemaKanbanGestionProyectos.GestorProyectos.repository;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findProjectByName(String name);
    Optional<Project> findById(Integer id);
}
