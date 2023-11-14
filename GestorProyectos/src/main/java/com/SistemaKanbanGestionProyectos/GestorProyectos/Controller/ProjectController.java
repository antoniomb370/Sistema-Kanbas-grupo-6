package com.SistemaKanbanGestionProyectos.GestorProyectos.Controller;

import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.ProjectDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import com.SistemaKanbanGestionProyectos.GestorProyectos.service.ProjectService;
import com.SistemaKanbanGestionProyectos.GestorProyectos.service.ProjectStatusService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectStatusService projectStatusService;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectStatusService projectStatusService) {
        this.projectService = projectService;
        this.projectStatusService = projectStatusService;
    }

    // crear un nuevo proyecto
    @PostMapping("project")
    public ResponseEntity<Object> registerNewProject(@RequestBody ProjectDto projectDto) {
        return this.projectService.createProject(projectDto);
    }

    // obtener todos los proyectos
    @GetMapping("projects")
    public List<Project> getProjects() {
        return this.projectService.getProjects();
    }

    @GetMapping("projectsForPage")
    public ResponseEntity<Object> getProjectsForPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {

        {
            Page<Project> projectPage = projectService.getProjectsPage(page, size);
            List<Project> projects = projectPage.getContent();

            // Construir el objeto de respuesta según el formato requerido
            HashMap<String, Object> response = new HashMap<>();
            response.put("total_elements", projectPage.getTotalElements());
            response.put("page", page);
            response.put("content", projects);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    // actualizar un  proyecto por id
    @PutMapping(path = "projects/{id}")
    public ResponseEntity<Object> updateProjectById(@PathVariable("id") Long id, @RequestBody ProjectDto projectDto) {
        return this.projectService.updateProject(id, projectDto);
    }


    // eliminar un proyecto por id
    @DeleteMapping(path = "project/{id}")
    public ResponseEntity<Object> deleteProjectById(@PathVariable("id") Long id) {
        return this.projectService.deleteProject(id);

    }

    // busca    un proyecto por id
    @GetMapping(path = "project/{id}")
    public ResponseEntity<Object> getProjectById(@PathVariable("id") Long id) {
        return this.projectService.getProjectById(id);
    }

    @GetMapping("projects/status")
    public ResponseEntity<List<Project>> getProjectsStatus() {
        List<Project> projects = projectService.getProjects();
        return ResponseEntity.ok(projects);
    }

    // traer todas las tareas por tipo de estado
    @GetMapping("/{id}/board")
    public ResponseEntity<Object> getProjectBoard(@PathVariable Long id) {
        try {
            Map<String, Object> board = projectService.getProjectBoard(id);
            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("No se encontró ningún proyecto con el ID: " + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener el tablero del proyecto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}