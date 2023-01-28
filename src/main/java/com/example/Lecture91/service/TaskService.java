package com.example.Lecture91.service;

import java.util.List;

public interface TaskService {
    List<String>findAllRandomTasksByCount(int count);

    List<String>findAllLimit(int count);
}
