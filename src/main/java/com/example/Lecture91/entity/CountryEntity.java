package com.example.Lecture91.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Entity
@Table(name = "country")
public class CountryEntity {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15, unique = true, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String continent;

    @Column(nullable = false)
    private int area;
}
