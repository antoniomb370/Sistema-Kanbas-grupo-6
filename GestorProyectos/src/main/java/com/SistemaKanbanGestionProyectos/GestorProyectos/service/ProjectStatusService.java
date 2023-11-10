package com.SistemaKanbanGestionProyectos.GestorProyectos.service;
import com.SistemaKanbanGestionProyectos.GestorProyectos.dto.ProjectStatusDto;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.Project;
import com.SistemaKanbanGestionProyectos.GestorProyectos.model.ProjectStatus;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.ProjectRepository;
import com.SistemaKanbanGestionProyectos.GestorProyectos.repository.ProjectStatusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Optional;


@Service
public class ProjectStatusService {

    private final ProjectStatusRepository projectStatusRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectStatusService(ProjectStatusRepository projectStatusRepository,
                                ProjectRepository projectRepository) {
        this.projectStatusRepository = projectStatusRepository;
        this.projectRepository = projectRepository;
    }


    public ResponseEntity<Object> updateProjectStatus(Long projectId, String newStatus) {
        HashMap<String, Object> datos = new HashMap<>();

        try {
            Optional<Project> optionalProject = projectRepository.findById(projectId);
            if (optionalProject.isPresent()) {
                Project project = optionalProject.get();

                // Actualizar el estado del proyecto
                project.setStatus(newStatus);
                projectRepository.save(project);

                // Actualizar el estado en ProjectStatus (asumo que hay una relación OneToOne entre Project y ProjectStatus)
                ProjectStatus projectStatus = project.getProjectStatuses();
                projectStatus.setActive("active".equals(newStatus));
                projectStatus.setPaused("paused".equals(newStatus));
                projectStatus.setInactive("inactive".equals(newStatus));
                projectStatusRepository.save(projectStatus);

                datos.put("datos", project);
                datos.put("message", "Se ha actualizado el estado del proyecto con éxito");
                return new ResponseEntity<>(datos, HttpStatus.OK);
            } else {
                datos.put("error", true);
                datos.put("message", "No se encontró ningún proyecto con el ID: " + projectId);
                return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            datos.put("error", true);
            datos.put("message", "Error al actualizar el estado del proyecto: " + e.getMessage());
            return new ResponseEntity<>(datos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}


