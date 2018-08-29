package com.hogenboom.taskservice.presentation;

import com.hogenboom.taskservice.model.Task;
import com.hogenboom.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @CrossOrigin
    @PostMapping("/tasks")
    public void createTask(@RequestBody Task task, HttpServletResponse response) {
        taskService.createTask(task);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.PUT)
    public void update(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        taskService.updateTask(taskId, updatedTask);

    }

    @CrossOrigin
    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable Long taskId, @RequestBody Task toBeDeletedTask) {
        taskService.deleteTask(toBeDeletedTask);
    }
}
