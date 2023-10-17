package com.SistemaKanbanGestionProyectos.GestorProyectos.service;

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

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects() {

        return projectRepository.findAll();

    }

    public ResponseEntity<Object> addNewProject( Project project) {
        Optional<Project> res = projectRepository.findProjectByName(project.getName());
        HashMap<String,Object> datos = new HashMap<>();

        if (res.isPresent()) {
            datos.put("error",true);
            datos.put("message", "El nombre del proyecto ya existe");
            return new ResponseEntity<>(
                   datos,
                    HttpStatus.CONFLICT
            );
        }

        projectRepository.save(project);
        datos.put("datos",project);
        datos.put("message", "Se ha creado el proyecto con exito");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );


    }


//      public ResponseEntity<Object> actualizar(Integer id, Project project) {
//
//        Optional<Project> res = projectRepository.findById(id);
//         HashMap<String,Object> datos = new HashMap<>();
//         if (res.isPresent()) {
//             datos.put("error",true);
//               datos.put("message", "El proyecto no se encontro");
//               return new ResponseEntity<>(
//                      datos,
//                       HttpStatus.CONFLICT
//               );
//
//         }
//         projectRepository.findById(id);
//         projectRepository.save(project);
//         datos.put("datos",project);
//         datos.put("message", "Se ha actualizado el proyecto con exito");
//         return new ResponseEntity<>(
//                 datos,
//                 HttpStatus.CREATED
//         );
//      }

    public Project actualizar(Integer id, Project project) {
         if (id == null) {
               throw new RuntimeException("El id es requerido");
         }
         Optional<Project> projectOptional = this.projectRepository.findById(id);
         if (projectOptional.isPresent()) {
               Project projectEncontrado = projectOptional.get();
               projectEncontrado.setName(project.getName());
               projectEncontrado.setDescription(project.getDescription());
               return this.projectRepository.save(projectEncontrado);
         } else {
               throw new RuntimeException("El proyecto no existe");
         }
    }

      public ResponseEntity<Object> deleteProject(Long id) {
         Optional<Project> res = projectRepository.findById(id);
         HashMap<String,Object> datos = new HashMap<>();
         if (res.isPresent()) {
               projectRepository.deleteById(id);
               datos.put("message", "Se ha eliminado el proyecto con exito");
               return new ResponseEntity<>(
                     datos,
                     HttpStatus.OK
               );
         }
         datos.put("error",true);
         datos.put("message", "El proyecto no se encontro");
         return new ResponseEntity<>(
                  datos,
                  HttpStatus.CONFLICT
         );
      }
}
