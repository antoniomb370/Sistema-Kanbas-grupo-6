package com.SistemaKanbanGestionProyectos.GestorProyectos.Controller;

import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.TaskDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Task;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaskController {


    private final TaskService taskService;
    private final TaskRepository taskRepository;
    @Autowired
    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
         this.taskRepository = taskRepository;
    }

    // create task
    @PostMapping("/{projectId}")
    public ResponseEntity<Object> createTask(@PathVariable Long projectId, @RequestBody TaskDto taskDto) {
        return taskService.createTask(projectId, taskDto);
    }

    // get all tasks
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return taskService.getTasks();
    }


    // get task by page and size
    @GetMapping("/tasksForPage")
    public ResponseEntity<Object> getTasksForPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Task> taskPage = taskService.getTasksPage(page, size);
        List<Task> tasks = taskPage.getContent();

        // Construir el objeto de respuesta según el formato requerido
        HashMap<String, Object> response = new HashMap<>();
        response.put("total_elements", taskPage.getTotalElements());
        response.put("page", page);
        response.put("content", tasks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // update task by id
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return taskService.updateTask(id, taskDto);
    }

    // delete task by id
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }




    @GetMapping("/due/{projectId}")
    public List<TaskDto> getDueTasks(@PathVariable Long projectId) {
        return taskService.getDueTasks(projectId);
    }

}
