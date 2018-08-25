package com.hogenboom.todoservice.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Example {

    @RequestMapping("/")
    @SuppressWarnings("unused")
    String home() {
        return "Hello World!";
    }


}