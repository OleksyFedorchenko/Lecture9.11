package com.example.Lecture91.controller;

import com.example.Lecture91.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/math/examples")
public class TaskController {
    TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<String>> getTasks(@RequestParam int count) {
        return ResponseEntity.ok(taskService.findAllRandomTasksByCount(count));
    }
}
