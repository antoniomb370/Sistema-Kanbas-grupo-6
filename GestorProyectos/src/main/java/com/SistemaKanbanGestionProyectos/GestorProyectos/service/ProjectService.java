package com.SistemaKanbanGestionProyectos.GestorProyectos.service;

import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.ProjectDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.exceptionManeger.ProjectAlreadyExistsException;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // crear un nuevo proyecto
    public ResponseEntity<Object> createProject(ProjectDto projectDto) {
        HashMap<String, Object> datos = new HashMap<>();

        try {
            // Validaciones
            if (projectDto.getName() == null || projectDto.getName().isEmpty()) {
                throw new IllegalArgumentException("El nombre del proyecto es requerido");
            }

            if (projectDto.getDescription() == null || projectDto.getDescription().isEmpty()) {
                throw new IllegalArgumentException("El detalle del proyecto es requerido");
            }

            Optional<Project> existingProject = projectRepository.findProjectByName(projectDto.getName());

            if (existingProject.isPresent()) {
                throw new ProjectAlreadyExistsException("El nombre del proyecto ya existe");
            }
// Crear y guardar el proyecto
            Project project = new Project(projectDto.getName(), projectDto.getDescription());
            projectRepository.save(project);

            datos.put("datos", project);
            datos.put("message", "Se ha creado el proyecto con éxito");
            return new ResponseEntity<>(datos, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            datos.put("error", true);
            datos.put("message", e.getMessage());
            return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
        } catch (ProjectAlreadyExistsException e) {
            datos.put("error", true);
            datos.put("message", e.getMessage());
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        } catch (Exception e) {
            datos.put("error", true);
            datos.put("message", "Error al crear el proyecto: " + e.getMessage());
            return new ResponseEntity<>(datos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public List<Project> getProjects() {
        return projectRepository.findAll();
    }


    // actualizar un  proyecto por id
    public ResponseEntity<Object> updateProject(Long id, ProjectDto projectDto) {

        HashMap<String, Object> datos = new HashMap<>();
        try {
            Optional<Project> res = projectRepository.findById(id);
            if (res.isPresent() && id > 0) {
                Project project = res.get();
                project.setName(projectDto.getName());
                project.setDescription(projectDto.getDescription());
                projectRepository.save(project);
                datos.put("message", "Se ha actualizado el proyecto con exito");
                return new ResponseEntity<>(
                        datos,
                        HttpStatus.OK
                );
            } else {
                datos.put("error", true);
                datos.put("message", "No se encontró ningún proyecto con el ID: " + id);
                return new ResponseEntity<>(
                        datos,
                        HttpStatus.NOT_FOUND
                );
            }
        } catch (Exception e) {
            datos.put("error", true);
            datos.put("message", "Error al actualizar el proyecto: " + e.getMessage());
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
    }

    // eliminar un proyecto por id
    public ResponseEntity<Object> deleteProject(Long id) {
        HashMap<String, Object> datos = new HashMap<>();

        try {
            Optional<Project> res = projectRepository.findById(id);
            if (res.isPresent()) {
                projectRepository.deleteById(id);
                datos.put("message", "Se ha eliminado el proyecto con exito");
                return new ResponseEntity<>(
                        datos,
                        HttpStatus.OK
                );
            } else {
                datos.put("error", true);
                datos.put("message", "No se encontró ningún proyecto con el ID: " + id);
                return new ResponseEntity<>(
                        datos,
                        HttpStatus.NOT_FOUND
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

    }

    // busca    un proyecto por id
    public ResponseEntity<Object> getProjectById(Long id) {

        HashMap<String, Object> datos = new HashMap<>();
        try {
            Optional<Project> projectOptional = projectRepository.findById(id);
            if (projectOptional.isPresent()) {
                Project project = projectOptional.get();
                ProjectDto projectDto = new ProjectDto();
                projectDto.setId(project.getId());
                projectDto.setName(project.getName());
                projectDto.setDescription(project.getDescription());
                projectDto.setCreateAt(project.getCreateAt());
                projectDto.setUpdateAt(project.getUpdateAt());
                datos.put("project", projectDto);
                datos.put("message", "Proyecto encontrado con éxito");
                return new ResponseEntity<>(datos, HttpStatus.OK);
            } else {
                datos.put("error", true);
                datos.put("message", "No se encontró ningún proyecto con el ID: " + id);
                return new ResponseEntity<>(
                        datos,
                        HttpStatus.NOT_FOUND
                );
            }

        } catch (Exception e) {
            datos.put("error", true);
            datos.put("message", "Error al buscar el proyecto");
            return new ResponseEntity<>(datos, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}