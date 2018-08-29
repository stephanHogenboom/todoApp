package com.hogenboom.taskservice.presentation;

import com.hogenboom.taskservice.acces.TaskRepository;
import com.hogenboom.taskservice.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ToDoController {

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping("/todo")
    public String getToDoPage(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "to_do_page";
    }
}