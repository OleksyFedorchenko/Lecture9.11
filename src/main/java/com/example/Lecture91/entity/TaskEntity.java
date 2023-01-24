package com.example.Lecture91.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Entity
@Table(name = "task")
public class TaskEntity {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15, unique = true, nullable = false)
    private String name;
}
