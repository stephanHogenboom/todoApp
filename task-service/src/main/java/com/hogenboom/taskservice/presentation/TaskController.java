package com.hogenboom.taskservice.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hogenboom.taskservice.model.Task;
import com.hogenboom.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class TaskController {

    private ObjectMapper Mapper = new ObjectMapper();

    @Autowired
    private TaskService taskService;

    @CrossOrigin
    @GetMapping("/tasks")
    public void getTasks(HttpServletResponse response) throws IOException {
        List<Task> tasks = taskService.getAllTasks();
        String tasksAsJson = Mapper.writeValueAsString(tasks);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        response.getWriter().write(tasksAsJson);
    }

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

    // not supported by rest assured
    /*@CrossOrigin
    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable Long taskId, @RequestBody Task toBeDeletedTask) {
        taskService.deleteTask(toBeDeletedTask);
    }*/

    @CrossOrigin
    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);
        taskService.deleteTask(task);
    }
}