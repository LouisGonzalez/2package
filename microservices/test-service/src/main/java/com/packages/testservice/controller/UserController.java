package com.packages.testservice.controller;

import com.packages.testservice.entity.UserTest;
import com.packages.testservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserTest>> getAll() {
        List<UserTest> users = userService.getlAll();
        if(users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTest> getById(@PathVariable("id") int id) {
        UserTest user = userService.getUserById(id);
        if(user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserTest> save(@RequestBody UserTest user) {
        UserTest userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

}
