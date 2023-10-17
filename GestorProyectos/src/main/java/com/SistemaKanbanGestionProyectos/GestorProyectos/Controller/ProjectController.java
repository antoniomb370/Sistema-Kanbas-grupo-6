package com.SistemaKanbanGestionProyectos.GestorProyectos.Controller;

import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import com.SistemaKanbanGestionProyectos.GestorProyectos.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {


    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getProjects() {
        return this.projectService.getProjects();
    }

    @PostMapping
    public ResponseEntity<Object> registerNewProject(@RequestBody Project project) {
        return this.projectService.addNewProject(project);
    }

    @PutMapping(path = "projects/{id}")
    public Project actualizarProjectById(@PathVariable("id") Integer id, @RequestBody Project project) {
        return this.projectService.actualizar(id, project);
    }

    @DeleteMapping(path = "projects/{id}")
    public ResponseEntity<Object> deleteProjectById(@PathVariable("id") Long id) {
     return   this.projectService.deleteProject(id);

}
}
