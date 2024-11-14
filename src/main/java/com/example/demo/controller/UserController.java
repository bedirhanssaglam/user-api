package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.models.ResponseWrapper;
import com.example.demo.models.User;
import com.example.demo.utils.constants.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MappingConstants.USERS)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<User>>> getAllUsers() {
        final ResponseWrapper<List<User>> response = userService.getAllUsers();
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping(MappingConstants.ID)
    public ResponseEntity<ResponseWrapper<User>> getUserById(@PathVariable String id) {
        final ResponseWrapper<User> response = userService.getUserById(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<User>> createUser(@RequestBody User user) {
        final ResponseWrapper<User> response = userService.createUser(user);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping(MappingConstants.NAME)
    public ResponseEntity<ResponseWrapper<List<User>>> getUsersByName(@PathVariable String name) {
        final ResponseWrapper<List<User>> response = userService.getUsersByName(name);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping(MappingConstants.EMAIL)
    public ResponseEntity<ResponseWrapper<User>> getUserByEmail(@PathVariable String email) {
        final ResponseWrapper<User> response = userService.getUserByEmail(email);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping(MappingConstants.ID)
    public ResponseEntity<ResponseWrapper<User>> updateUser(@PathVariable String id, @RequestBody User user) {
        final ResponseWrapper<User> response = userService.updateUser(id, user);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping(MappingConstants.ID)
    public ResponseEntity<ResponseWrapper<Void>> deleteUserById(@PathVariable String id) {
        final ResponseWrapper<Void> response = userService.deleteUserById(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
