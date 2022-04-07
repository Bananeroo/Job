package com.example.demo.tasks;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public @Data
class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_sequence")
    @SequenceGenerator(name = "tasks_sequence", sequenceName = "tasks_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long programmerId;
    private String title;
    private String description;
    private String finishNote;
    private Date dateOfTaskCompletion;


}
