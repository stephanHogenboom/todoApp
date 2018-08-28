package com.hogenboom.todoservice.presentation;

import com.hogenboom.todoservice.acces.ResourceNotFoundException;
import com.hogenboom.todoservice.acces.TaskRepository;
import com.hogenboom.todoservice.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @CrossOrigin
    @PostMapping("/tasks")
    public void createTask(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @CrossOrigin
    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.PUT)
    public void update(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        taskRepository.findById(taskId)
                .map(task -> {
                    task.setDateOfCompletion(updatedTask.getDateOfCompletion());
                    task.setName(updatedTask.getName());
                    task.setStatus(updatedTask.getStatus());
                    task.setPriority(updatedTask.getPriority());
                    task.setDeadline(updatedTask.getDeadline());
                    return taskRepository.save(task);
                }).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find a task with id = %s", taskId)));
    }

    @CrossOrigin
    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable Long taskId, @RequestBody Task toBeDeletedTask) {
        taskRepository.delete(toBeDeletedTask);
    }
}
