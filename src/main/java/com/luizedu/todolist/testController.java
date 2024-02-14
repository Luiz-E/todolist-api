package com.luizedu.todolist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/")
public class testController {
    @GetMapping("/")
    public String testing() {
        return "Hello World!";
    }
    
}
