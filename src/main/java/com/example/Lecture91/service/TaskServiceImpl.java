package com.example.Lecture91.service;

import com.example.Lecture91.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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


    //Можна зробити рандом прикладів ще ось таким чином без БД.
    @Override
    public List<String> findAllLimit(int count) {
        Random rand = new Random();
        List<String> ops = Arrays.asList("+", "-", "/", "*");
        List<String> examples = new ArrayList<>();
        String sb;
        int max = 100;
        int min = 1;
        int range = max - min + 1;
        for (int i = 0; i < count; i++) {
            int randomIndex = rand.nextInt(ops.size());
            String randomOps = ops.get(randomIndex);
            sb = (((int) (Math.random() * range) + min) + " " + randomOps + " " + ((int) (Math.random() * range) + min));
            examples.add(sb);
        }
        return examples;

    }

}
