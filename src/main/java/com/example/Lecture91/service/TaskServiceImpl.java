package com.example.Lecture91.service;

import com.example.Lecture91.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;

    }

    @Override
    public List<String> findAllRandomTasksByCount(int count) {
//        return entityManager.createQuery("SELECT t.name FROM TaskEntity t ORDER BY RAND()", String.class)
//                .setMaxResults(limit).getResultList();
        return taskRepository.findAllRandomTasksByCount(count);
    }

}
