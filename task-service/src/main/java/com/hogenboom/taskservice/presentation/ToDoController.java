package com.hogenboom.taskservice.presentation;

import com.hogenboom.taskservice.acces.TaskRepository;
import com.hogenboom.taskservice.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ToDoController {

    private Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping("/todo")
    public String getToDoPage(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        LOGGER.info("Returned task list for todo page");
        return "to_do_page";
    }
}
