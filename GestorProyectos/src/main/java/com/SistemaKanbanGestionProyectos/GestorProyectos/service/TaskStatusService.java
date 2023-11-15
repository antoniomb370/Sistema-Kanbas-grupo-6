package com.SistemaKanbanGestionProyectos.GestorProyectos.service;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Task;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.TaskStatus;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.ProjectRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class TaskStatusService {
    @Autowired
    private final TaskStatusRepository taskStatusRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskStatusService(TaskStatusRepository taskStatusRepository, ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.taskStatusRepository = taskStatusRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public ResponseEntity<Object> updateTaskStatus(Long taskId, String newStatus) {
        HashMap<String, Object> datos = new HashMap<>();
        try {
            Optional<Task> optionalTask = taskRepository.findById(taskId);
            if (optionalTask.isPresent()) {
                Task task = optionalTask.get();
                TaskStatus taskStatus = task.getTaskStatus();
                // Verificar si el estado enviado es válido
                if (!"TODO".equals(newStatus) && !"INPROGRESS".equals(newStatus)
                        && !"BLOCKED".equals(newStatus) && !"DONE".equals(newStatus)) {
                    datos.put("mensaje", "El estado " + newStatus + " no es válido");
                    return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
                }
                // Verificar si el cambio de estado es válido
                String currentStatus = task.getCurrentStatus();
                if ("TODO".equals(currentStatus) && !"INPROGRESS".equals(newStatus)
                        || "INPROGRESS".equals(currentStatus) && "TODO".equals(newStatus)
                        || "BLOCKED".equals(currentStatus) && "TODO".equals(newStatus)
                        || "DONE".equals(currentStatus)) {
                    datos.put("mensaje", "el estado " + newStatus + " no es válido");
                    return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
                }
                // Actualizar los campos toDo, inProgress, blocked y done
                taskStatus.setToDo("TODO".equals(newStatus));
                taskStatus.setInProgress("INPROGRESS".equals(newStatus));
                taskStatus.setBlocked("BLOCKED".equals(newStatus));
                taskStatus.setDone("DONE".equals(newStatus));
                // Actualizar el estado de la tarea
                task.setCurrentStatus(newStatus);
                taskStatusRepository.save(taskStatus);
                datos.put("datos", task);
                datos.put("message", "Se ha actualizado el estado de la tarea con éxito");
                return new ResponseEntity<>(datos, HttpStatus.OK);
            } else {
                datos.put("error", true);
                datos.put("message", "No se encontró ninguna tarea con el ID: " + taskId);
                return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            datos.put("error", true);
            datos.put("message", "Error al actualizar el estado de la tarea: " + e.getMessage());
            return new ResponseEntity<>(datos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
