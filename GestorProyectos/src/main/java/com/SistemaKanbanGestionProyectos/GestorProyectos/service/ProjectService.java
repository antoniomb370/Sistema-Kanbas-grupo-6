package com.SistemaKanbanGestionProyectos.GestorProyectos.service;
import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.ProjectDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.exceptionManeger.ProjectAlreadyExistsException;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.ProjectStatus;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Task;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.ProjectRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.ProjectStatusRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectStatusRepository projectStatusRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          ProjectStatusRepository projectStatusRepository,
                          TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.projectStatusRepository = projectStatusRepository;
        this.taskRepository = taskRepository;
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
// Crear el proyecto
            Project project = new Project(projectDto.getName(), projectDto.getDescription());
            project.setStatus("active");

            // crear estado del proyecto
            ProjectStatus projectStatus = new ProjectStatus();
            projectStatus.setActive(true);
            projectStatus.setProject(project);
            project.setProjectStatuses(projectStatus);

// guardar el proyecto y el estado del proyecto
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

    // tarer todos los porject de manera paginada

    public Page<Project> getProjectsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return projectRepository.findAll(pageable);
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
                projectDto.setStatus(project.getStatus());
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
    // Traer tablero por tipo de estado
    public Map<String, Object> getProjectBoard(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            // Obtener todas las tareas del proyecto y agruparlas por estado
            List<Task> tasks = taskRepository.findByProjectId(id);
            Map<String, List<Task>> tasksByStatus = tasks.stream()
                    .collect(Collectors.groupingBy(Task::getCurrentStatus));
            // Crear el tablero
            List<Map<String, Object>> board = new ArrayList<>();
            for (Map.Entry<String, List<Task>> entry : tasksByStatus.entrySet()) {
                Map<String, Object> statusTasks = new HashMap<>();
                statusTasks.put("status", entry.getKey());
                statusTasks.put("tasks", entry.getValue());
                board.add(statusTasks);
            }
            // Crear la respuesta
            Map<String, Object> response = new HashMap<>();
            response.put("project", project);
            response.put("board", board);
            return response;
        } else {
            throw new EntityNotFoundException("No se encontró ningún proyecto con el ID: " + id);
        }
    }


}