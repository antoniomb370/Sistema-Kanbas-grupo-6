package com.SistemaKanbanGestionProyectos.GestorProyectos.service;

import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.TaskDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.TaskTypeDto;
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
    private final TaskTypeRepository taskTypeRepository;

    @Autowired
    public TaskService(TaskTypeRepository taskTypeRepository, TaskRepository taskRepository, ProjectRepository projectRepository, TaskStatusRepository taskStatusRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.taskTypeRepository = taskTypeRepository;
    }


// create task

    public ResponseEntity<Object> createTask(Long projectId, TaskDto taskDto) {

        TaskTypeDto taskTypeDto = taskDto.getTaskType();
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

            TaskType taskType = new TaskType();
            taskType.setBug(taskTypeDto.getBug());
            taskType.setStory(taskTypeDto.getStory());
            taskType.setSpike(taskTypeDto.getSpike());
            taskType.setBedt(taskTypeDto.getBedt());
            taskType.setTask(task);

            List<TaskType> taskTypes = new ArrayList<>();
            taskTypes.add(taskType);
            task.setTas_type(taskTypes);

            taskRepository.save(task);


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
                task.getDue_date(taskDto.getDue_date());

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
            datos.put("message", "Error al eliminar la tarea: " + e.getMessage());
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }
    }

    // arregalr este metodo trae todo en null


    // get task by id
    public ResponseEntity<Object> getTaskById(Long id) {
        HashMap<String, Object> datos = new HashMap<>();
        try {
            Optional<Task> taskOptional = taskRepository.findById(id);
            if (taskOptional.isPresent()) {
                Task task = taskOptional.get();
                TaskDto taskDto = new TaskDto();
                taskDto.setId_task(task.getId_task());
                taskDto.setName(task.getName());
                taskDto.setDescription(task.getDescription());
                taskDto.setStart_date();
                taskDto.setDue_date();


                // Asegúrate de establecer todos los campos necesarios aquí
                datos.put("task", taskDto);
                datos.put("message", "Tarea encontrada con éxito");
                return new ResponseEntity<>(datos, HttpStatus.OK);
            } else {
                datos.put("error", true);
                datos.put("message", "No se encontró ninguna tarea con el ID: " + id);
                return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            datos.put("error", true);
            datos.put("message", "Error al buscar la tarea: " + e.getMessage());
            return new ResponseEntity<>(datos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
