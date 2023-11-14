package com.SistemaKanbanGestionProyectos.GestorProyectos.Controller;

import com.SistemaKanbanGestionProyectos.GestorProyectos.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/v1")
public class TaskStatusController {


    private final TaskStatusService taskStatusService;

    @Autowired
    public TaskStatusController(TaskStatusService taskStatusService) {
        this.taskStatusService = taskStatusService;

    }
    @PutMapping("/taskStatus/{taskId}")
    public ResponseEntity<Object> updateProjectStatus(
            @PathVariable Long taskId,
            @RequestBody Map<String, String> requestBody
    ) {
        String newStatus = requestBody.get("newStatus");
        return taskStatusService.updateTaskStatus(taskId, newStatus);
    }

}
