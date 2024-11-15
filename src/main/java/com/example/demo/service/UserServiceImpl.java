package com.example.demo.service;

import com.example.demo.models.ResponseWrapper;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.constants.ErrorMessages;
import com.example.demo.utils.constants.SuccessMessages;
import com.example.demo.utils.validators.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get a user by their unique ID
     *
     * @param id The ID of the user
     * @return ResponseEntity with user data or error if not found
     */
    @Override
    public ResponseWrapper<User> getUserById(String id) {
        return userRepository.findById(id)
                .map(ResponseWrapper::success)
                .orElse(ResponseWrapper.error(HttpStatus.NOT_FOUND.value(), ErrorMessages.USER_NOT_FOUND));
    }

    /**
     * Fetch a user by their email
     *
     * @param email The email of the user to search for
     * @return ResponseEntity with user data or error message if not found
     */
    @Override
    public ResponseWrapper<User> getUserByEmail(String email) {
        try {
            String decodedEmail = URLDecoder.decode(email, StandardCharsets.UTF_8);
            if (!Validators.isValidEmailFormat(decodedEmail)) {
                return ResponseWrapper.error(HttpStatus.BAD_REQUEST.value(), ErrorMessages.INVALID_EMAIL);
            }

            final User user = userRepository.findByEmail(decodedEmail);
            if (user == null) {
                return ResponseWrapper.error(HttpStatus.NOT_FOUND.value(), ErrorMessages.USER_NOT_FOUND);
            }
            return ResponseWrapper.success(user);
        } catch (Exception e) {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessages.GENERAL_ERROR);
        }
    }

    /**
     * Fetch all users from the database
     *
     * @return ResponseEntity with list of users or error if no users found
     */
    @Override
    public ResponseWrapper<List<User>> getAllUsers() {
        final List<User> users = userRepository.findAll();

        return ResponseWrapper.success(users);
    }

    /**
     * Create a new user
     *
     * @param user The user data to be created
     * @return ResponseEntity with created user data or error message
     */
    @Override
    public ResponseWrapper<User> createUser(User user) {
        if (!isValidUserData(user)) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST.value(), ErrorMessages.MISSING_USER_DATA);
        }
        if (!Validators.isValidEmailFormat(user.getEmail())) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST.value(), ErrorMessages.INVALID_EMAIL);
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseWrapper.error(HttpStatus.CONFLICT.value(), ErrorMessages.EMAIL_ALREADY_USED);
        }
        if (!Validators.isValidNameLength(user.getName())) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST.value(), ErrorMessages.NAME_TOO_LONG);
        }

        try {
            final User savedUser = userRepository.save(user);
            return ResponseWrapper.success(savedUser, SuccessMessages.USER_CREATED);
        } catch (Exception e) {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessages.GENERAL_ERROR);
        }
    }

    /**
     * Update an existing user by their unique ID
     *
     * @param id   The ID of the user to be updated
     * @param user The user data to be updated
     * @return ResponseEntity with updated user data or error message if not found or if email is already taken
     */
    @Override
    public ResponseWrapper<User> updateUser(String id, User user) {
        final Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isEmpty()) {
            return ResponseWrapper.error(HttpStatus.NOT_FOUND.value(), ErrorMessages.USER_NOT_FOUND);
        }

        final User existingUser = existingUserOptional.get();
        if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail())) {
            if (userRepository.findByEmail(user.getEmail()) != null) {
                return ResponseWrapper.error(HttpStatus.CONFLICT.value(), ErrorMessages.EMAIL_ALREADY_USED);
            }
        }

        if (user.getName() != null) existingUser.setName(user.getName());
        if (user.getEmail() != null) existingUser.setEmail(user.getEmail());

        try {
            final User updatedUser = userRepository.save(existingUser);
            return ResponseWrapper.success(updatedUser, SuccessMessages.USER_UPDATED);
        } catch (Exception e) {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessages.GENERAL_ERROR);
        }
    }

    /**
     * Delete a user by their unique ID
     *
     * @param id The ID of the user to be deleted
     * @return ResponseEntity indicating success or error
     */
    @Override
    public ResponseWrapper<Void> deleteUserById(String id) {
        final Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return ResponseWrapper.success(null, SuccessMessages.USER_DELETED);
        } else {
            return ResponseWrapper.error(HttpStatus.NOT_FOUND.value(), ErrorMessages.USER_NOT_FOUND);
        }
    }

    /**
     * Fetch users by their name
     *
     * @param name The name of the user to search for
     * @return ResponseEntity with list of users or error if no users found
     */
    @Override
    public ResponseWrapper<List<User>> getUsersByName(String name) {
        final List<User> users = userRepository.findByName(name);
        return ResponseWrapper.success(users);
    }

    private boolean isValidUserData(User user) {
        return user.getEmail() != null && user.getName() != null && !user.getEmail().isEmpty() && !user.getName().isEmpty();
    }
}
