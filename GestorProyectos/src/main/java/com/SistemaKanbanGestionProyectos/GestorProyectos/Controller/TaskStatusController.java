package com.SistemaKanbanGestionProyectos.GestorProyectos.Controller;

import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.TaskDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.TaskStatusDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.TaskStatus;
import com.SistemaKanbanGestionProyectos.GestorProyectos.service.ProjectService;
import com.SistemaKanbanGestionProyectos.GestorProyectos.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class TaskStatusController {


    private final TaskStatusService taskStatusService;
    private final ProjectService projectService;
    @Autowired
    public TaskStatusController(  TaskStatusService taskStatusService , ProjectService projectService) {
        this.taskStatusService = taskStatusService;
         this.projectService = projectService;
    }

//    @PostMapping
//    public ResponseEntity<TaskStatus> createTaskStatus(@RequestBody TaskStatusDto taskStatusDto) {
//        TaskStatus createdTaskStatus = taskStatusService.createTaskStatus(taskStatusDto);
//        return new ResponseEntity<>(createdTaskStatus, HttpStatus.CREATED);
//    }

}
