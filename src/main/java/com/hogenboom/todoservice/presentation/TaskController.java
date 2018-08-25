package com.hogenboom.todoservice.presentation;

import com.hogenboom.todoservice.acces.ResourceNotFoundException;
import com.hogenboom.todoservice.acces.TaskRepository;
import com.hogenboom.todoservice.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping("/tasks")
    public String todo(Model model, @RequestParam(value="name", required=false, defaultValue="stranger") String name) {
        model.addAttribute("name", name);
        return "to_do_page";
    }


    @CrossOrigin
    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task) {
        return taskRepository.save(task);
    }


    @CrossOrigin
    @PutMapping("/tasks")
    public Task updateTask(@PathVariable Long taskId,
                           @Valid @RequestBody Task updatedTask) {
        return taskRepository.findById(taskId)
                .map(task -> {
                    task.setDateOfCompletion(updatedTask.getDateOfCompletion());
                    updatedTask.setName(updatedTask.getName());
                    return taskRepository.save(task);
                }).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find a task with id = %s", taskId)));
    }




}
