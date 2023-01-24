package com.example.Lecture91.service;

import com.example.Lecture91.dto.TaskDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    List<String> findAllByLimit(Pageable pageable);
}
