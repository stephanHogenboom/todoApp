package com.hogenboom.taskservice.service;

import com.hogenboom.taskservice.acces.ResourceNotFoundException;
import com.hogenboom.taskservice.acces.TaskRepository;
import com.hogenboom.taskservice.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Simple service class that has an instance of the taskRepository
 *  When this class i called by the presentation layer it does a simple CRUD action
 *  on a single task. Create and update calls are first validated.
 */
@Component
public class TaskService {
    Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void createTask(Task task) {
        validateTask(task);
        taskRepository.save(task);
        logger.info("saved task: {}", task);
    }

    public void deleteTask(Task toBeDeletedTask) {
        taskRepository.delete(toBeDeletedTask);
        logger.info("deleted task: {}", toBeDeletedTask);
    }

    public Task getTaskById(long id) {
        return taskRepository.findTaskById(id);
    }

    public void updateTask(long taskId, Task updatedTask) {
        validateTask(updatedTask);
        taskRepository.findById(taskId)
                .map(task -> {
                    task.setDateOfCompletion(updatedTask.getDateOfCompletion());
                    task.setName(updatedTask.getName());
                    task.setStatus(updatedTask.getStatus());
                    task.setPriority(updatedTask.getPriority());
                    task.setDeadline(updatedTask.getDeadline());
                    return taskRepository.save(task);
                }).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find a task with id = %s", taskId)));
        logger.info("updated task: {}", updatedTask);
    }

    private void validateTask(Task task) {
        if (task.getStartDate() == null)   throw new IllegalArgumentException("A tasks start date cannot be null");
        if (isNullOrEmpty(task.getName())) throw new IllegalArgumentException("A tasks name cannot be null");
        if (isNullOrEmpty(task.getPriority())) throw new IllegalArgumentException("A tasks name cannot be null");
        if (isNullOrEmpty(task.getStatus())) throw new IllegalArgumentException("A tasks name cannot be null");
    }

    private boolean isNullOrEmpty(String field) {
        return field == null || field.length() == 0;
    }
}
