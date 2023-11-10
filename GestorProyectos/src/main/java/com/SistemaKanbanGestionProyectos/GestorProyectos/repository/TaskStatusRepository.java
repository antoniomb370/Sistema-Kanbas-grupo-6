package com.SistemaKanbanGestionProyectos.GestorProyectos.repository;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {


}
