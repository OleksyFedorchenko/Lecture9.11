package com.example.Lecture91.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
@Data
@Entity
@Table(name = "city")
public class CityEntity {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 15, nullable = false)
    private String name;

    @Column(nullable = false)
    private int population;

    @Column(name = "capital", nullable = false)
    private boolean capitalOfCountry;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}