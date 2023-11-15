package com.SistemaKanbanGestionProyectos.GestorProyectos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Long id;


    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @Column(name = "status")
    private String status;

    @JsonManagedReference
    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
    private ProjectStatus  projectStatuses;


    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }


}
