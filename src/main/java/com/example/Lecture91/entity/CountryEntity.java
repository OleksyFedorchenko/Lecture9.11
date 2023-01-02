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
}
