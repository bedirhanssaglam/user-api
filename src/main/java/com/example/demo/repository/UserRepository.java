package com.example.demo.repository;

import com.example.demo.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

// Repository interface for performing CRUD operations on User entities
public interface UserRepository extends MongoRepository<User, String> {

    // Method to find users by their name
    List<User> findByName(String name);

    // Method to find a user by their email
    User findByEmail(String email);
}
