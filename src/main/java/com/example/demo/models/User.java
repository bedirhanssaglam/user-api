package com.example.demo.models;

import org.springframework.data.annotation.Id;

// Represents a User entity with id, name, and email fields
public class User {

    @Id // The @Id annotation indicates that this field is the primary identifier for the entity
    private String id; // Unique identifier for the user
    private String name; // Name of the user
    private String email; // Email address of the user

    // Getter for id
    public String getId() {
        return id;
    }

    // Setter for id
    public void setId(String id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }
}
