package com.SistemaKanbanGestionProyectos.GestorProyectos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task_status")
public class TaskStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_status_type;
    @Column(name = "to_do")
    private boolean toDo;

    @Column(name = "in_progress")
    private boolean inProgress;

    @Column(name = "blocked")
    private boolean blocked;

    @Column(name = "done")
    private boolean done;




}
