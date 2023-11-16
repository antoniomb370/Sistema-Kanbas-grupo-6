package com.SistemaKanbanGestionProyectos.GestorProyectos.Controller;

import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskTypeRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TaskTypeController {


    private final TaskTypeService taskTypeService;
    private final TaskTypeRepository taskTypeRepository;

    @Autowired
    public TaskTypeController(TaskTypeService taskTypeService,
                              TaskTypeRepository taskTypeRepository) {
        this.taskTypeService = taskTypeService;
        this.taskTypeRepository = taskTypeRepository;
    }

    @PutMapping("/taskType/{taskId}")
    public ResponseEntity<Object> updateProjectStatus(
            @PathVariable Long taskId,
            @RequestBody Map<String, String> requestBody
    ) {
        String newType = requestBody.get("newType");
        return taskTypeService.updateTaskType(taskId, newType);
    }


}
