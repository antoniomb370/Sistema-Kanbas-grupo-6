package com.SistemaKanbanGestionProyectos.GestorProyectos.service;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Task;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.TaskType;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class TaskTypeService {

    private final TaskTypeRepository taskTypeRepository;
    private final TaskRepository taskRepository;

    public TaskTypeService(TaskTypeRepository taskTypeRepository, TaskRepository taskRepository) {
        this.taskTypeRepository = taskTypeRepository;
        this.taskRepository = taskRepository;
    }

    public ResponseEntity<Object> updateTaskType(Long taskId, String newType) {
        HashMap<String, Object> datos = new HashMap<>();

        try {
            Optional<Task> optionalTask = taskRepository.findById(taskId);
            if (optionalTask.isPresent()) {
                Task task = optionalTask.get();

                // Actualizar el tipo de la tarea
                task.setTaskTypeString(newType);
                taskRepository.save(task);

                TaskType taskType = task.getTaskType();
                // Actualizar los campos toDo, inProgress, blocked y done
                taskType.setBug("BUG".equals(newType));
                taskType.setBedt("BEDT".equals(newType));
                taskType.setSpike("SPIKE".equals(newType));
                taskType.setStory("STORY".equals(newType));
                // Actualizar el estado de la tarea
                task.setTaskTypeString(newType);
                taskTypeRepository.save(taskType);

                datos.put("datos", task);
                datos.put("mensaje", "Se ha actualizado el tipo de la tarea con éxito");
                return new ResponseEntity<>(datos, HttpStatus.OK);
            } else {
                datos.put("mensaje", "No se encontró ninguna tarea con el ID: " + taskId);
                return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            datos.put("mensaje", "Error al actualizar el tipo de la tarea: " + e.getMessage());
            return new ResponseEntity<>(datos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
