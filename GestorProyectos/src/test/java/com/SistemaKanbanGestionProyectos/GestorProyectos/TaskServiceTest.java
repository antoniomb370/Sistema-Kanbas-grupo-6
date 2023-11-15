package com.SistemaKanbanGestionProyectos.GestorProyectos;

import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.TaskDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.TaskTypeDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Task;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.TaskType;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.ProjectRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskStatusRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskTypeRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;
    @Mock
    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;
    private TaskStatusRepository taskStatusRepository;
    private TaskTypeRepository taskTypeRepository;

    @Test
    public void testCreateTask() {
        // Crear datos de prueba
        Long projectId = 1L;
        Project project = new Project();
        TaskDto taskDto = new TaskDto();
        taskDto.setName("Test Task");
        taskDto.setDescription("Test Description");
        taskDto.setTaskTypeString("Test Type");
        TaskTypeDto taskTypeDto = new TaskTypeDto();
        taskTypeDto.setBug(true);
        taskTypeDto.setStory(false);
        taskTypeDto.setSpike(false);
        taskTypeDto.setBedt(false);
        taskDto.setTaskType(taskTypeDto);

        // Configurar los mocks
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArguments()[0]);
        when(taskTypeRepository.save(any(TaskType.class))).thenAnswer(i -> i.getArguments()[0]);

        // Llamar al método que se está probando
        ResponseEntity<Object> response = taskService.createTask(projectId, taskDto);

        // Verificar los resultados

        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertNotNull(body);
        assertEquals("Se ha creado la tarea con éxito", body.get("message"));
        Task task = (Task) body.get("datos");
        assertEquals(taskDto.getName(), task.getName());
        assertEquals(taskDto.getDescription(), task.getDescription());
        assertEquals(taskDto.getTaskTypeString(), task.getTaskTypeString());
        assertEquals(taskDto.getStart_date(), task.getStart_date());
        assertEquals(taskDto.getDue_date(), task.getDue_date());
    }

//
////    @Test
////    public void testCreateTask_Success() {
////        // Crear datos de prueba
////        Long projectId = 1L;
////        Project project = new Project();
////        TaskDto taskDto = new TaskDto();
////        taskDto.setName("Test Task");
////
////        // Configurar los mocks
////        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
////        when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArguments()[0]);
////
////        // Llamar al método que se está probando
////        ResponseEntity<Object> response = taskService.createTask(projectId, taskDto);
////
////        // Verificar los resultados
////        assertEquals(HttpStatus.CREATED, response.getStatusCode());
////        @SuppressWarnings("unchecked")
////        Map<String, Object> body = (Map<String, Object>) response.getBody();
////        assertNotNull(body);
////        assertEquals("Se ha creado la tarea con éxito", body.get("message"));
////        Task task = (Task) body.get("datos");
////        assertEquals(taskDto.getName(), task.getName());
////    }
//
//    @Test
//    public void testCreateTask() {
//        // Crear datos de prueba
//        Long projectId = 1L;
//        Project project = new Project();
//        TaskDto taskDto = new TaskDto();
//        taskDto.setName("Test Task");
//
//        // Configurar los mocks
//        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
//        when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArguments()[0]);
//
//        // Llamar al método que se está probando
//        ResponseEntity<Object> response = taskService.createTask(projectId, taskDto);
//
//        // Verificar los resultados
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(projectRepository).findById(projectId);
//        verify(taskRepository).save(any(Task.class));
//    }
}
