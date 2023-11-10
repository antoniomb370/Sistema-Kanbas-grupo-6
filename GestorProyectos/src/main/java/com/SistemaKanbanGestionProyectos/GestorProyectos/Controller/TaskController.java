package com.SistemaKanbanGestionProyectos.GestorProyectos.Controller;


import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.TaskDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Task;
import com.SistemaKanbanGestionProyectos.GestorProyectos.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//    @PostMapping("task/{projectId}")
//    public ResponseEntity<Object> createTask(@RequestBody TaskDto taskDto, @PathVariable Long projectId) {
//        HashMap<String, Object> response = new HashMap<>();
//
//        try {
//            ResponseEntity<Object> taskCreationResponse = taskService.createTask(taskDto, projectId);
//
//            // Puedes incluir más lógica de respuesta aquí si es necesario
//
//            return taskCreationResponse;
//        } catch (Exception e) {
//            response.put("error", true);
//            response.put("message", "Error al crear la tarea: " + e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("tasks")
//    public ResponseEntity<List<Task>> getTasks() {
//        return taskService.getTasks();
//
//    }

    @PostMapping("tasks/{projectId}")
    public ResponseEntity<Object> createTask(@PathVariable Long projectId, @RequestBody TaskDto taskDto) {
        return taskService.createTask(projectId, taskDto);
    }
}
