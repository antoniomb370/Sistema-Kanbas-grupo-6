package com.SistemaKanbanGestionProyectos.GestorProyectos.Controller;

import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.ProjectDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import com.SistemaKanbanGestionProyectos.GestorProyectos.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {


    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
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

    // actualizar un  proyecto por id
    @PutMapping(path = "projects/{id}")
    public ResponseEntity<Object>  updateProjectById(@PathVariable("id") Long id, @RequestBody ProjectDto projectDto) {
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
}