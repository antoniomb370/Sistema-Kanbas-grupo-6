package com.SistemaKanbanGestionProyectos.GestorProyectos;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.ProjectDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Task;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.ProjectRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.TaskRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.service.ProjectService;
import com.SistemaKanbanGestionProyectos.GestorProyectos.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.ExpectedCount;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;
    private TaskService taskService;
    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testCreateProject() {
        // Crear datos de prueba
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("Test Project");
        projectDto.setDescription("Test Description");

        // Configurar los mocks
        when(projectRepository.findProjectByName(projectDto.getName())).thenReturn(Optional.empty());

        // Llamar al método que se está probando
        ResponseEntity<Object> response = projectService.createProject(projectDto);

        // Verificar los resultados
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("Se ha creado el proyecto con éxito", body.get("message"));
        Project project = (Project) body.get("datos");
        assertEquals(projectDto.getName(), project.getName());
        assertEquals(projectDto.getDescription(), project.getDescription());
    }

    @Test
    public void testGetProjects() {
        // Crear datos de prueba
        Project project1 = new Project("Test Project 1", "Test Description 1");
        Project project2 = new Project("Test Project 2", "Test Description 2");
        List<Project> projects = Arrays.asList(project1, project2);

        // Configurar los mocks
        when(projectRepository.findAll()).thenReturn(projects);

        // Llamar al método que se está probando
        List<Project> result = projectService.getProjects();

        // Verificar los resultados
        assertEquals(projects, result);
    }


    @Test
    public void testGetProjectsPage() {
        // Crear datos de prueba
        Project project1 = new Project("Test Project 1", "Test Description 1");
        Project project2 = new Project("Test Project 2", "Test Description 2");
        List<Project> projects = Arrays.asList(project1, project2);
        Page<Project> page = new PageImpl<>(projects);

        // Configurar los mocks
        Pageable pageable = PageRequest.of(0, 2);
        when(projectRepository.findAll(pageable)).thenReturn(page);

        // Llamar al método que se está probando
        Page<Project> result = projectService.getProjectsPage(1, 2);

        // Verificar los resultados
        assertEquals(page, result);
    }

    @Test
    public void testUpdateProject() {
        // Crear datos de prueba
        ProjectDto projectDto = new ProjectDto("Test Project", "Test Description");
        Project project = new Project("Old Project", "Old Description");
        Long id = 1L;

        // Configurar los mocks
        when(projectRepository.findById(id)).thenReturn(Optional.of(project));

        // Llamar al método que se está probando
        ResponseEntity<Object> response = projectService.updateProject(id, projectDto);

        // Verificar los resultados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("Se ha actualizado el proyecto con exito", body.get("message"));
        assertEquals(projectDto.getName(), project.getName());
        assertEquals(projectDto.getDescription(), project.getDescription());
    }

    @Test
    public void testDeleteProject() {
        // Crear datos de prueba
        Project project = new Project("Test Project", "Test Description");
        Long id = 1L;

        // Configurar los mocks
        when(projectRepository.findById(id)).thenReturn(Optional.of(project));

        // Llamar al método que se está probando
        ResponseEntity<Object> response = projectService.deleteProject(id);

        // Verificar los resultados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("Se ha eliminado el proyecto con exito", body.get("message"));

        // Verificar que el método deleteById fue llamado
        verify(projectRepository, times(1)).deleteById(id);
    }


    @Test
    public void testGetProjectById() {
        // Crear datos de prueba
        Project project = new Project("Test Project", "Test Description");
        Long id = 1L;

        // Configurar los mocks
        when(projectRepository.findById(id)).thenReturn(Optional.of(project));

        // Llamar al método que se está probando
        ResponseEntity<Object> response = projectService.getProjectById(id);

        // Verificar los resultados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("Proyecto encontrado con éxito", body.get("message"));
        ProjectDto resultProject = (ProjectDto) body.get("project");
        assertEquals(project.getName(), resultProject.getName());
        assertEquals(project.getDescription(), resultProject.getDescription());
    }
    @Test
    public void testGetProjectBoard() {
        // Crear datos de prueba
        Project project = new Project("Test Project", "Test Description");
        Long id = 1L;
        Task task1 = new Task();
        task1.setName("Task 1");
        task1.setDescription("Description 1");
        task1.setCurrentStatus("Status 1");
        Task task2 = new Task();
        task2.setName("Task 1");
        task2.setDescription("Description 1");
        task2.setCurrentStatus("Status 1");
        List<Task> tasks = Arrays.asList(task1, task2);

        // Configurar los mocks
        when(projectRepository.findById(id)).thenReturn(Optional.of(project));
        when(taskRepository.findByProjectId(id)).thenReturn(tasks);

        // Llamar al método que se está probando
        Map<String, Object> response = projectService.getProjectBoard(id);

        // Verificar los resultados
        assertEquals(project, response.get("project"));
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> board = (List<Map<String, Object>>) response.get("board");
        for (Map<String, Object> statusTasks : board) {
            String status = (String) statusTasks.get("status");
            @SuppressWarnings("unchecked")
            List<Task> statusTaskList = (List<Task>) statusTasks.get("tasks");
            for (Task task : statusTaskList) {
                assertEquals(status, task.getCurrentStatus());
            }
        }
    }

}
