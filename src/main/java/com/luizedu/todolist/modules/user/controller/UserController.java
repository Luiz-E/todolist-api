package com.luizedu.todolist.modules.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizedu.todolist.modules.user.model.UserModel;
import com.luizedu.todolist.modules.user.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/allusers")
    public ResponseEntity<List<UserModel>> listAll() {
        return ResponseEntity.ok(userService.allUsers());
    }
}
