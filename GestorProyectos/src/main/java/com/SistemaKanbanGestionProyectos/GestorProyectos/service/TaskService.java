package com.SistemaKanbanGestionProyectos.GestorProyectos.service;

import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.TaskDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Task;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.TaskStatus;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.ProjectRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskStatusRepository taskStatusRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository,ProjectRepository projectRepository, TaskStatusRepository taskStatusRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
         this.taskStatusRepository = taskStatusRepository;
    }


// create task

    public ResponseEntity<Object> createTask(Long projectId, TaskDto taskDto) {
        HashMap<String, Object> datos = new HashMap<>();

        try {
            Optional<Project> optionalProject = projectRepository.findById(projectId);
            if (!optionalProject.isPresent()) {
                datos.put("error", true);
                datos.put("message", "No se encontró ningún proyecto con el ID: " + projectId);
                return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
            }

            Project project = optionalProject.get();

            Task task = new Task();
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setStart_date(taskDto.getStart_date());
            task.getDue_date(taskDto.getDue_date());
            task.setProject(project);

            TaskStatus taskStatus = new TaskStatus();
            taskStatus.setTo_do(true);
            taskStatus.setTask(task);

            List<TaskStatus> taskStatuses = new ArrayList<>();
            taskStatuses.add(taskStatus);
            task.setTask_status(taskStatuses);

            taskRepository.save(task);


            // Cuando esté listo el TaskTypeRepository, descomenta estas líneas
        /*
        TaskType taskType = new TaskType();
        taskType.setType(taskDto.getType());
        taskType.setTask(task);
        taskTypeRepository.save(taskType);
        */

            datos.put("datos", task);
            datos.put("message", "Se ha creado la tarea con éxito");
            return new ResponseEntity<>(datos, HttpStatus.CREATED);
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

}
