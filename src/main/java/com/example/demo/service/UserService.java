package com.example.demo.service;

import com.example.demo.models.ResponseWrapper;
import com.example.demo.models.User;

import java.util.List;

public interface UserService {
    ResponseWrapper<User> getUserById(String id);
    ResponseWrapper<User> getUserByEmail(String email);
    ResponseWrapper<List<User>> getAllUsers();
    ResponseWrapper<User> createUser(User user);
    ResponseWrapper<User> updateUser(String id, User user);
    ResponseWrapper<Void> deleteUserById(String id);
    ResponseWrapper<List<User>> getUsersByName(String name);
}