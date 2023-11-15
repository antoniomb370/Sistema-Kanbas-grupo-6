package com.SistemaKanbanGestionProyectos.GestorProyectos.model;

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
@Table(name = "task_type")
public class TaskType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_task_type;
    @Column(name = "bug")
    private boolean bug;
    @Column(name = "story")
    private boolean story;
    @Column(name = "spike")
    private boolean spike;
    @Column(name = "bedt")
    private boolean bedt;


}




