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
        return buildResponse(response);
    }

    @GetMapping(MappingConstants.ID)
    public ResponseEntity<ResponseWrapper<User>> getUserById(@PathVariable String id) {
        final ResponseWrapper<User> response = userService.getUserById(id);
        return buildResponse(response);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<User>> createUser(@RequestBody User user) {
        final ResponseWrapper<User> response = userService.createUser(user);
        return buildResponse(response);
    }

    @GetMapping(MappingConstants.NAME)
    public ResponseEntity<ResponseWrapper<List<User>>> getUsersByName(@RequestParam String name) {
        final ResponseWrapper<List<User>> response = userService.getUsersByName(name);
        return buildResponse(response);
    }

    @GetMapping(MappingConstants.EMAIL)
    public ResponseEntity<ResponseWrapper<User>> getUserByEmail(@RequestParam String email) {
        final ResponseWrapper<User> response = userService.getUserByEmail(email);
        return buildResponse(response);
    }

    @PutMapping(MappingConstants.ID)
    public ResponseEntity<ResponseWrapper<User>> updateUser(@PathVariable String id, @RequestBody User user) {
        final ResponseWrapper<User> response = userService.updateUser(id, user);
        return buildResponse(response);
    }

    @DeleteMapping(MappingConstants.ID)
    public ResponseEntity<ResponseWrapper<Void>> deleteUserById(@PathVariable String id) {
        final ResponseWrapper<Void> response = userService.deleteUserById(id);
        return buildResponse(response);
    }

    private <T> ResponseEntity<ResponseWrapper<T>> buildResponse(ResponseWrapper<T> response) {
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
