package com.SistemaKanbanGestionProyectos.GestorProyectos.repository;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {


}
