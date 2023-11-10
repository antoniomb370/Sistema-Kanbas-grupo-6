package com.SistemaKanbanGestionProyectos.GestorProyectos.repository;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTypeRepository  extends JpaRepository<TaskType, Long> {


}
