package com.SistemaKanbanGestionProyectos.GestorProyectos.service;

import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.TaskDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.exceptionManeger.ProjectNotFoundException;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Task;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.TaskStatus;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.TaskType;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.ProjectRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskStatusRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final TaskTypeRepository taskTypeRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskTypeRepository taskTypeRepository, ProjectRepository projectRepository, TaskStatusRepository taskStatusRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.taskTypeRepository = taskTypeRepository;

    }
// create task

    public ResponseEntity<Object> createTask(Long projectId, TaskDto taskDto) {
        HashMap<String, Object> datos = new HashMap<>();
        try {
            // Buscar el proyecto
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ProjectNotFoundException("No se encontró ningún proyecto con el ID: " + projectId));
            // Crear la tarea
            Task task = new Task();
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setCurrentStatus("TODO");
            task.setTaskTypeString(taskDto.getTaskTypeString());
            task.setStart_date(taskDto.getStart_date());
            task.setDue_date(taskDto.getDue_date());
            task.setCreateAt(LocalDateTime.now());
            task.setUpdateAt(LocalDateTime.now());
            task.setProject(project);
            // Crear y asignar los estados de la tarea
            TaskStatus taskStatus = new TaskStatus();
            taskStatus.setToDo(true);
            taskStatus.setInProgress(false);
            taskStatus.setBlocked(false);
            taskStatus.setDone(false);
            task.setTaskStatus(taskStatus);
            // Crear y asignar el tipo de tarea
            TaskType taskType = new TaskType();
            taskType.setBug(taskDto.getTaskType().isBug());
            taskType.setStory(taskDto.getTaskType().isStory());
            taskType.setSpike(taskDto.getTaskType().isSpike());
            taskType.setBedt(taskDto.getTaskType().isBedt());
            taskTypeRepository.save(taskType);
            task.setTaskType(taskType);
            taskRepository.save(task);
            datos.put("datos", task);
            datos.put("message", "Se ha creado la tarea con éxito");
            return new ResponseEntity<>(datos, HttpStatus.CREATED);
        } catch (ProjectNotFoundException e) {
            datos.put("error", true);
            datos.put("message", "No se encontró ningún proyecto con el ID: " + projectId);
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            datos.put("error", true);
            datos.put("message", "Error al crear la tarea: " + e.getMessage());
            return new ResponseEntity<>(datos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get tasks
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // get task by page
    public Page<Task> getTasksPage(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return taskRepository.findAll(pageable);
    }

    // update task by id
    public ResponseEntity<Object> updateTask(Long id, TaskDto taskDto) {
        HashMap<String, Object> datos = new HashMap<>();
        try {
            Optional<Task> res = taskRepository.findById(id);
            if (res.isPresent() && id > 0) {
                Task task = res.get();
                task.setName(taskDto.getName());
                task.setDescription(taskDto.getDescription());
                task.setStart_date(taskDto.getStart_date());
                task.setDue_date(taskDto.getDue_date());

                taskRepository.save(task);
                datos.put("message", "Se ha actualizado la tarea con éxito");
                return new ResponseEntity<>(datos, HttpStatus.OK);
            } else {
                datos.put("error", true);
                datos.put("message", "No se encontró ninguna tarea con el ID: " + id);
                return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            datos.put("error", true);
            datos.put("message", "Error al actualizar la tarea: " + e.getMessage());
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }
    }

    // delete task by id
    public ResponseEntity<Object> deleteTask(Long id) {
        HashMap<String, Object> datos = new HashMap<>();
        try {
            Optional<Task> res = taskRepository.findById(id);
            if (res.isPresent()) {
                taskRepository.deleteById(id);
                datos.put("message", "Se ha eliminado la tarea con éxito");
                return new ResponseEntity<>(datos, HttpStatus.OK);
            } else {
                datos.put("error", true);
                datos.put("message", "No se encontró ninguna tarea con el ID: " + id);
                return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            datos.put("error", true);
            datos.put("message", "Error al eliminar la tarea: ");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }
    }

    // arregalr este metodo trae todo en null


    // get task by id
//    public ResponseEntity<Object> getTaskById(Long id) {
//        HashMap<String, Object> datos = new HashMap<>();
//        try {
//            Optional<Task> taskOptional = taskRepository.findById(id);
//            if (taskOptional.isPresent()) {
//                Task task = taskOptional.get();
//                TaskDto taskDto = new TaskDto();
//                taskDto.setId_task(task.getId_task());
//                taskDto.setName(task.getName());
//                taskDto.setDescription(task.getDescription());
//                  taskDto.setStart_date();
//
//                taskDto.setCreateAt(task.getCreateAt());
//                taskDto.setUpdateAt(task.getUpdateAt());
//                taskDto.setDue_date();
//                taskDto.
//
//                        Optional<Project> projectOptional = projectRepository.findById(id);
//                if (projectOptional.isPresent()) {
//                    Project project = projectOptional.get();
//                    ProjectDto projectDto = new ProjectDto();
//                    projectDto.setId(project.getId());
//                    projectDto.setName(project.getName());
//                    projectDto.setDescription(project.getDescription());
//                    projectDto.setStatus(project.getStatus());
//                    projectDto.setCreateAt(project.getCreateAt());
//                    projectDto.setUpdateAt(project.getUpdateAt());
//                    datos.put("project", projectDto);
//                    datos.put("message", "Proyecto encontrado con éxito");
//                    return new ResponseEntity<>(datos, HttpStatus.OK);
//
//                    // Asegúrate de establecer todos los campos necesarios aquí
//                    datos.put("task", taskDto);
//                    datos.put("message", "Tarea encontrada con éxito");
//                    return new ResponseEntity<>(datos, HttpStatus.OK);
//                } else {
//                    datos.put("error", true);
//                    datos.put("message", "No se encontró ninguna tarea con el ID: " + id);
//                    return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
//                }
//            } catch(Exception e){
//                datos.put("error", true);
//                datos.put("message", "Error al buscar la tarea: " + e.getMessage());
//                return new ResponseEntity<>(datos, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }

}
