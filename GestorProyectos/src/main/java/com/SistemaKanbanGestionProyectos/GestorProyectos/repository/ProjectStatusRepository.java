package com.SistemaKanbanGestionProyectos.GestorProyectos.repository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStatusRepository  extends JpaRepository<ProjectStatus, Long> {

}
