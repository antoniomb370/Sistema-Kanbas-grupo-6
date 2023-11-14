package com.SistemaKanbanGestionProyectos.GestorProyectos.repository;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long id);

}
