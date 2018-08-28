package com.hogenboom.todoservice.presentation;

import com.hogenboom.todoservice.acces.ResourceNotFoundException;
import com.hogenboom.todoservice.acces.TaskRepository;
import com.hogenboom.todoservice.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping("/todo")
    public String getToDoPage(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "to_do_page";
    }

    @RequestMapping(value = "/todo/update", method = RequestMethod.GET)
    public String toUpDatePage(Model model, @RequestParam Long taskId) {
        Task task = taskRepository.findTaskById(taskId);
        model.addAttribute("task", task);
        return "update_task";
    }


    @CrossOrigin
    @PostMapping("/tasks")
    public void createTask(@RequestBody Task task) {
        taskRepository.save(task);
    }


    @CrossOrigin
    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.PUT)
    public String update(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        taskRepository.findById(taskId)
                .map(task -> {
                    task.setDateOfCompletion(updatedTask.getDateOfCompletion());
                    task.setName(updatedTask.getName());
                    task.setStatus(updatedTask.getStatus());
                    task.setPriority(updatedTask.getPriority());
                    task.setDeadline(updatedTask.getDeadline());
                    return taskRepository.save(task);
                }).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find a task with id = %s", taskId)));
        return "to_do_page";
    }

    @CrossOrigin
    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.DELETE)
    public String deleteTask(@PathVariable Long taskId, @RequestBody Task toBeDeletedTask) {
        Task savedTask = taskRepository.findTaskById(taskId);
        taskRepository.delete(savedTask);
        return "to_do_page";
    }
}
