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
        return taskRepository.findAllRandomTasksByCount(count);
    }

}
