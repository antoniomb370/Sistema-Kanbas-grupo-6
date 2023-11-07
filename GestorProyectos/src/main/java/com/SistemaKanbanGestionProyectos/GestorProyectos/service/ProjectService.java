package com.SistemaKanbanGestionProyectos.GestorProyectos.service;

import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.ProjectDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ResponseEntity<Object> createProject(ProjectDto projectDto) {
        HashMap<String, Object> datos = new HashMap<>();
        boolean hasName = projectDto.getName() != null && !projectDto.getName().isEmpty();
        boolean hasDescription = projectDto.getDescription() != null && !projectDto.getDescription().isEmpty();

        if (!hasName && !hasDescription) {
            datos.put("error", true);
            datos.put("message", "El nombre y el detalle del proyecto son requeridos");
            return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
        }

        if (!hasName) {
            datos.put("error", true);
            datos.put("message", "El nombre del proyecto es requerido");
            return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
        }

        if (!hasDescription) {
            datos.put("error", true);
            datos.put("message", "El detalle del proyecto es requerido");
            return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
        }

        Optional<Project> res = projectRepository.findProjectByName(projectDto.getName());


        if (res.isPresent()) {
            datos.put("error", true);
            datos.put("message", "El nombre del proyecto ya existe");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Project project = new Project(projectDto.getId(),
                projectDto.getName(),
                projectDto.getDescription());
        projectRepository.save(project);
        datos.put("datos", project);
        datos.put("message", "Se ha creado el proyecto con exito");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }


    public List<ProjectDto> getProjects() {

        return projectRepository.findAll()
                .stream()
                .map(project -> new ProjectDto(
                        project.getId(),
                        project.getName(),
                        project.getDescription()))
                .collect(Collectors.toList());
    }


//    public Project updateProject(Integer id, Project project) {
//        if (id == null) {
//            throw new RuntimeException("El id es requerido");
//        }
//        Optional<Project> projectOptional = this.projectRepository.findById(id);
//        if (projectOptional.isPresent()) {
//            Project projectEncontrado = projectOptional.get();
//            projectEncontrado.setName(project.getName());
//            projectEncontrado.setDescription(project.getDescription());
//            return this.projectRepository.save(projectEncontrado);
//        } else {
//            throw new RuntimeException("El proyecto no existe");
//        }
//    }

    // actualizar un  proyecto por id
    public ResponseEntity<Object> updateProject(Long id, ProjectDto projectDto) {
        Optional<Project> res = projectRepository.findById(id);
        HashMap<String, Object> datos = new HashMap<>();
        if (res.isPresent()) {
            Project project = res.get();
            project.setName(projectDto.getName());
            project.setDescription(projectDto.getDescription());
            projectRepository.save(project);
            datos.put("message", "Se ha actualizado el proyecto con exito");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.OK
            );
        }
        datos.put("error", true);
        datos.put("message", "El proyecto no se encontro");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CONFLICT
        );
    }

    public ResponseEntity<Object> deleteProject(Long id) {
        Optional<Project> res = projectRepository.findById(id);
        HashMap<String, Object> datos = new HashMap<>();
        if (res.isPresent()) {
            projectRepository.deleteById(id);
            datos.put("message", "Se ha eliminado el proyecto con exito");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.OK
            );
        }
        datos.put("error", true);
        datos.put("message", "El proyecto no se encontro");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CONFLICT
        );
    }


    public ResponseEntity<Object> getProjectById(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        HashMap<String, Object> datos = new HashMap<>();

        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            ProjectDto projectDto = new ProjectDto();
            projectDto.setId(project.getId());
            projectDto.setName(project.getName());
            projectDto.setDescription(project.getDescription());

            datos.put("project", projectDto);
            datos.put("message", "Proyecto encontrado con éxito");
            return new ResponseEntity<>(datos, HttpStatus.OK);
        }

        datos.put("error", true);
        datos.put("message", "El proyecto no se encontró");
        return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
    }


}