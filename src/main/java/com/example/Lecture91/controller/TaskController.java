package com.example.Lecture91.controller;

import com.example.Lecture91.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<String>> getTasks(@RequestParam int count) {
        Pageable pageWithCountElements = PageRequest.of(0, count, Sort.by("id").ascending());
        return ResponseEntity.ok(taskService.findAllByLimit(pageWithCountElements));
    }
}
