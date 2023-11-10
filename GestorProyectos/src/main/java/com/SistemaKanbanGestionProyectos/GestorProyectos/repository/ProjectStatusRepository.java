package com.SistemaKanbanGestionProyectos.GestorProyectos.repository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProjectStatusRepository  extends JpaRepository<ProjectStatus, Long> {
//    Optional<ProjectStatus> findProjectByName(String name);
//
//    Optional<ProjectStatus> findById(Integer id);
}
