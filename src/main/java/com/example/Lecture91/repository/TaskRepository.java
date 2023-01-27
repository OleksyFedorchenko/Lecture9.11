package com.example.Lecture91.repository;

import com.example.Lecture91.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long> {
    @Query("SELECT t.name FROM TaskEntity t ORDER BY RAND() LIMIT :lim")
    List<String> findAllRandomTasksByCount(@Param("lim") int count);
}
