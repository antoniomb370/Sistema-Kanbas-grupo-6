package com.SistemaKanbanGestionProyectos.GestorProyectos.Controller;

import com.SistemaKanbanGestionProyectos.GestorProyectos.service.ProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProjectStatusController {

    private final ProjectStatusService projectStatusService;

    @Autowired
    public ProjectStatusController(ProjectStatusService projectStatusService) {
        this.projectStatusService = projectStatusService;
    }

    // actualizar el estado de un proyecto
    @PutMapping("/status/{projectId}")
    public ResponseEntity<Object> updateProjectStatus(
            @PathVariable Long projectId,
            @RequestBody Map<String, String> requestBody
    ) {
        String newStatus = requestBody.get("newStatus");
        return projectStatusService.updateProjectStatus(projectId, newStatus);
    }


}
