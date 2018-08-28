package com.hogenboom.todoservice.service;

import com.hogenboom.todoservice.acces.TaskRepository;
import com.hogenboom.todoservice.model.Task;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.save(task);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    private void validateTask(Task task) {

    }
}
